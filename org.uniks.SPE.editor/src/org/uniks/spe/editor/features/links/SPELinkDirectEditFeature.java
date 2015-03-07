package org.uniks.spe.editor.features.links;

import java.util.regex.Matcher;

import model.SPEAttribute;
import model.SPELink;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;

public class SPELinkDirectEditFeature extends AbstractDirectEditingFeature {

    public SPELinkDirectEditFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public int getEditingType() { 
        return TYPE_TEXT;
    }

    @Override
    public String getInitialValue(IDirectEditingContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        SPELink link = (SPELink) getBusinessObjectForPictogramElement(pictogramElement);         
        return link.getName();
    }
    
    @Override
    public boolean canDirectEdit(IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        GraphicsAlgorithm graphic = context.getGraphicsAlgorithm();        
        
        return getBusinessObjectForPictogramElement(pictorgram) instanceof SPELink && graphic instanceof Text ;
    }
 
 
    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {   
        value = value.replaceAll(" ", "");        
       
        if(value.matches("^\\w$")){
            return null;
        }
        
        return "invalid format.";
    }
    
    @Override
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        SPELink link = (SPELink) getBusinessObjectForPictogramElement(pictorgram);  
        
        value = value.replaceAll(" ", "");     
        link.setName(value);  
       
        updatePictogramElement(((Shape) pictorgram).getContainer());
    }

}
