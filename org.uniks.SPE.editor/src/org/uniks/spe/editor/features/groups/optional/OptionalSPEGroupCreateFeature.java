package org.uniks.spe.editor.features.groups.optional;

import model.MatchTag;
import model.ModelFactory;
import model.SPEGroup;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.groups.SPEGroupCreateFeature;

public class OptionalSPEGroupCreateFeature extends SPEGroupCreateFeature {

	public OptionalSPEGroupCreateFeature(IFeatureProvider fp) {
	    super(fp, "Not Group", "Creates a new not group");
	}
	 

    @Override
    protected SPEGroup createBusinessObject() {
        SPEGroup grp = ModelFactory.eINSTANCE.createSPEGroup();
        grp.setTag(MatchTag.NOT);
        return grp;
    }
}
