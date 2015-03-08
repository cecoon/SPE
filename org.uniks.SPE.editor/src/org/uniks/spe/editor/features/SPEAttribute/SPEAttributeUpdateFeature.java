package org.uniks.spe.editor.features.SPEAttribute;

import model.SPEAttribute;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class SPEAttributeUpdateFeature extends AbstractUpdateFeature {

    public SPEAttributeUpdateFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public boolean canUpdate(IUpdateContext context) {
        return getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof SPEAttribute 
                && context.getPictogramElement() instanceof ContainerShape;
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
        ContainerShape cs = (ContainerShape) pictogramElement;
        for (Shape shape : cs.getChildren()) {
            if (shape.getGraphicsAlgorithm() instanceof Text) {
                Text text = (Text) shape.getGraphicsAlgorithm();
                return text.getValue();
            }
        }
        return "";
    }

    protected String getTextItShouldHave(PictogramElement pictogramElement) {
        SPEAttribute businessObject = (SPEAttribute) getBusinessObjectForPictogramElement(pictogramElement);           
        return businessObject.getName() + " " + businessObject.getOperation();
    }

    @Override
    public boolean update(IUpdateContext context) {
        // retrieve name from business model
        PictogramElement pictogramElement = context.getPictogramElement();
        String newText = getTextItShouldHave(pictogramElement);
 
        // Set name in pictogram model
        ContainerShape cs = (ContainerShape) pictogramElement;
        cs.getChildren().stream()
            .filter(it -> it.getGraphicsAlgorithm() instanceof Text)
            .map(it -> (Text) it.getGraphicsAlgorithm())
            .forEach(it -> it.setValue(newText));
     
        return false;
    }

}
