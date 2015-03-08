package org.uniks.spe.editor.features.links.optional;

import model.MatchTag;
import model.ModelFactory;
import model.SPELink;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.links.SPELinkCreateFeature;

public class OptionalSPELinkCreateFeature extends SPELinkCreateFeature {

    public OptionalSPELinkCreateFeature(IFeatureProvider fp) {
        super(fp, "Optional Link", "Creates a new optional link between two objects");
    }

    protected SPELink createBusinessObject() {
        SPELink link = ModelFactory.eINSTANCE.createSPELink();
        link.setTag(MatchTag.OPTIONAL);
        return link;
    }
}
