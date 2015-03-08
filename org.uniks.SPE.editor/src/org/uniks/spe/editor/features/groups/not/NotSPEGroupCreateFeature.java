package org.uniks.spe.editor.features.groups.not;

import model.MatchTag;
import model.ModelFactory;
import model.SPEGroup;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.groups.SPEGroupCreateFeature;

public class NotSPEGroupCreateFeature extends SPEGroupCreateFeature {

	public NotSPEGroupCreateFeature(IFeatureProvider fp) {
	    super(fp, "Optional Group", "Creates a new Optional group");
	}

    @Override
    protected SPEGroup createBusinessObject() {
        SPEGroup grp = ModelFactory.eINSTANCE.createSPEGroup();
        grp.setTag(MatchTag.OPTIONAL);
        return grp;
    }
}
