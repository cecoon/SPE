package org.uniks.spe.generator;

import java.util.Map;

import model.ModelPackage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.IEncodingProvider;
import org.eclipse.xtext.service.AbstractGenericModule;

import com.google.inject.Guice;

public class GeneratorHelper {
    
    public static void doGenerate(String modelUri, String targetUri) {        
        ModelPackage.eINSTANCE.eClass();
        
        Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;      
        Map<String, Object> extentionMap = reg.getExtensionToFactoryMap();
        extentionMap.put("spemodel", new XMIResourceFactoryImpl());        
        
        JavaIoFileSystemAccess fsa = getFileSystemAccess();
        fsa.setOutputPath(targetUri + "/spe-gen/");
        
        ResourceSetImpl rset = new ResourceSetImpl();   
        Resource resource = rset.getResource(URI.createURI(modelUri), true);
        
        Generator generator = new Generator();
        generator.doGenerate(resource, fsa);
    }

    
    protected static JavaIoFileSystemAccess getFileSystemAccess() {
        JavaIoFileSystemAccess fsa = new JavaIoFileSystemAccess();        
        Guice.createInjector(new AbstractGenericModule() {
            public Class<? extends IEncodingProvider> bindIEncodingProvider() {
                return IEncodingProvider.Runtime.class;
            }
        }).injectMembers(fsa);
        return fsa;
    }
}
