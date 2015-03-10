package codeGenerator

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
	
	private SPEGroup root
	private List<SPEObject> allObjects
	private Set<SPEObject> matchedObjects =  new HashSet<SPEObject>()
	private Set<SPELink> matchedLinks = new HashSet<SPELink>()	
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {
		root = input.allContents.findFirst[it instanceof SPEGroup] as SPEGroup
		if (root != null) {
			allObjects = getAllObjects(root)
			fsa.generateFile('''MatchClass«root.name».java''', generateClassCode())
		} 
	}
	
	def generateClassCode() {				
		val start = allObjects.findFirst[name == START_OBJECT_NAME]	
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
				«generateMatcherCode(start)»
				
			}  
		'''		
	}
	

 
	def generateMatcherCode(SPEObject start) {
		matchedObjects.add(start)
		val type = start.type 
		val varName = varNameForObject(start) 
			val generateCreateLinksCode = generateLinksCode(Operations.CREATE, "Create");
		val generateDeleteLinksCode = generateLinksCode(Operations.DELETE, "Delete");	
	
		
		'''
		public «type»Set findMatch(«type» start){					
			«type»Set startSet = new «type»Set().with(start);
			«type»PO «varName» = startSet.has«type»PO()«createAttributeMatchCode(start.attributes, type)»;
			
			//matching objects 
			« generateMatchCodeForLinksToUnknownObjects(start)»
			
			//matching missing links to known					
			«root.links.filter[!matchedLinks.contains(it) && operation.isNotCreate]
					   .fold("", [left, it|'''«left»«createMatchCodeForMissingLink»'''])»
			
			//update model
			«generateDeleteLinksCode»
			«generateCreateLinksCode»
			«generateAttributesUpdateCode»
			«generateObjectUpdateCode»
			
			return «varName».allMatches();
		}
		''' 
	} 
	
	//region update model
	
	def generateLinksCode(Operations op, String action) {
		root.links.filter[it.operation == op]
			.fold('''''', [left, it| '''
			«left»«varNameForObject(it.source)».start«action»().has«it.Name»(«varNameForObject(it.target)»).end«action»();'''])		
	} 
	
	def generateObjectUpdateCode() { 
		matchedObjects.filter[operation == Operations.DELETE]
		 			   .fold("", [left, it| '''«left»«varNameForObject».destroy();'''])		
	}
	 
		
	def generateAttributesUpdateCode() {
		var result = ''''''	
		for (object : matchedObjects) {
			val varName = varNameForObject(object)	
			var addAttributes = object.attributes.filter[it.operation.matches("^:=.*$")];			
			if(addAttributes.size > 0){		
				var createAttr = addAttributes.fold("",[left, it| '''«left».create«getAttrName»(«getAttrValue»)''' ])				 			
				result += '''«varName».startCreate()«createAttr».endCreate();'''				
			}	
		}
		result
	}
	
	//endregion
	
	//region match code for links to unknown objects
	
	def generateMatchCodeForLinksToUnknownObjects(SPEObject object) {
		val varName = varNameForObject(object);
		object.outboundLinks.filter[tag != MatchTag.NOT && operation.isNotCreate]
							.fold("",[left, it|'''«left»«createMatchingCodeForLinkedObjects(it, varName)»'''])
	}
	

	def CharSequence createMatchingCodeForLinkedObjects(SPELink link, String fromVarName) {
		val target = link.target
		if (matchedObjects.contains(target)) {
			return ""
		}
		matchedObjects.add(target) 
		matchedLinks.add(link)

		val varName = varNameForObject(target) 
			
		var matchExpression = '''.has«link.Name»()«createAttributeMatchCode(target.attributes, target.type)»'''
		matchExpression = addMatchTags(matchExpression, target)
					
		
		'''
			«target.type»PO «varName» = «fromVarName»«matchExpression»;
			«generateMatchCodeForLinksToUnknownObjects(target)»
		'''
	}

	//endregion		
	
	def static createMatchCodeForMissingLink(SPELink link) {		
		val sourceVarName = varNameForObject(link.source)
		val targetVarName = varNameForObject(link.target)
		var linkName = link.name.toFirstUpper;

		var matchCode = '''.has«linkName»(«targetVarName»)'''
		matchCode = addMatchTags(matchCode, link)
	
		'''
			«sourceVarName»«matchCode»;
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
	
 	def static getAttrValue(SPEAttribute raw){ 
 		raw.operation.replaceAll("[:!=<>]", "")
 	}   
 	
 	def static getAttrName(SPEAttribute raw){  
 		raw.name.toFirstUpper
 	}   
 	
 	//endregion 
 	 
	//region helper
		
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
	
	def isNop(Operations operations) {
		operations == Operations.NOP
	}
	  
	def isNotCreate(Operations operations) {
		operations != Operations.CREATE
	}
	
	def static varNameForObject(SPEObject object){ 
		 object.name + "PO"
	} 
	
	def static List<SPEObject> getAllObjects(SPEGroup root) {
		val rootObjects = root.objects.clone
		if (root.subGroups != null) {
			rootObjects.addAll(root.subGroups.map[it|it.allObjects])
		}
		rootObjects
	} 
	
	//endregion
}
