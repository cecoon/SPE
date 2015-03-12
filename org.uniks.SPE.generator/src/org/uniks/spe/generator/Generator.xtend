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
import model.Operations

class Generator implements IGenerator {
	val START_OBJECT_NAME = "this"		
	val static attributeMatchHandler = initAttributeHandler();
	
	private Set<SPEObject> matchedObjects =  new HashSet<SPEObject>()
	private Set<SPELink> matchedLinks = new HashSet<SPELink>()	
	
	var startPO = "";	
	private SPEGroup root
	private List<SPEObject> allObjects
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {				 	
		root = input.allContents.findFirst[it instanceof SPEGroup] as SPEGroup
		if (root != null) {
			allObjects = getAllObjects(root)
			fsa.generateFile('''MatchClass«root.name».java''', generateClassCode())
		}  
	}  		 
	  
	def generateClassCode() {				
		val start = allObjects.findFirst[name == START_OBJECT_NAME]	
		
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
			public class MatchClass«root.name» {
				
				/**
				* finds a match from a given start 
				*/
				«matchCode»
				
			}
		'''		
	} 
	 
	def generateMatcherCode(SPEObject start) {
		matchedObjects.add(start)
		val type = start.type 
		startPO = start.varName		
		'''
		public «type»Set findMatch(«type» start){					
			«type»Set startSet = new «type»Set().with(start);
			«type»PO «startPO» = startSet.has«type»PO()«createAttributeMatchCode(start.attributes, type)»;
			
			//matching objects of root grp
			«generateMatchCodeForNonAlienObjects(start)» 
			
			//match objects of subgroups
			«root.subGroups.fold("", [left, it|'''«left»«createCodeForSubgroupMatching»'''])»
			
			
			//matching missing links to known					
			«root.links.filter[!matchedLinks.contains(it) && operation.isNotCreate]
					   .fold("", [left, it|'''«left»«createMatchCodeForMissingLink»'''])»
			
			//update model
			«generateModelUpdateCode»
			
			return «startPO».allMatches();
		} 
		''' 
	}
	
	//region match code for links to unknown objects
	
	def generateMatchCodeForNonAlienObjects(SPEObject object) {
		val varName = varName(object);
		val groupOfObject = object.group
		object.outboundLinks.filter[tag != MatchTag.NOT && operation.isNotCreate && groupOfObject == it.target.group]
							.fold("",[left, it|'''«left»«createMatchingCodeForLinkedObjects(it, varName)»'''])
	}
	
		
	def CharSequence createMatchingCodeForLinkedObjects(SPELink link, String fromVarName) {
		val target = link.target
		if (matchedObjects.contains(target)) {
			return ""
		}
		matchedObjects.add(target) 
		matchedLinks.add(link) 
			
		var matchExpression = '''.has«link.Name»()«createAttributeMatchCode(target.attributes, target.type)»'''
		matchExpression = addMatchTags(matchExpression, target)					
		
		'''
			«target.type»PO «target.varName» = «fromVarName»«matchExpression»;
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
		var alienLinks = findAlienLinks(matchedObjects, group)				
		if(alienLinks.size == 0) return '''''' //ignore invalid diagrams
		
		var link = alienLinks.get(0);
	 	var entryPoint = extractEntryPoint(link, group);
		matchedLinks.add(link);
		alienLinks.remove(link)
		   
		'''
			«entryPoint.alien.varName».start«group.toSDMLibMatchTag»();
			«entryPoint.entrySourceCode»
			«generateMatchCodeForNonAlienObjects(entryPoint.start)»			
			«alienLinks.fold("", [left, it|'''«left»«createMatchCodeForMissingLink»'''])»			
			«entryPoint.alien.varName».end«group.toSDMLibMatchTag»();
		'''		  
	}
	
