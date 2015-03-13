package org.uniks.spe.generator.model

import model.SPEObject

class EntryPoint {
	private SPEObject alien
	private SPEObject start
	private CharSequence entrySourceCode 
	
	
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

def CharSequence getEntrySourceCode() {
    return entrySourceCode;
}

def void setEntrySourceCode(CharSequence entrySourceCode) {
    this.entrySourceCode = entrySourceCode;
}
  
}

