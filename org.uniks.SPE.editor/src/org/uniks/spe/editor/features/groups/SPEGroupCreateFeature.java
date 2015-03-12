package org.uniks.spe.editor.features.groups;

import model.MatchTag;
import model.ModelFactory;
import model.SPEGroup;
import model.SPEObject;

import org.eclipse.graphiti.features.ICreateFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

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
        Object bo = getBusinessObjectForPictogramElement(context.getTargetContainer());
        if (bo instanceof SPEGroup) {
            SPEGroup group = (SPEGroup) bo;
            if (group.getTag().equals(MatchTag.DEFAULT)) {
                return true;
            }
        }
        
        return false;
    }

	@Override
	public Object[] create(ICreateContext context) {      
	    
	    SPEGroup object = createBusinessObject();	
	    SPEGroup root = getRootGroup(); 
	    root.getSubGroups().add(object);
	    
		addGraphicalRepresentation(context, object);
		return new Object[] { object };
	}

    protected SPEGroup createBusinessObject() {
        return ModelFactory.eINSTANCE.createSPEGroup();
    }
    
    protected SPEGroup getRootGroup() {
        return (SPEGroup) getBusinessObjectForPictogramElement(getDiagram().getLink().getPictogramElement());
    }
    
}