	def findAlienLinks(Set<SPEObject> objects, SPEGroup group) {  
	 	var alienLinks = newArrayList()
		for (object : objects){
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
			«type»PO «result.start.varName» = «result.alien.varName».has«link.Name»()«createAttributeMatchCode(attr, type)»;
			'''				
						
		}  else { //is outboundLink	result.alien = link.source;
			result.alien = link.target;
			result.start = link.source;
						
			var type = result.start.type;			
			var attr = result.start.attributes;
			
			result.entrySourceCode = '''				
			«type»PO «result.start.varName» = new «type»Set().with(new «type»()).has«type»PO()«createAttributeMatchCode(attr, type)»;
			«result.start.varName».has«link.Name»(«result.alien.varName»); 
			'''	
		}
		
		return result
	}
	
	//endregion
	
	def createMatchCodeForMissingLink(SPELink link) { 
		var matchCode = '''.has«link.Name»(«link.target.varName»)'''
		matchCode = addMatchTags(matchCode, link)
		matchedLinks.add(link);	
		'''
			«link.source.varName»«matchCode»;
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
 	
 	 
 	 //region update model
	
	def generateModelUpdateCode() {
		val generateCreateLinksCode = generateLinksCode(Operations.CREATE, "Create");
		val generateDeleteLinksCode = generateLinksCode(Operations.DELETE, "Destroy");	
	
		'''
		«generateCreateObjectCode»
		«generateCreateLinksCode»
		«generateDeleteLinksCode»
		«generateAttributesUpdateCode»
		«generateDeleteObjectCode»
		'''
	}
	
	def generateLinksCode(Operations op, String action) {
		root.links.filter[it.operation == op]
			.fold('''''', [left, it| '''
			«left»«varName(it.source)».start«action»().has«it.Name»(«varName(it.target)»).end«action»();'''])		
	}  
	
	def generateDeleteObjectCode() { 
		matchedObjects.filter[operation == Operations.DELETE]
		 			   .fold("", [left, it| '''«left»«varName».destroy();'''])		
	} 
	 			 
	def generateCreateObjectCode() { 
		allObjects.filter[operation == Operations.CREATE] 
				  .fold("", [left, it| '''«left»«type»PO «varName» = new «type»Set().with(new «type»()).has«type»PO();'''])		
	}	
	  
	def generateAttributesUpdateCode() {
		var result = ''''''	
		for (object : allObjects) {
			val varName = varName(object)	
			var addAttributes = object.attributes.filter[it.operation.matches("^:=.*$")];			
			if(addAttributes.size > 0){		
				var createAttr = addAttributes.fold("",[left, it| '''«left».create«getAttrName»(«getAttrValue»)''' ])				 			
				result += '''«varName».startCreate()«createAttr».endCreate();'''				
			}	
		}
		result 
	}
	
	//endregion
 	 
 	 //region helper
	
	def SPEGroup  getGroup(SPEObject object){
		if(root.objects.contains(object))
			return root
		else 
			return root.subGroups.findFirst[objects.contains(object)]		
	}
	
	def static getAttrValue(SPEAttribute raw){ 
 		raw.operation.replaceAll("[:!=<>]", "")
 	}   
 	
 	def static getAttrName(SPEAttribute raw){  
 		raw.name.toFirstUpper
 	}   
 	
	def static toSDMLibMatchTag(SPEGroup group) {
		if(group.tag == MatchTag.NOT)
			return "NAC"
		if(group.tag == MatchTag.OPTIONAL)
			return "SubPattern"		
		return "WTF?"		
	}
		
	def static String addMatchTags(String string, IHasMatchTag tag) {
		if(tag.tag == MatchTag.NOT){
			return  string.asNAC
		} 
		
		if(tag.tag == MatchTag.OPTIONAL){		
			return  string.asSubPattern
		}
		
		return string		
	} 
	
	def static String Name(SPELink link) {
		link.name.toFirstUpper
	}	
	
	def static String asSubPattern(String value) 
	'''.startSubPattern()«value».endSubPattern()'''	
	
	def static String asNAC(String value)
	'''.startNAC()«value».endNAC()'''
	
	def static isNop(Operations operations) {
		operations == Operations.NOP
	}
	  
	def static isNotCreate(Operations operations) {
		operations != Operations.CREATE
	}
	
	def static varName(SPEObject object){ 
		 object.name + "PO"
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

