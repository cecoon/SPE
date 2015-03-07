package org.uniks.spe.editor.features.objects;

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
		this(fp, "Object", "Creates a new Object");
	}
	public SPEObjectCreateFeature(IFeatureProvider fp, String name, String description) {
	    super(fp, name, description);
    }

    @Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) { 
		SPEObject object = createBusinessObject();			
		getDiagram().eResource().getContents().add(object);		
		addGraphicalRepresentation(context, object);
		return new Object[] { object };
	}

    protected SPEObject createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPEObject();
    }
}
