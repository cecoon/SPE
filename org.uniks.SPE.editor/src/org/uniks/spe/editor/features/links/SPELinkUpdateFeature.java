package org.uniks.spe.editor.features.links;

import model.SPEAttribute;
import model.SPELink;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ConnectionDecorator;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.util.IColorConstant;

public class SPELinkUpdateFeature extends AbstractUpdateFeature {

    public SPELinkUpdateFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public boolean canUpdate(IUpdateContext context) {
        return getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof SPELink;
    }

    @Override
    public IReason updateNeeded(IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        
        String textItShouldHave = getTextItShouldHave(pictogramElement);         
        String textItHas =  getTextItHas(pictogramElement);
        
        if(textItShouldHave.equals(textItHas)) {
            return Reason.createFalseReason();    
        }
      
        return Reason.createTrueReason("Element is out of date");        
    }

    protected String getTextItHas(PictogramElement pictogramElement) {
     
        return "";
    }

    protected String getTextItShouldHave(PictogramElement pictogramElement) {
        SPELink businessObject = (SPELink) getBusinessObjectForPictogramElement(pictogramElement);           
        return businessObject.getName();
    }

    @Override
    public boolean update(IUpdateContext context) {
        // retrieve name from business model
        PictogramElement pictogramElement = context.getPictogramElement();
        String newText = getTextItShouldHave(pictogramElement);
 
        // Set name in pictogram model
        ContainerShape cs = (ContainerShape) pictogramElement;
        for (Shape shape : cs.getChildren()) {
            if (shape.getGraphicsAlgorithm() instanceof Text) {
                Text text = (Text) shape.getGraphicsAlgorithm();
                text.setValue(newText);
                return true;
            }
        }
 
        return false;
    }

}
