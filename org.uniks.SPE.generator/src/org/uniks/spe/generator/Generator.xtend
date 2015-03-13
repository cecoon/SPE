package org.uniks.spe.generator

import java.util.HashSet
import java.util.List
import java.util.Set
import model.MatchTag
import model.SPEAttribute
import model.SPEGroup
import model.SPELink
import model.SPEObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import model.IHasMatchTag

import static extension org.uniks.spe.generator.utils.CodeSnippets.*
import static extension org.uniks.spe.generator.utils.Extentions.*
import org.uniks.spe.generator.model.EntryPoint
import org.uniks.spe.generator.model.MatchState

class Generator implements IGenerator { 
	val START_OBJECT_NAME = "this"		
	val static attributeMatchHandler = initAttributeHandler();
		
	val ModelUpdateGenerator modelUpdater = new ModelUpdateGenerator
	var MatchState matchState;
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {
		matchState = new MatchState;						 	
		matchState.root = input.allContents.findFirst[it instanceof SPEGroup] as SPEGroup
		if (matchState.root == null) { return }
			
		matchState.allObjects = getAllObjects(matchState.root)
		fsa.generateFile('''MatchClass«matchState.root.name».java''', generateClassCode())
		
	}  		 
	  
	def generateClassCode() {				
		val start = matchState.allObjects.findFirst[name == START_OBJECT_NAME]	
		
		var matchCode = "//invalidDiagram"
		if(start != null){
			matchCode = '''«generateMatcherCode(start)»'''
		}
				
		'''  
			package matcher; 
			
			import model.*; 
			import model.util.*;	
			import de.uniks.networkparser.logic.Condition;
			
			@SuppressWarnings("all")
			public class MatchClass«matchState.root.name» {
				
				/**
				* finds a match from a given start 
				*/
				«matchCode»
				
			}
		'''		
	} 
	 
	def generateMatcherCode(SPEObject start) {
		matchState.markAsMatched(start)
		val type = start.type 	
		'''
		public «type»Set findMatch(«type» start){					
			«type»Set startSet = new «type»Set().with(start);
			«declarePO(start)» = startSet.has«type»PO()«createAttributeMatchCode(start.attributes, type)»;
			
			//matching objects of root grp
			«generateMatchCodeForNonAlienObjects(start)» 
			
			//match objects of subgroups
			«matchState.root.subGroups.fold("", [left, it|'''«left»«createCodeForSubgroupMatching»'''])»
			
			
			//matching missing links to known					
			«matchState.root.links.filter[!matchState.isMatched(it) && operation.isNotCreate]
					   .fold("", [left, it|'''«left»«createMatchCodeForMissingLink»'''])»
			
			//update model 
			«modelUpdater.generateModelUpdateCode(matchState)»
			
			return «start.varName».allMatches();
		} 
		''' 
	}
	 
	
	
	//region match code for links to unknown objects
	
	def generateMatchCodeForNonAlienObjects(SPEObject object) {
		val varName = varName(object);
		val groupOfObject = object.group
		object.outboundLinks.filter[tag.isntNot && operation.isNotCreate && groupOfObject == it.target.group]
							.fold("",[left, it|'''«left»«createMatchingCodeForLinkedObjects(it, varName)»'''])
	}
	

	def CharSequence createMatchingCodeForLinkedObjects(SPELink link, String fromVarName) {
		val target = link.target
		if (matchState.isMatched(target)) {
			return ""
		}
		matchState.markAsMatched(target) 
		matchState.markAsMatched(link)  
			
		var matchExpression = '''.has«link.Name»()«createAttributeMatchCode(target.attributes, target.type)»'''
		var matchExp = addMatchTags(matchExpression, target)		 			
		
		'''
			«declarePO(target)» = «fromVarName»«matchExp»;
			«generateMatchCodeForNonAlienObjects(target)»
		'''
	}
	

	//endregion		
	
	//region subgrpMatch	
	// 1. find an "alien" object in matchedObjects to start with
	// 2. generate code for non aliens
	// 3. generate missing links in grp and for all connected aliens
	// 4. party
	
	def createCodeForSubgroupMatching(SPEGroup group) {
		var alienLinks = findAlienLinks(group)				
		if(alienLinks.size == 0) return '''''' //ignore invalid diagrams
		
		var link = alienLinks.get(0);
	 	var entryPoint = extractEntryPoint(link, group);
		matchState.markAsMatched(link);
		alienLinks.remove(link)
		   
		'''
			«entryPoint.alien.varName».start«group.toSDMLibMatchTag»();
			«entryPoint.entrySourceCode»
			«generateMatchCodeForNonAlienObjects(entryPoint.start)»
			«alienLinks.fold("", [left, it|'''«left»«createMatchCodeForMissingLink»'''])»
			«entryPoint.alien.varName».end«group.toSDMLibMatchTag»();
		'''		  
	}
	
