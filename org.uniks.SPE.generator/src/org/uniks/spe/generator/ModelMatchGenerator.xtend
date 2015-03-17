package org.uniks.spe.generator

import static extension org.uniks.spe.generator.utils.Extentions.*
import static extension org.uniks.spe.generator.utils.CodeSnippets.*
import org.uniks.spe.generator.model.MatchState
import model.SPEObject
import model.SPELink
import model.SPEGroup 
import model.SPEAttribute
import model.MatchTag
import model.IHasMatchTag
import java.util.List
import org.uniks.spe.generator.model.EntryPoint

class ModelMatchGenerator { 	
				
	var static attributeMatchHandler = initAttributeHandler();
	
	private MatchState matchState	
	
	new( MatchState matchState) {
		this.matchState = matchState		
	}	
	 
	//region match code for links to unknown objects 
	
	def generateMatchCodeForNonAlienObjects(SPEObject object) { 
		object.outboundLinks.filter[canBeMatched && canCreatePO]
							.filter[areInSameGroup(object, target)]
							.fold("",[_, it|'''«_»«createMatchingCodeForLinkedObjects(it, object.varName)»'''])
	}	
	
	private def CharSequence createMatchingCodeForLinkedObjects(SPELink link, String fromVarName) {
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
	
	
	//region missing links
	
	def generateCodeForMissingLinks() {
		matchState.root.links.filter[!matchState.isMatched(it) && canBeMatched]
							 .fold("", [_, it|'''«_»«createMatchCodeForALink»'''])
	}
	
	private def createMatchCodeForALink(SPELink link) { 
		matchState.markAsMatched(link);	
		'''
			«link.source.varName»«addMatchTags(hasLinkToObj(link), link)»;
		'''
	}	 	
	
	//endregion
	
	//region subgrpMatch	
		 
	def createObjectsOfSubgroups(){	
		matchState.root.subGroups.fold("", [left, it|'''«left»«createCodeForSubgroupMatching»'''])
	}	

	private def createCodeForSubgroupMatching(SPEGroup group) {
		var alienLinks = findAlienLinks(group)				
		if(alienLinks.size == 0) 
			return '''''' //ignore invalid diagrams
		
		
	 	var entryPoints = alienLinks.map[extractEntryPoint(it, group)]; 
		'''			
			//subgroup
			«matchState.start.varName».start«group.toSDMLibMatchTag»();
			«entryPoints.filter[inbound].fold("", [_, it | '''«_»«matchCodeForEntryPointObjects»'''])»
			«findUnmatchedGroupLinks(group).fold("", [_, it|'''«_»«createMatchCodeForALink»'''])»
			«alienLinks.fold("", [_, it|'''«_»«createMatchCodeForALink»'''])»
			«matchState.start.varName».end«group.toSDMLibMatchTag»();
		'''		 
	} 
	
	def matchCodeForEntryPointObjects(EntryPoint point) {
		if(matchState.isMatched(point.start)) 
			return ''''''
				
		matchState.markAsMatched(point);		
		'''
		«declarePO(point.start)» = «point.alien.varName».has«point.link.Name»()«createAttributeMatchCode(point.start.attributes, point.start.type)»;
		«generateMatchCodeForNonAlienObjects(point.start)»
		'''
	}
	
	private def findAlienLinks(SPEGroup group) {   
	 	var alienLinks = newArrayList()
		for (object : matchState.matchedObjects){
			alienLinks.addAll(object.outboundLinks.filter[canBeMatched && group == it.target.group])			
			alienLinks.addAll(object.inboundLinks.filter[canBeMatched && group == it.source.group])
		}		
		alienLinks	
	}
	
	private def findUnmatchedGroupLinks(SPEGroup group) {  
	 	var grpLinks = newArrayList()
		for (object : matchState.matchedObjects){
			grpLinks.addAll(object.outboundLinks.filter[group == target.group 
													&& group == source.group
													&& !matchState.isMatched(it)])		
		}		
		grpLinks	
	}
	
	private def extractEntryPoint(SPELink link, SPEGroup grp){
		var result = new EntryPoint
		result.inbound = link.target.group == grp; 		
		result.link = link;					
		if(result.inbound){ 
			result.alien = link.source;
			result.start = link.target;	
		}  else { 
			result.alien = link.target;
			result.start = link.source;	
		}				 
		result
	}
	
	//endregion
	 
	 
	//region attribute match call 
	
	def static createAttributeMatchCode(List<SPEAttribute> attribute, String type) {	
		attribute.fold("", [_, it|'''«_»«createAttributeMethodCall(it, type)»'''])
	}
	
	private def static createAttributeMethodCall(SPEAttribute attribute, String type){		
		 var call = attributeMatchHandler.findFirst[attribute.operation.matches(it.key)]?.value?.apply(attribute)
		 if(call == null){
		 	call = createRegExCondition(attribute, type)
		 }		  
		 call
	} 			
	
	private def static createRegExCondition(SPEAttribute raw, String type) 
	'''.has(new Condition<Object>() {
           @Override
           public boolean check(Object value) {
               if(value instanceof «type»){
                   boolean matches = ((«type»)value).getName().matches("«raw.attrValue»");
                   return matches;
               }
               return false;
            }
        })
   '''
	 	
 	//endregion  
 	
 		 	 
 	//region helper 
	
	private def areInSameGroup(SPEObject source, SPEObject target){
		source.group == target.group
	}
	
	private def SPEGroup  getGroup(SPEObject object){
		if(matchState.root.objects.contains(object))
			return matchState.root
						
		return matchState.root.subGroups.findFirst[objects.contains(object)]		
	}
			
	private def static CharSequence addMatchTags(CharSequence string, IHasMatchTag tag) {
		if(tag.tag == MatchTag.NOT) 
			return  string.asNAC	
				
		if(tag.tag == MatchTag.OPTIONAL)
			return  string.asSubPattern	
				 
		return string		
	}  
		
	//endregion	
	
	private def static initAttributeHandler() {
		val maxInt = "Integer.MAX_VALUE"	
		val minInt = "Integer.MIN_VALUE" 		
		
		//code duplication due to readability
		val equal = "^==[\\d\"].*$" -> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue»)''']			
		val smaller = "^<\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue» - 1 ,«maxInt»)''']
		val sEqual = "^<=\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«getAttrValue» ,«maxInt»)''']	
		val bigger = "^>\\d*$" -> [SPEAttribute it |'''.has«getAttrName»(«minInt», «getAttrValue» + 1)'''] 
		val bEqual = "^>=\\d*$"-> [SPEAttribute it |'''.has«getAttrName»(«minInt», «getAttrValue»)''']  		
		val nEqual = "^!=.*$"  -> [SPEAttribute it |'''.startNAC().has«getAttrName»(«getAttrValue»).endNAC()''']  
		val setVal = "^:=.*$"  -> [SPEAttribute it |''''''] 	//ll be handled later		 			
		val inv_bs = "^>\\d*<\\d*$" -> [SPEAttribute it |'''.has«getAttrName»(«it.operation.replace(">", "").replace("<", " + 1, ")» - 1)''']  						 		
		val inv_sb = "^<\\d*>\\d*$" -> 	[SPEAttribute it |'''.has«getAttrName»(«it.operation.replace("<", "").replace(">", " - 1, ")» + 1)'''] 	
			 
		#[equal, bEqual, nEqual, smaller, sEqual, bigger, inv_bs, inv_sb, setVal]		
	}
	
	def getMatchState() {
		return matchState;
	}
	
}