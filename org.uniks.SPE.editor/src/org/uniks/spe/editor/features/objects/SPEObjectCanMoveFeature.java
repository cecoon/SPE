package org.uniks.spe.editor.features.objects;

import model.SPEGroup;
import model.SPEObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;

public class SPEObjectCanMoveFeature extends DefaultMoveShapeFeature {

    public SPEObjectCanMoveFeature(IFeatureProvider fp) {
        super(fp);
    }   
    
    @Override
    public boolean canMoveShape(IMoveShapeContext context) {
        Object newGrp = getBusinessObjectForPictogramElement(context.getTargetContainer());
        Object oldGrp = getBusinessObjectForPictogramElement(context.getSourceContainer());
        if (newGrp instanceof SPEGroup && oldGrp instanceof SPEGroup) {
            return true;
        }

        return super.canMoveShape(context);
    }
    
    @Override
    protected void postMoveShape(IMoveShapeContext context) {
        SPEObject objectToMove = (SPEObject) getBusinessObjectForPictogramElement(context.getShape());
        SPEGroup newGroup = (SPEGroup) getBusinessObjectForPictogramElement(context.getTargetContainer());
        SPEGroup oldGroup = (SPEGroup) getBusinessObjectForPictogramElement(context.getSourceContainer());
        
        if( ! newGroup.equals(oldGroup)){
            oldGroup.getObjects().remove(objectToMove);
            newGroup.getObjects().add(objectToMove);
            objectToMove.setTag(newGroup.getTag());  
        }
        super.postMoveShape(context);
    }
}
