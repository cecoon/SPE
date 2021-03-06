package org.uniks.spe.generator

import model.Operations
import static extension org.uniks.spe.generator.utils.Extentions.*
import static extension org.uniks.spe.generator.utils.CodeSnippets.*

import model.SPEObject
import java.util.List
import model.SPEGroup
import org.uniks.spe.generator.model.MatchState

class ModelUpdateGenerator {
	
	def generateModelUpdateCode(MatchState matchState) {
		val generateCreateLinksCode = generateLinksCode(Operations.CREATE, "Create", matchState.root);
		val generateDeleteLinksCode = generateLinksCode(Operations.DELETE, "Destroy", matchState.root);	
	
		val allObjects = matchState.allObjects; 
		
		'''
		«generateDeleteLinksCode»
		«generateDeleteObjectCode(allObjects)»
		«generateCreateObjectCode(allObjects)»
		«generateCreateLinksCode»
		«generateAttributesUpdateCode(allObjects)»
		''' 
	}  
	 
	private def static generateLinksCode(Operations op, String action, SPEGroup root) {
		root.links.filter[it.operation == op]
				  .fold('''''', [_, it| '''«_»«varName(it.source)».start«action»()«hasLinkToObj».end«action»();'''])		
	}  
	
	private def static generateDeleteObjectCode( List<SPEObject> allObjects) { 
		allObjects.filter[operation == Operations.DELETE]
		 		  .fold("", [_, it| '''
		 		  «_»«varName».destroy();'''])		 
	} 
	 			 
	private def static generateCreateObjectCode(List<SPEObject> allObjects) { 
		allObjects.filter[operation == Operations.CREATE] 
				  .fold("", [_, it| '''
				  «_»«declarePO» = «createPO»;'''])		
	}	
	  
	private def static generateAttributesUpdateCode(List<SPEObject> allObjects) {
		var result = ''''''	
		for (object : allObjects.filter[operation != Operations.DELETE]) {
			val varName = varName(object)	
			var addAttributes = object.attributes.filter[it.operation.matches("^:=.*$")];			
			if(addAttributes.size > 0){		
				var createAttr = addAttributes.fold("",[_, it| '''«_».create«getAttrName»(«getAttrValue»)''' ])				 			
				result += '''«varName».startCreate()«createAttr».endCreate();'''				
			}	
		}
		result 
	}
}