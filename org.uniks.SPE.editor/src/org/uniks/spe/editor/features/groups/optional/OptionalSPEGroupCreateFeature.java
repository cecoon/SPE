package org.uniks.spe.editor.features.groups.optional;

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

public class OptionalSPEGroupCreateFeature extends SPEGroupCreateFeature {

	public OptionalSPEGroupCreateFeature(IFeatureProvider fp) {
	    super(fp, "Not Group", "Creates a new not group");
	}
	 

    @Override
    protected SPEGroup createBusinessObject() {
        SPEGroup grp = ModelFactory.eINSTANCE.createSPEGroup();
        grp.setTag(Tag.NOT);
        return grp;
    }
}