	def findAlienLinks(SPEGroup group) {  
	 	var alienLinks = newArrayList()
		for (object : matchState.matchedObjects){
			alienLinks.addAll(object.outboundLinks.filter[group == it.target.group])			
			alienLinks.addAll(object.inboundLinks.filter[group == it.source.group])
		}		
		return alienLinks	
	}
	
	def extractEntryPoint(SPELink link, SPEGroup grp){ 
		var result = new EntryPoint
		if(link.target.group == grp){  //is inboundLink	
			result.alien = link.source;
			result.start = link.target;						
			var type = result.start.type;			
			var attr = result.start.attributes;
			
			result.entrySourceCode = '''
			«declarePO(result.start)» = «result.alien.varName».has«link.Name»()«createAttributeMatchCode(attr, type)»;
			'''				
						
		}  else { //is outboundLink	result.alien = link.source;
			result.alien = link.target;
			result.start = link.source;						
			var type = result.start.type;			
			var attr = result.start.attributes; 
			
			result.entrySourceCode = '''				
			«declarePO(result.start)» = «createPO(result.start)»«createAttributeMatchCode(attr, type)»;
			«result.start.varName»«hasLinkToObj(link)»; 
			'''	
		}
		 
		return result
	}
	
	//endregion
	
	def createMatchCodeForMissingLink(SPELink link) { 
		matchState.markAsMatched(link);	
		'''
			«link.source.varName»«addMatchTags(hasLinkToObj(link), link)»;
		'''
	}
	 
	//region attribute match call
	
	def static createAttributeMatchCode(List<SPEAttribute> attribute, String type) {	
		attribute.fold("", [left, it|'''«left»«createAttributeMethodCall(it, type)»'''])
	}
	
	def static createAttributeMethodCall(SPEAttribute attribute, String type){		
		 var call = attributeMatchHandler.findFirst[attribute.operation.matches(it.key)]?.value?.apply(attribute)
		 if(call == null){
		 	call = createRegExCondition(attribute, type)
		 }		  
		 call
	}
 			
	def static initAttributeHandler() {
		val maxInt = "Integer.MAX_VALUE"	
		val minInt = "Integer.MIN_VALUE" 		
		
		//code duplication due to readability
		val equal = "^==[\\d\"].*$" -> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue»)''']			
		val smaller = "^<\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue» - 1 ,«maxInt»)''']
		val sEqual = "^<=\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue» ,«maxInt»)''']	
		val bigger = "^>\\d*$" -> [SPEAttribute it |'''.has«getAttrName»(«minInt», «getAttrValue» + 1)'''] 
		val bEqual = "^>=\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«minInt», «getAttrValue»)''']  		
		val nEqual = "^!=.*$"  -> [SPEAttribute it |'''.startNAC().has«getAttrName»(«getAttrValue»).endNAC()''']  
		val setVal = "^:=.*$"  -> [SPEAttribute it |''''''] 			 			
		val inv_bs = "^>\\d*<\\d*$" -> [SPEAttribute it |'''.has«getAttrName»(«it.operation.replace(">", "").replace("<", " + 1, ")» - 1)''']  						 		
		val inv_sb = "^<\\d*>\\d*$" -> 	[SPEAttribute it |'''.has«getAttrName»(«it.operation.replace("<", "").replace(">", " - 1, ")» + 1)'''] 	
			 
		#[equal, bEqual, nEqual, smaller, sEqual, bigger, inv_bs, inv_sb, setVal]		
	}
	
	def static createRegExCondition(SPEAttribute raw, String type) {
		'''.has(new Condition<Object>() {              
            @Override
            public boolean check(Object value) {
                if(value instanceof «type»){
                    boolean matches = ((«type»)value).getName().matches("«raw.attrValue»");
                    return matches;
                }
                return false;   
            }   
        })'''
	}
 	
 	//endregion  	 	 
 	 
 	//region helper
	
	def SPEGroup  getGroup(SPEObject object){
		if(matchState.root.objects.contains(object))
			return matchState.root
		else 
			return matchState.root.subGroups.findFirst[objects.contains(object)]		
	}
			
	def static CharSequence addMatchTags(CharSequence string, IHasMatchTag tag) {
		if(tag.tag == MatchTag.NOT){
			return  string.asNAC
		} 
		
		if(tag.tag == MatchTag.OPTIONAL){		
			return  string.asSubPattern
		}
		 
		return string		
	}  
		 
	def static List<SPEObject> getAllObjects(SPEGroup root) {
		var rootObjects = root.objects.clone;
		if (root.subGroups == null) 
			return rootObjects
		
		var tmp =  root.subGroups.map[objects].flatten.toList 
		tmp.addAll(rootObjects) 
		tmp		
	}	
		
	//endregion	
}

