package org.uniks.spe.editor.features.objects;

import model.SPEGroup;
import model.SPEObject;
import model.Tag;

import org.eclipse.graphiti.features.IAddFeature;
import org.eclipse.graphiti.features.IDirectEditingInfo;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.impl.AbstractAddFeature;
import org.eclipse.graphiti.features.impl.DefaultMoveShapeFeature;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.RoundedRectangle;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.Orientation;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.services.Graphiti;
import org.eclipse.graphiti.services.IGaService;
import org.eclipse.graphiti.services.IPeCreateService; 
import org.eclipse.graphiti.util.IColorConstant;
import org.uniks.spe.editor.features.CommonFeatureStyles;

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
