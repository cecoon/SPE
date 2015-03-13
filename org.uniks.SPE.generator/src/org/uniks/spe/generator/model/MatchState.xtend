package org.uniks.spe.generator.model

import model.SPEGroup
import java.util.List
import java.util.Set
import model.SPELink
import model.SPEObject
import java.util.HashSet

class MatchState { 
	private SPEGroup root
	private List<SPEObject> allObjects
	private Set<SPEObject> matchedObjects =  new HashSet<SPEObject>()
	private Set<SPELink> matchedLinks = new HashSet<SPELink>()
	
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
	
	
	
	
	
	// region getter setter
	
def SPEGroup getRoot() {
    	return root;
}

def setRoot(SPEGroup root) {
    this.root = root;
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