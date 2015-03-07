package org.uniks.spe.editor.features.links.SPEOptionalLink;

 
import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.CommonFeatureColors;
import org.uniks.spe.editor.features.links.SPELinkAddFeature;


public class SPEOptionalLinkAddFeature extends SPELinkAddFeature {

	public SPEOptionalLinkAddFeature(IFeatureProvider fp) {
		super(fp);
		forgroundColor = CommonFeatureColors.OPTIONAL_FOREGROUND;
	}
 
}
