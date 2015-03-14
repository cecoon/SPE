package org.uniks.spe.generator.utils

import model.SPELink
import model.Operations
import model.SPEObject
import java.util.List
import model.MatchTag
import model.SPEGroup
import model.SPEAttribute

public final class Extentions {	 
	
	def static getIsNot(MatchTag tag) {
		tag == MatchTag.NOT
	}
		
	def static String Name(SPELink link) {
		link.name.toFirstUpper
	}	
	def static getAttrValue(SPEAttribute raw){ 
 		raw.operation.replaceAll("[:!=<>]", "")
 	}   
 	
 	def static getAttrName(SPEAttribute raw){  
 		raw.name.toFirstUpper
 	}   
 	
	def static toSDMLibMatchTag(SPEGroup group) {
		if(group.tag == MatchTag.NOT)
			return "NAC"
		if(group.tag == MatchTag.OPTIONAL)
			return "SubPattern"		
		return "WTF?"		
	}
	 
	def static CharSequence asSubPattern(CharSequence value) 
	'''.startSubPattern()«value».endSubPattern()'''	
	
	def static CharSequence asNAC(CharSequence value)
	'''.startNAC()«value».endNAC()'''
	
	def static isNop(Operations operations) {
		operations == Operations.NOP
	}
	  
	def static isNotCreate(Operations operations) {
		operations != Operations.CREATE
	}
	
	def static varName(SPEObject object){ 
		 object.name + "PO"
	}  	
}