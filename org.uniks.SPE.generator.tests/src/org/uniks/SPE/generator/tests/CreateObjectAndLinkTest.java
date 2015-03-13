package org.uniks.SPE.generator.tests;
   
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import matcher.MatchClassCreateObjectAndLink;
import matcher.MatchClassNotLink; 
import matcher.MatchClasscreateDeleteLink;
import model.Item;
import model.Person;
import model.Store; 
import model.util.ItemPO;
import model.util.ItemSet;
import model.util.PersonPO;
import model.util.StorePO;
import model.util.StoreSet;
import static org.junit.Assert.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.junit.Before;
import org.junit.Test;   
import org.sdmlib.models.classes.ClassModel;

public class CreateObjectAndLinkTest {  
    private Store store; 
    private Item i1;
    
    @Before
    public void createStore() { 
        store = new Store();        
        i1 = store.createHas().withValue(3);     
    } 
     
    @Test
    public void modelTest() {        
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
        ItemPO item1 = mainPO.hasHas().hasValue(3);
        
        ItemPO i2PO = new ItemSet().with(new Item()).hasItemPO(); 
       
       
        i2PO.startCreate().hasValue(4).endCreate();
        item1.startCreate().hasNext(i2PO).endCreate();
        
        assertTrue( ! mainPO.allMatches().isEmpty());    
        assertNotNull(i1.getNext());    
        assertEquals(4, i1.getNext().getValue());    
        
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader)cl).getURLs();
        for(URL url: urls){
            String path = url.getFile();
            if(path.contains("model") && path.contains("bin")){                 
                Collection<File> files = FileUtils.listFiles(
                     new File(url.getFile()), 
                     new RegexFileFilter("^(.*?)"), 
                     DirectoryFileFilter.DIRECTORY
                 );
                
                Stream<String> ClassNames = files.stream().map(it -> it.getName().replace(".class", ""));
                ClassNames.forEach(it -> System.out.println(it));
         }
      
     }
    
    } 
           
         
      
       
    @Test 
    public void generatedMatcherTest() {         
        boolean empty = new MatchClassCreateObjectAndLink().findMatch(store).isEmpty();
        assertFalse(empty);     

        assertNotNull(i1.getNext());    
        assertEquals(4, i1.getNext().getValue());  
        
    } 
    
    
    

    
    
    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 