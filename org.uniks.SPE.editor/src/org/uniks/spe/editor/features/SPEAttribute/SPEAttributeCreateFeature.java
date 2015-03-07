package org.uniks.spe.editor.features.SPEAttribute;

import model.ModelFactory;
import model.SPEAttribute; 
import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class SPEAttributeCreateFeature extends AbstractCreateFeature implements
		ICreateFeature {

	public SPEAttributeCreateFeature(IFeatureProvider fp) {
		super(fp, "Attribute", "Creates a new Attribute");
	}

	@Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) { 
		SPEAttribute attribute = ModelFactory.eINSTANCE.createSPEAttribute();			
		getDiagram().eResource().getContents().add(attribute);		
		addGraphicalRepresentation(context, attribute);
		return new Object[] { attribute };
	}
}
