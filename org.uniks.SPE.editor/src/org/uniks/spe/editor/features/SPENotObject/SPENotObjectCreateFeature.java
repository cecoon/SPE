package org.uniks.spe.editor.features.SPENotObject;

import model.ModelFactory;
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class SPENotObjectCreateFeature extends AbstractCreateFeature implements
		ICreateFeature {

	public SPENotObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Not Object", "Creates a new not Object");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) { 
		SPEObject object = ModelFactory.eINSTANCE.createSPEObject();			
		getDiagram().eResource().getContents().add(object);		
		addGraphicalRepresentation(context, object);
		return new Object[] { object };
	}
}
