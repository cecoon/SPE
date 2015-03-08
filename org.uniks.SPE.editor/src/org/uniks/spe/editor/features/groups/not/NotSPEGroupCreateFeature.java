package org.uniks.spe.editor.features.groups.not;

import model.ModelFactory;
import model.SPEGroup;
import model.SPEObject;
import model.Tag;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.uniks.spe.editor.features.groups.SPEGroupCreateFeature;

public class NotSPEGroupCreateFeature extends SPEGroupCreateFeature {

	public NotSPEGroupCreateFeature(IFeatureProvider fp) {
	    super(fp, "Optional Group", "Creates a new Optional group");
	}

    @Override
    protected SPEGroup createBusinessObject() {
        SPEGroup grp = ModelFactory.eINSTANCE.createSPEGroup();
        grp.setTag(Tag.OPTIONAL);
        return grp;
    }
}
