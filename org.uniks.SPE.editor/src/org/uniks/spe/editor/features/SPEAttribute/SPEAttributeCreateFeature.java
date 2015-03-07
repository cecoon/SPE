package org.uniks.spe.editor.features.SPEAttribute;

import model.ModelFactory;
import model.SPEAttribute; 
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature; 

public class SPEAttributeCreateFeature extends AbstractCreateFeature implements
		ICreateFeature {

	public SPEAttributeCreateFeature(IFeatureProvider fp) {
		super(fp, "Attribute", "Creates a new Attribute");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
        return getBusinessObjectForPictogramElement(context.getTargetContainer()) instanceof SPEObject;
	}

	@Override
	public Object[] create(ICreateContext context) { 	    
		SPEAttribute attribute = ModelFactory.eINSTANCE.createSPEAttribute();
		
		SPEObject tgtObject = (SPEObject) getBusinessObjectForPictogramElement(context.getTargetContainer());
		tgtObject.getAttributes().add(attribute);
		
		addGraphicalRepresentation(context, attribute);		
	    getFeatureProvider().getDirectEditingInfo().setActive(true);		
		return new Object[] { attribute };
	}
}
