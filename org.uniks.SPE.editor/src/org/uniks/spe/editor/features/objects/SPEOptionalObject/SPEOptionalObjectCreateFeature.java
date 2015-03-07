package org.uniks.spe.editor.features.objects.SPEOptionalObject;

import model.ModelFactory;
import model.SPEObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;

public class SPEOptionalObjectCreateFeature extends SPEObjectCreateFeature {

	public SPEOptionalObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Optional Object", "Creates a new Optional Object");
	}

    @Override
    protected SPEObject createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPEOptionalObject();
    }
}
