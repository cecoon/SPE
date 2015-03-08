package org.uniks.spe.editor.features.objects;
 
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.SPEGroup;
import model.SPEObject;
import model.Tag;

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
    private final static String THIS = SPEObjectAddFeature.THIS;
    private final static String OBEJCT_WITH_CLASS_REGEX = "^[?!.]{0,1}(\\w+):(\\w+)$"; 
    
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
        return SPEObjectAddFeature.createHeaderTextOfObject(speObject);
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
            speObject.setType(""); 
        } else {
            Matcher match = Pattern.compile(OBEJCT_WITH_CLASS_REGEX).matcher(value);
            match.matches();        
            speObject.setName(match.group(1)); 
            speObject.setType(match.group(2));
        }
        
        if(isInRootGroup(speObject)){
            if(value.startsWith("!")){
                speObject.setTag(Tag.NOT);
            } 
            if(value.startsWith("?")){
                speObject.setTag(Tag.OPTIONAL);
            }
            if(value.startsWith(".")){
                speObject.setTag(Tag.DEFAULT);
            }             
        };
        
        updatePictogramElement(((Shape) pictorgram).getContainer());
    }

    protected boolean isInRootGroup(SPEObject speObject) {
        return getRootGroup().getObjects().contains(speObject);
    }

    protected SPEGroup getRootGroup() {
        return (SPEGroup) getBusinessObjectForPictogramElement(getDiagram().getLink().getPictogramElement());
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
        SPEGroup rootGroup = getRootGroup();        
        Stream<SPEObject> flatMap = rootGroup.getSubGroups().stream()
                .map(it -> it.getObjects())
                .flatMap(it -> it.stream());        
        Stream<SPEObject> allObjects = Stream.concat(flatMap, rootGroup.getObjects().stream());    
        
        Set<String> proposals = allObjects.map(it -> it.getType())
                .filter(it -> it.startsWith(enteredClassName))
                .map(it -> objectName + " : " + it)
                .collect(Collectors.toSet());
            
        String[] result = proposals.toArray(new String[proposals.size()]);
        return result;
    }
    
    public boolean containsSPEObject(PictogramElement elem){
       return getBusinessObjectForPictogramElement(elem.getLink().getPictogramElement()) instanceof SPEObject;
    }
}