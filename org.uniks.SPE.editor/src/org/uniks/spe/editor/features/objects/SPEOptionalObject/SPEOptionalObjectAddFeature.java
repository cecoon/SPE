package org.uniks.spe.editor.features.objects.SPEOptionalObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.CommonFeatureColors;
import org.uniks.spe.editor.features.objects.SPEObjectAddFeature;

public class SPEOptionalObjectAddFeature extends SPEObjectAddFeature {

	public SPEOptionalObjectAddFeature(IFeatureProvider fp) {
		super(fp);
		
	   objectForeground = CommonFeatureColors.OPTIONAL_FOREGROUND;
	   objectBackground = CommonFeatureColors.OPTIONAL_BACKGROUND;
	}
	
}
