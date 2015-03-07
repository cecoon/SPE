package org.uniks.spe.editor.features.objects;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 












import model.ModelFactory;
import model.SPEObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
 
public class SPEObjectDirectEditFeature extends AbstractDirectEditingFeature {
     
    private final static String THIS = "this";
    private final static String OBEJCT_WITH_CLASS_REGEX = "^(\\w+):(\\w+)$"; 
    
    public SPEObjectDirectEditFeature(IFeatureProvider fp) {
        super(fp); 
    }

    @Override
    public int getEditingType() { 
        return TYPE_TEXT;
    }
    
    @Override
    public String getInitialValue(IDirectEditingContext context) {
        PictogramElement pictogramElement = context.getPictogramElement();
        SPEObject speObject = (SPEObject) getBusinessObjectForPictogramElement(pictogramElement);  
        
        if(speObject.getName().equals(THIS)){
            return THIS;
        }
        
        return speObject.getName() + " : " + speObject.getClass_();
    }
    
    @Override
    public boolean canDirectEdit(IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        GraphicsAlgorithm graphic = context.getGraphicsAlgorithm();        
        
        return getBusinessObjectForPictogramElement(pictorgram) instanceof SPEObject && graphic instanceof Text ;
    }
 
    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {   
        value = value.replaceAll(" ", "");        
       
       if(THIS.equals(value) || value.matches(OBEJCT_WITH_CLASS_REGEX)){
           return null;  // null means, that the value is valid
       };
 
        return "invalid format.";
    }

    @Override
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        SPEObject speObject = (SPEObject) getBusinessObjectForPictogramElement(pictorgram);  
        
        value = value.replaceAll(" ", "");  
        if(THIS.equals(value)){
            speObject.setName(THIS); 
            speObject.setClass(""); 
        } else {
            Matcher match = Pattern.compile(OBEJCT_WITH_CLASS_REGEX).matcher(value);
            match.matches();        
            speObject.setName(match.group(1)); 
            speObject.setClass(match.group(2)); 
        }
        
        updatePictogramElement(((Shape) pictorgram).getContainer());
    }

    @Override
    public boolean isAutoCompletionEnabled() {
        return true;
    }

    @Override
    public boolean isCompletionAvailable() {
        return true;
    }

    @Override
    public String[] getValueProposals(String value, int caretPos, IDirectEditingContext context)   {  
        String[] split = value.replaceAll(" ", "").split(":");       
        if(split.length <= 1) {
            return new String[0];           
        }

        String objectName = split[0];
        String enteredClassName = split[1];
        
        HashSet<String> proposals = new HashSet<String>();
        Diagram diagram = getDiagram();
        EList<Shape> children = diagram.getChildren();
        for (Shape shape : children) { 
            Object businessObject = getBusinessObjectForPictogramElement(shape.getLink().getPictogramElement());
            if(businessObject instanceof SPEObject){
                SPEObject object = (SPEObject) businessObject;
                if(object.getClass_().startsWith(enteredClassName)){
                    proposals.add(objectName + " : " + object.getClass_());           
                }                                
            }
        }  
        String[] result = proposals.toArray(new String[proposals.size()]);
        return result;
    }
}