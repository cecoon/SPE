package org.uniks.spe.editor.features.objects.not;

import model.MatchTag;
import model.ModelFactory;
import model.SPEObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.uniks.spe.editor.features.objects.SPEObjectCreateFeature;

public class NotSPEObjectCreateFeature extends SPEObjectCreateFeature {

	public NotSPEObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Object", "Creates a new not Object");
	}
 
	@Override
	protected SPEObject createBusinessObject() {
        SPEObject object = ModelFactory.eINSTANCE.createSPEObject();
        object.setTag(MatchTag.NOT);
        return object;
	}

 
}
