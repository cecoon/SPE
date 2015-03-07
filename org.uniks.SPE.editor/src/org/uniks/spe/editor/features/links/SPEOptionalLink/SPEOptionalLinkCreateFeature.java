package org.uniks.spe.editor.features.links.SPEOptionalLink;

import model.ModelFactory;
import model.SPELink; 
import org.eclipse.graphiti.features.IFeatureProvider; 
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;

public class SPEOptionalLinkCreateFeature extends SPELinkCreateFeature {

    public SPEOptionalLinkCreateFeature(IFeatureProvider fp) {
        super(fp, "Optional Link", "Creates a new optional link between two objects");
    }

    protected SPELink createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPEOptionalLink();
    }
}
