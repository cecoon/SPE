package org.uniks.spe.generator.model

import model.SPEObject
import model.SPELink

class EntryPoint {
	private SPEObject alien
	private SPEObject start
	private SPELink link
	private boolean inbound 
	
	
def boolean isInbound() {
    return inbound;
}

def void setInbound(boolean inbound) {
    this.inbound = inbound;
}
	
def SPELink getLink() {
    return link;
}

def void setLink(SPELink link) {
    this.link = link;
}	

def SPEObject getAlien() {
    return alien;
}

def void setAlien(SPEObject alien) {
    this.alien = alien;
}

def SPEObject getStart() {
    return start;
}

def void setStart(SPEObject start) {
    this.start = start;
}
 
  
}

