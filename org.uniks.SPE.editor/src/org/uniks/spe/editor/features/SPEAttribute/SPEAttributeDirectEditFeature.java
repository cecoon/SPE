package org.uniks.spe.editor.features.SPEAttribute;

import model.SPEAttribute;
import model.SPELink;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
 
public class SPEAttributeDirectEditFeature extends AbstractDirectEditingFeature {
 
    public SPEAttributeDirectEditFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public int getEditingType() { 
        return TYPE_TEXT;
    }
    
    @Override
    public String getInitialValue(IDirectEditingContext context) {
        return "* = *";
    }
    
    @Override
    public boolean canDirectEdit(IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        GraphicsAlgorithm graphic = context.getGraphicsAlgorithm();        
        
        return getBusinessObjectForPictogramElement(pictorgram) instanceof SPEAttribute
                && graphic instanceof Text ;
    }
 
 
    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {   
       if( ! value.matches("(.*?)=(.*?)")){
           return "please use the following format: 'attribute name = attribute value'";           
       };
 
        // null means, that the value is valid
        return null;
    }
 
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        
        value = value.trim();
        if(value.isEmpty()){
            value = getInitialValue(context);
        }
        
        SPEAttribute speAttribute = (SPEAttribute) getBusinessObjectForPictogramElement(pictorgram);        
        speAttribute.setName(value); 
       
        updatePictogramElement(((Shape) pictorgram).getContainer());
    }

  
}