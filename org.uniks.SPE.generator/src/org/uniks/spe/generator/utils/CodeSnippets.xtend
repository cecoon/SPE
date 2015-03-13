package org.uniks.spe.generator.utils

import model.SPEObject
import static extension org.uniks.spe.generator.utils.Extentions.*
import model.SPELink

public final class CodeSnippets {
	def static CharSequence declarePO(SPEObject obj) 
		'''«obj.type»PO «obj.varName»'''
	   
	def static CharSequence createPO(SPEObject obj) 
		'''new «obj.type»Set().with(new «obj.type»()).has«obj.type»PO()'''
		
	def static CharSequence hasLinkToObj(SPELink link) 
		'''.has«link.Name»(«link.target.varName»)'''
}