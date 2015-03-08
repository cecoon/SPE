package org.uniks.spe.editor.features.objects;
 
import java.util.Optional;

import model.MatchTag;
import model.SPEObject;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Polyline;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.algorithms.styles.Color;
import org.eclipse.graphiti.mm.algorithms.styles.LineStyle;
import org.eclipse.graphiti.mm.algorithms.styles.TextStyleRegion;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.uniks.spe.editor.features.CommonFeatureStyles;

public class SPEObjectUpdateFeature extends AbstractUpdateFeature {

    public SPEObjectUpdateFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public boolean canUpdate(IUpdateContext context) {
        return getBusinessObjectForPictogramElement(context.getPictogramElement()) instanceof SPEObject 
                && context.getPictogramElement() instanceof ContainerShape;
    }

    @Override
    public IReason updateNeeded(IUpdateContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        SPEObject object = (SPEObject) getBusinessObjectForPictogramElement(pictogramElement);
        
        String textItShouldHave = SPEObjectAddFeature.createHeaderTextOfObject(object);     
        String textItHas =  getTextItHas(pictogramElement);
        
        Color foregoundIsShouldHave = manageColor(CommonFeatureStyles.getForegroundByTag(object.getTag()));
        Color foregroundItHas = pictogramElement.getGraphicsAlgorithm().getForeground();
        
        if(foregoundIsShouldHave.equals(foregroundItHas) && textItShouldHave.equals(textItHas)){
            return Reason.createFalseReason();  
        }

        return Reason.createTrueReason("Element is out of date");        
    }

    protected String getTextItHas(PictogramElement pictogramElement) {
        ContainerShape containerShape = (ContainerShape) pictogramElement;
        
        Optional<Text> fristText = containerShape.getChildren().stream()
            .filter(it -> it.getGraphicsAlgorithm() instanceof Text)
            .map(it -> (Text) it.getGraphicsAlgorithm())
            .findFirst();    
        
        return fristText.get().getValue();
    }
 

    @Override
    public boolean update(IUpdateContext context) { 
        PictogramElement pictogramElement = context.getPictogramElement();
        SPEObject object = (SPEObject) getBusinessObjectForPictogramElement(pictogramElement);
        
        //update text
        String newText = SPEObjectAddFeature.createHeaderTextOfObject(object);
        ContainerShape containerShape = (ContainerShape) pictogramElement;
        TextStyleRegion underlinedStyleRegion = SPEObjectAddFeature.createUnderlinedStyleregion(newText);        
        containerShape.getChildren().stream()
                .filter(it -> it.getGraphicsAlgorithm() instanceof Text)
                .map(it -> (Text) it.getGraphicsAlgorithm())
                .forEach(it -> {
                    it.setValue(newText);
                    it.getStyleRegions().clear();
                    it.getStyleRegions().add(underlinedStyleRegion);
                });
       

        //update colors
        MatchTag tag = object.getTag();
        Color foregroundItShouldHave = manageColor(CommonFeatureStyles.getForegroundByTag(tag));
        Color backgroundItShouldHave = manageColor(CommonFeatureStyles.getBackgroundByTag(tag));
        LineStyle lineStyleItShouldhave = CommonFeatureStyles.getLineStyleByTag(tag);
        
        GraphicsAlgorithm mainContainer = pictogramElement.getGraphicsAlgorithm();
        mainContainer.setForeground(foregroundItShouldHave);
        mainContainer.setBackground(backgroundItShouldHave);
        mainContainer.setLineStyle(lineStyleItShouldhave);
        
        containerShape.getChildren().stream()
            .filter(it -> it.getGraphicsAlgorithm() instanceof Polyline)
            .map(it -> (Polyline) it.getGraphicsAlgorithm())
            .forEach(it -> {
                it.setForeground(foregroundItShouldHave);
                it.setLineStyle(lineStyleItShouldhave);    
            });  
     
        return true;
    }

}
