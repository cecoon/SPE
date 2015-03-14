package org.uniks.spe.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator
import static extension org.uniks.spe.generator.utils.CodeSnippets.*
import static extension org.uniks.spe.generator.utils.Extentions.*
import org.uniks.spe.generator.model.MatchState

class Generator implements IGenerator { 
	val ModelUpdateGenerator modelUpdater = new ModelUpdateGenerator
	
	override doGenerate(Resource input, IFileSystemAccess fsa) {
		val matchState = new MatchState();		
		matchState.init(input);	 	
		
		if (matchState.isValid) { 
			fsa.generateFile('''MatchClass«matchState.root.name».java''', generateClassCode(matchState))
		}	
	}  		   
	  
	def generateClassCode(MatchState matchState) {	
		var modelPackage = matchState.root.model;
		if(modelPackage == null || modelPackage.isEmpty){
			modelPackage = "model"
		}				
		
		'''  
			package matcher; 
			
			import «modelPackage».*; 
			import «modelPackage».util.*;	
			import de.uniks.networkparser.logic.Condition;
			
			@SuppressWarnings("all")
			/**
			* generated Matchclass for «matchState.root.name».spe diagram.
			*/
			public class MatchClass«matchState.root.name» {
				
				/**
				* matches and applies the in «matchState.root.name».spe action to a given start object.
				* @returns true if match was successfull
				*/
				«generateMethod(matchState)»
			}
		'''		
	}
	
	def generateMethod(MatchState matchState) {				 
		val matcher = new ModelMatchGenerator(matchState)
		
		val start = matchState.start 	 
		val type = start.type 	 
		matchState.markAsMatched(start)	
		
		''' 
		public boolean execute(«type» start){
			//grep start point
			«type»Set startSet = new «type»Set().with(start);
			«declarePO(start)» = startSet.has«type»PO()«ModelMatchGenerator.createAttributeMatchCode(start.attributes, type)»;
			
			//matching objects of root grp
			«matcher.generateMatchCodeForNonAlienObjects(start)»
			
			//match objects of subgroups 
			«matcher.createObjectsOfSubgroups»
			
			//matching missing links to known
			«matcher.generateCodeForMissingLinks»
			
			//update model 
			«modelUpdater.generateModelUpdateCode(matcher.matchState)»
		
			return ! «start.varName».allMatches().isEmpty();
		}
		'''		
	}
			
}
	 

