package org.uniks.spe.editor.features.objects.SPENotObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.util.ColorConstant; 
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;

public class SPENotObjectAddFeature extends SPEObjectAddFeature {

	public SPENotObjectAddFeature(IFeatureProvider fp) {
		super(fp);
		
		E_CLASS_FOREGROUND = new ColorConstant(227, 75, 75);
		E_CLASS_BACKGROUND = new ColorConstant(235, 145, 145);
	}
}
