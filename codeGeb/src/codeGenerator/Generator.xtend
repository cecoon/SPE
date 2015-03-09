package codeGenerator

import org.eclipse.xtext.generator.IGenerator
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import model.SPEGroup import model.SPEObject
import java.util.List
import model.SPEAttribute
import java.util.HashSet
import java.util.Set
import model.SPELink

class Generator implements IGenerator {	
	public static String START_OBJECT_NAME = "this"
	
	private SPEGroup root 	
	private Set<SPEObject> matchedObjects = new HashSet<SPEObject>()	
	private Set<SPELink> matchedLinks = new HashSet<SPELink>() 
	
	override doGenerate(Resource input, IFileSystemAccess fsa) { 
		val rootGrps = input.allContents.toIterable.filter(SPEGroup).toList
		if(rootGrps.size != 1){ 
			return		
		}							              
		root = rootGrps.get(0);
			 
		val start = getAllObjects(root).findFirst[it|it.name.equals(START_OBJECT_NAME)]	
		fsa.generateFile('''MatchClass«root.name».java''', generateMatcherCode(start))				 	
	}	 
	  
	def generateMatcherCode(SPEObject start){
		matchedObjects.add(start)
		val type = start.type 
		val varName = varNameForObject(start)	 
							
		val codeForAllObjects = start.outboundLinks.fold("", [
			value, it |'''«value»«createMatchCodeLinks(it, varName)»'''
		])
									 				
		val codeForMissingLinks = root.links.filter[it| ! matchedLinks.contains(it)]
									  .fold("", [value, it |'''«value»«createMatchCodeForMissingLink»'''])	
		return  ''' 
		package matcher;
		
		import model.*; 
		import model.util.*;
		 
		public class MatchClass«root.name» {
			
			public «type»Set findMatch(«type» start){
				«type»Set startSet = new «type»Set().with(start);
				«type»PO «varName» = startSet.has«type»PO()«createAttributeMatchCode(start.attributes)»;
					
				«codeForAllObjects»
				«codeForMissingLinks»
				return «varName».allMatches();
			}
		}  
		''' 	
	}	
   
   def CharSequence createMatchCodeLinks(SPELink link, String fromVarName){    	
   		val target = link.target	
   		if(matchedObjects.contains(target)){
   			return ""
   		} 
   		  	
   		matchedObjects.add(target)
   		matchedLinks.add(link)
   		
   		val varName = varNameForObject(target) 
   		val linkName = link.name.toFirstUpper;
   		val codeForLink = '''
   		«target.type»PO «varName» = «fromVarName».has«linkName»()«createAttributeMatchCode(target.attributes)»;
   		'''   		 
   	 	return target.outboundLinks.fold(codeForLink, [value, it |'''«value»«createMatchCodeLinks(it, varName)»'''])	
   }
   
   def static createMatchCodeForMissingLink(SPELink link){
   		val sourceVarName = varNameForObject(link.source) 
   		val targetVarName = varNameForObject(link.target)
   		var linkName = link.name.toFirstUpper;
   		
   		return '''
   		«sourceVarName».has«linkName»(«targetVarName»);
   		'''
   } 
	
	def static createAttributeMatchCode(List<SPEAttribute> attribute) { 
		return attribute.fold("", [value, it|'''«value».has«it.name.toFirstUpper»(«createAttributeMethodCall»)''']) 
	}
   
   	def static createAttributeMethodCall(SPEAttribute attribute){
   		val opString = attribute.operation   		
   		if(opString.startsWith("==")){
   			return opString.replace("==", "")
   		}   		
   		return ""
   	}	 
   	   
	def static varNameForObject (SPEObject object) {
		return object.name + "_" + object.hashCode
	}   
	
	def static List<SPEObject> getAllObjects (SPEGroup root) {
		val rootObjects = root.objects.clone
		if(root.subGroups != null){
			rootObjects.addAll(root.subGroups.map[it|it.allObjects])
		}		
		return rootObjects
	} 
}  