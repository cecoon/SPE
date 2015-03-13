package org.uniks.spe.generator.model;

import model.SPEObject;

@SuppressWarnings("all")
public class EntryPoint {
  private SPEObject alien;
  
  private SPEObject start;
  
  private CharSequence entrySourceCode;
  
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
  
  public CharSequence getEntrySourceCode() {
    return this.entrySourceCode;
  }
  
  public void setEntrySourceCode(final CharSequence entrySourceCode) {
    this.entrySourceCode = entrySourceCode;
  }
}
