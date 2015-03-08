package org.uniks.spe.editor.features.groups;

import model.ModelFactory;
import model.SPEGroup;
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

public class SPEGroupCreateFeature extends AbstractCreateFeature implements
		ICreateFeature { 

	public SPEGroupCreateFeature(IFeatureProvider fp) {
		this(fp, "Group", "Creates a new Group");
	}
	public SPEGroupCreateFeature(IFeatureProvider fp, String name, String description) {
	    super(fp, name, description);
    }

    @Override
	public boolean canCreate(ICreateContext context) {
		return context.getTargetContainer() instanceof Diagram;
	}

	@Override
	public Object[] create(ICreateContext context) { 
	    SPEGroup object = createBusinessObject();			
		getDiagram().eResource().getContents().add(object);		
		addGraphicalRepresentation(context, object);
        getFeatureProvider().getDirectEditingInfo().setActive(true);
		return new Object[] { object };
	}

    protected SPEGroup createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPEGroup();
    }
}
