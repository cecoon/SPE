package org.uniks.spe.editor.features.links.optional;

import model.ModelFactory;
import model.SPELink; 
import model.Tag;

import org.eclipse.graphiti.features.IFeatureProvider; 
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;

public class OptionalSPELinkCreateFeature extends SPELinkCreateFeature {

    public OptionalSPELinkCreateFeature(IFeatureProvider fp) {
        super(fp, "Optional Link", "Creates a new optional link between two objects");
    }

    protected SPELink createBusinessObject() {
        SPELink link = ModelFactory.eINSTANCE.createSPELink();
        link.setTag(Tag.OPTIONAL);
        return link;
    }
}
