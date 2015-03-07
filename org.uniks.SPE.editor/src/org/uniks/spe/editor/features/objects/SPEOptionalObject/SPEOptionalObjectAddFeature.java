package org.uniks.spe.editor.features.objects.SPEOptionalObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.util.ColorConstant;  
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;

public class SPEOptionalObjectAddFeature extends SPEObjectAddFeature {

	public SPEOptionalObjectAddFeature(IFeatureProvider fp) {
		super(fp);
		
	   E_CLASS_FOREGROUND = new ColorConstant(164, 164, 164);
	   E_CLASS_BACKGROUND = new ColorConstant(210, 210, 210);
	}
	
	
	
}
