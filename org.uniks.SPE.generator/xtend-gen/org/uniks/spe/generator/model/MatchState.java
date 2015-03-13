package org.uniks.spe.generator.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.SPEGroup;
import model.SPELink;
import model.SPEObject;

@SuppressWarnings("all")
public class MatchState {
  private SPEGroup root;
  
  private List<SPEObject> allObjects;
  
  private Set<SPEObject> matchedObjects = new HashSet<SPEObject>();
  
  private Set<SPELink> matchedLinks = new HashSet<SPELink>();
  
  public boolean isMatched(final SPELink link) {
    return this.matchedLinks.contains(link);
  }
  
  public boolean isMatched(final SPEObject obj) {
    return this.matchedObjects.contains(obj);
  }
  
  public boolean markAsMatched(final SPELink link) {
    return this.matchedLinks.add(link);
  }
  
  public boolean markAsMatched(final SPEObject obj) {
    return this.matchedObjects.add(obj);
  }
  
  public SPEGroup getRoot() {
    return this.root;
  }
  
  public SPEGroup setRoot(final SPEGroup root) {
    return this.root = root;
  }
  
  public List<SPEObject> getAllObjects() {
    return this.allObjects;
  }
  
  public void setAllObjects(final List<SPEObject> allObjects) {
    this.allObjects = allObjects;
  }
  
  public Set<SPEObject> getMatchedObjects() {
    return this.matchedObjects;
  }
  
  public void setMatchedObjects(final Set<SPEObject> matchedObjects) {
    this.matchedObjects = matchedObjects;
  }
  
  public Set<SPELink> getMatchedLinks() {
    return this.matchedLinks;
  }
  
  public void setMatchedLinks(final Set<SPELink> matchedLinks) {
    this.matchedLinks = matchedLinks;
  }
}
