package org.uniks.spe.editor.features.SPEObject;

import model.ModelFactory;
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class SPEObjectCreateFeature extends AbstractCreateFeature implements
		ICreateFeature {

	public SPEObjectCreateFeature(IFeatureProvider fp) {
		super(fp, "Object", "Creates a new Object");
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
