package util;

import java.util.Map;

import model.ModelPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.service.AbstractGenericModule;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

import codeGenerator.Generator;

import com.google.inject.Guice;

public class CodeGeneration {
    
    @BeforeClass
    public static void generateModel() {
        ClassModel model = new ClassModel("model");  
        
        Clazz itemClass = model.createClazz("Item")
                .withAttribute("name", DataType.STRING)
                .withAttribute("value", DataType.INT);
        
        itemClass.withAssoc(itemClass, "next", Card.ONE, "prev", Card.ONE); 
        
        Clazz personClass = model.createClazz("Person")
                .withAttribute("name", DataType.STRING)
                .withAttribute("balance", DataType.INT)
                .withAssoc(itemClass, "has", Card.MANY, "owner", Card.ONE);      

        Clazz storeClass = model.createClazz("Store")
                .withAssoc(itemClass,"has", Card.MANY, "inStore", Card.ONE) 
                .withAssoc(personClass,"customer", Card.MANY, "store", Card.ONE);
        
        model.generate("model");
    }
        
      
    @Test
    public void generateSimpleMatcher() {   
        String nameOfDiagram = "SimpleDiagram.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);    
    }  
    
    @Test
    public void generateNotLinkMatcher() {   
        String nameOfDiagram = "Not_Link_Diagram.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    } 
     
    @Test 
    public void generateAttributeMatcher1() {   
        String nameOfDiagram = "Attributes_smaller_bigger_interval_equals.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    } 
    
    @Test 
    public void generateAttributeMatcher2() {   
        String nameOfDiagram = "Attributes_notEqual_biggerE_smallerE_stringOps.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    }
    
    @Test 
    public void generateNotMatcher() {   
        String nameOfDiagram = "not_object.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    }
    
    @Test 
    public void generateDeleteMatcher() {   
        String nameOfDiagram = "deleteObject.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    }
    
    @Test 
    public void generateOptionalMatcher() {   
        String nameOfDiagram = "optional_setValue.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    }
    
    @Test 
    public void generateCreateDeleteLinkMatcher() {   
        String nameOfDiagram = "createDeleteLink.spemodel";        
        generateMatcherForDiagram(nameOfDiagram);        
    } 
    
    protected void generateMatcherForDiagram(String nameOfDiagram) {
        ModelPackage.eINSTANCE.eClass();  
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        ResourceSetImpl rset = new ResourceSetImpl();
        Map<String, Object> extentionMap = reg.getExtensionToFactoryMap();
        extentionMap.put("spemodel", new XMIResourceFactoryImpl());        
     
        JavaIoFileSystemAccess fsa = new JavaIoFileSystemAccess();
        Guice.createInjector(new AbstractGenericModule() {
            public Class<? extends IEncodingProvider> bindIEncodingProvider() {
                return IEncodingProvider.Runtime.class;
            }
        }).injectMembers(fsa);
         
        Generator generator = new Generator();  
        Resource resource = rset.getResource(URI.createURI("./src/diagram/" + nameOfDiagram), true);   
        fsa.setOutputPath("./generatedMatcher/matcher/");         
        generator.doGenerate(resource, fsa);
    }     
     


}
