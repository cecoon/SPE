package org.uniks.spe.editor.features.objects.SPENotObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.util.ColorConstant; 
import org.uniks.spe.editor.features.CommonFeatureColors;
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;

public class SPENotObjectAddFeature extends SPEObjectAddFeature {

	public SPENotObjectAddFeature(IFeatureProvider fp) {
		super(fp);
		
		objectForeground = CommonFeatureColors.NOT_FOREGROUND;
		objectBackground = CommonFeatureColors.NOT_BACKGROUND;
	}
}
