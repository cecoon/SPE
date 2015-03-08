package org.uniks.spe.editor.features.links;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.MatchTag;
import model.SPELink;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.pictograms.FreeFormConnection;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

public class SPELinkDirectEditFeature extends AbstractDirectEditingFeature {

    private final static String TAGGED_LINK_NAME_REGEX = "^[!?\\.]?(\\w*)$";
    
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
        SPELink link =  (SPELink) getBusinessObjectForPictogramElement((FreeFormConnection)pictogramElement.eContainer());         
        return link.getName();
    }
    
    @Override
    public boolean canDirectEdit(IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();         
        EObject container = pictorgram.eContainer();          
        return container instanceof FreeFormConnection 
                && getBusinessObjectForPictogramElement((FreeFormConnection)container) instanceof SPELink;
    }
 
 
    @Override
    public String checkValueValid(String value, IDirectEditingContext context) {   
        value = value.replaceAll(" ", "");  
        if(value.matches(TAGGED_LINK_NAME_REGEX)){
            return null;
        }
        return "invalid format.";
    }
    
    @Override
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        FreeFormConnection eContainer = (FreeFormConnection) pictorgram.eContainer();
        SPELink link = (SPELink) getBusinessObjectForPictogramElement(eContainer);          
        value = value.replaceAll(" ", "");    
        
        Matcher match = Pattern.compile(TAGGED_LINK_NAME_REGEX).matcher(value);
        match.matches();        
        link.setName(match.group(1)); 
        
        if(value.startsWith("!")){
            link.setTag(MatchTag.NOT);
        } 
        if(value.startsWith("?")){
            link.setTag(MatchTag.OPTIONAL);
        }
        if(value.startsWith(".")){
            link.setTag(MatchTag.DEFAULT);
        }            
        
        updatePictogramElement(pictorgram);
    }

}
