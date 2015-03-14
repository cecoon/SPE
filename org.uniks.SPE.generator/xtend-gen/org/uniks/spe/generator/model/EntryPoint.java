package org.uniks.spe.generator.model;

import model.SPELink;
import model.SPEObject;

@SuppressWarnings("all")
public class EntryPoint {
  private SPEObject alien;
  
  private SPEObject start;
  
  private SPELink link;
  
  private boolean inbound;
  
  public boolean isInbound() {
    return this.inbound;
  }
  
  public void setInbound(final boolean inbound) {
    this.inbound = inbound;
  }
  
  public SPELink getLink() {
    return this.link;
  }
  
  public void setLink(final SPELink link) {
    this.link = link;
  }
  
  public SPEObject getAlien() {
    return this.alien;
  }
  
  public void setAlien(final SPEObject alien) {
    this.alien = alien;
  }
  
  public SPEObject getStart() {
    return this.start;
  }
  
  public void setStart(final SPEObject start) {
    this.start = start;
  }
}
