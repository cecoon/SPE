package org.uniks.spe.generator.model

import model.SPEGroup
import java.util.List
import java.util.Set
import model.SPELink
import model.SPEObject
import java.util.HashSet
import org.eclipse.emf.ecore.resource.Resource

class MatchState { 	
	val START_OBJECT_NAME = "this"		
		
	private SPEObject startObject
	private SPEGroup root
	private List<SPEObject> allObjects
	private Set<SPEObject> matchedObjects =  new HashSet<SPEObject>()
	private Set<SPELink> matchedLinks = new HashSet<SPELink>()
	
	def init(Resource resource) {		
		root = resource.allContents.findFirst[it instanceof SPEGroup] as SPEGroup
		if (root == null){
			throw new Exception("Diagram doesnt contain a 'this'-object.") 
		}
			 
		allObjects = getAllObjects(root)	
		startObject = allObjects.findFirst[name == START_OBJECT_NAME]	
	}
	
	def isValid() {
		return root != null && startObject != null
	}		
	
	def isMatched(SPELink link) {
		return matchedLinks.contains(link)
	}
	
	def isMatched(SPEObject obj) {
		return matchedObjects.contains(obj)
	}
	
	def markAsMatched(SPELink link) {
		matchedLinks.add(link);
	}
	
	def markAsMatched(SPEObject obj) {
		matchedObjects.add(obj); 
	}
	
	def markAsMatched(EntryPoint point) {
		markAsMatched(point.start)		
		markAsMatched(point.link)
	}
			 
	def static List<SPEObject> getAllObjects(SPEGroup root) {
		var rootObjects = root.objects.clone;
		if (root.subGroups == null) 
			return rootObjects
		
		var tmp =  root.subGroups.map[objects].flatten.toList 
		tmp.addAll(rootObjects) 
		tmp		
	}	
	
	// region getter setter
	 
	def SPEGroup getRoot() {
    	return root;
	} 

	def SPEObject getStart() {
    	return startObject;
	} 



	def List<SPEObject> getAllObjects() {
	    return allObjects;
	}

	def void setAllObjects(List<SPEObject> allObjects) {
	    this.allObjects = allObjects;
	}

	def Set<SPEObject> getMatchedObjects() {
	    return matchedObjects;
	}

	def void setMatchedObjects(Set<SPEObject> matchedObjects) {
	    this.matchedObjects = matchedObjects;
	}

	def Set<SPELink> getMatchedLinks() {
	    return matchedLinks;
	}

	def void setMatchedLinks(Set<SPELink> matchedLinks) {
	    this.matchedLinks = matchedLinks;
	}
  
  //endregion
	
}