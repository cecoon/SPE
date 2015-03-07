package org.uniks.spe.editor.features.SPEAttribute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.SPEAttribute; 

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
 
public class SPEAttributeDirectEditFeature extends AbstractDirectEditingFeature {
    
    private final static String SINGEL_OPERATOR_REGEX = "^(\\w+)([!=<>]={0,1}[^!=<>]+)$";
    private final static String INTERVAL_OPERATOR_REGEX = "^(\\w+)([=<>]={0,1}[^!=<>]+){2}$";    
    private final static Pattern EXTRACT_ATTRIBUTE_PATTERN = Pattern.compile("^(\\w+)([!=<>].*)$");
    
    public SPEAttributeDirectEditFeature(IFeatureProvider fp) {
        super(fp);
    }

    @Override
    public int getEditingType() { 
        return TYPE_TEXT;
    }
    
    @Override
    public String getInitialValue(IDirectEditingContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        SPEAttribute speAttribute = (SPEAttribute) getBusinessObjectForPictogramElement(pictogramElement);         
        return speAttribute.getName() + " " + speAttribute.getOperation();
    }
    
    @Override
    public boolean canDirectEdit(IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        GraphicsAlgorithm graphic = context.getGraphicsAlgorithm();        
        
        return getBusinessObjectForPictogramElement(pictorgram) instanceof SPEAttribute && graphic instanceof Text ;
    }
 
 
    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {   
        value = value.replaceAll(" ", "");        
       
       if( EXTRACT_ATTRIBUTE_PATTERN.matcher(value).groupCount() == 2 
               && (value.matches(SINGEL_OPERATOR_REGEX)  || value.matches(INTERVAL_OPERATOR_REGEX)) ){
           return null;  // null means, that the value is valid
       };
 
        return "invalid format.";
    }
    
    @Override
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        SPEAttribute speAttribute = (SPEAttribute) getBusinessObjectForPictogramElement(pictorgram);  
        
        value = value.replaceAll(" ", "");        
        Matcher match = EXTRACT_ATTRIBUTE_PATTERN.matcher(value);
        match.matches();        
        speAttribute.setName(match.group(1)); 
        speAttribute.setOperation(match.group(2)); 
       
        updatePictogramElement(((Shape) pictorgram).getContainer());
    }

  
}