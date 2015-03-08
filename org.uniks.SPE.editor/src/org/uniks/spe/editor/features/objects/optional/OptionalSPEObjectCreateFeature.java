package org.uniks.spe.editor.features.objects.optional;

import model.ModelFactory;
import model.SPEObject;
import model.Tag;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;

public class OptionalSPEObjectCreateFeature extends SPEObjectCreateFeature {

	public OptionalSPEObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Optional Object", "Creates a new Optional Object");
	}

    @Override
    protected SPEObject createBusinessObject() {
        SPEObject createSPEObject = ModelFactory.eINSTANCE.createSPEObject();
        createSPEObject.setTag(Tag.OPTIONAL);
        return createSPEObject;
    }
}
