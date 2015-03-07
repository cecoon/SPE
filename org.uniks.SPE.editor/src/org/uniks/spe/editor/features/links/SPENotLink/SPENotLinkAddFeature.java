package org.uniks.spe.editor.features.links.SPENotLink;
 
import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.CommonFeatureColors;
import org.uniks.spe.editor.features.links.SPELinkAddFeature;

public class SPENotLinkAddFeature extends SPELinkAddFeature {

    public SPENotLinkAddFeature(IFeatureProvider fp) {
        super(fp);
        forgroundColor = CommonFeatureColors.NOT_FOREGROUND;
    } 
}
