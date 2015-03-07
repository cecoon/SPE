package org.uniks.spe.editor.features.objects.SPENotObject;

import model.ModelFactory;
import model.SPEObject;

import org.eclipse.graphiti.features.IFeatureProvider;  
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;

public class SPENotObjectCreateFeature extends SPEObjectCreateFeature {

	public SPENotObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Object", "Creates a new not Object");
	}
 
	@Override
	protected SPEObject createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPENotObject();
	}

 
}
