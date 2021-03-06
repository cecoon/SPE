package org.uniks.spe.editor.features.objects;
 
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import model.MatchTag;
import model.SPEGroup;
import model.SPEObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.impl.AbstractDirectEditingFeature;
import org.eclipse.graphiti.mm.algorithms.GraphicsAlgorithm;
import org.eclipse.graphiti.mm.algorithms.Text;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.uniks.spe.editor.ClassFinder;
import org.uniks.spe.editor.features.Common;
 
public class SPEObjectDirectEditFeature extends AbstractDirectEditingFeature {     
    private final static String OBEJCT_WITH_CLASS_REGEX = "^[+\\-?!.]{0,2}(\\w+):(\\w+)$";
    private ClassFinder classFinder;
     
   
    public SPEObjectDirectEditFeature(IFeatureProvider fp) {
        super(fp);
        this.classFinder = new ClassFinder(); 
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
       
       if(value.matches(OBEJCT_WITH_CLASS_REGEX)){
           return null;  // null means, that the value is valid
       };
 
        return "invalid format.";
    }

    @Override
    public void setValue(String value, IDirectEditingContext context) {
        PictogramElement pictorgram = context.getPictogramElement();  
        SPEObject speObject = (SPEObject) getBusinessObjectForPictogramElement(pictorgram);  
        
        value = value.replaceAll(" ", ""); 
        
        Matcher match = Pattern.compile(OBEJCT_WITH_CLASS_REGEX).matcher(value);
        match.matches();        
        speObject.setName(match.group(1)); 
        speObject.setType(match.group(2));       
        
        if(isInRootGroup(speObject)){
            Common.setTagBasedOnPrefix(speObject, value);
        }; 
        Common.setOperationBasedOnPrefix(speObject, value);
        
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
        
        Stream<String> classesInDiagram = getAllObjects().stream().map(it -> it.getType());  
     //   Stream<String> classesInModel = classFinder.getModelClasses(getRootGroup().getModel()).stream();
        
        Set<String> proposals = 
               // Stream.concat(classesInDiagram , classesInModel)
                classesInDiagram
                .filter(it -> it.startsWith(enteredClassName))
                .map(it -> objectName + " : " + it)
                .collect(Collectors.toSet());

        String[] result = proposals.toArray(new String[proposals.size()]);
        return result;
    }
    
    protected boolean isInRootGroup(SPEObject speObject) {
        return getRootGroup().getObjects().contains(speObject);
    }

    protected SPEGroup getRootGroup() {
        return (SPEGroup) getBusinessObjectForPictogramElement(getDiagram().getLink().getPictogramElement());
    }

    protected List<SPEObject> getAllObjects() {
        SPEGroup rootGroup = getRootGroup();        
        List<SPEObject> subGrpObjects = rootGroup.getSubGroups().stream()
                .map(it -> it.getObjects())
                .flatMap(it -> it.stream())
                .collect(Collectors.toList());
        
        subGrpObjects.addAll(rootGroup.getObjects());        
        
        return subGrpObjects;
    }
    
    public boolean containsSPEObject(PictogramElement elem){
       return getBusinessObjectForPictogramElement(elem.getLink().getPictogramElement()) instanceof SPEObject;
    }
   
}