package org.uniks.SPE.generator.tests;
   
import matcher.MatchClassNotLink; 
import matcher.MatchClassNotObject;
import matcher.MatchClassoptionalSetValue;
import model.Item;
import model.Person;
import model.Store; 
import model.util.ItemPO;
import model.util.PersonPO;
import model.util.StorePO;
import model.util.StoreSet;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;   

public class SetValueOnOptionalObjectTests {  
    private Store store;
    Item optionalItem;
    
    public void createStoreWithOptional() { 
        store = new Store();    
        optionalItem = store.createHas();
        Item i2 = store.createHas().withValue(3);        
        i2.setNext(optionalItem);
    } 
      
    @Test
    public void modelTest() { 
        createStoreWithOptional();    
        
        StorePO mainPO = new StoreSet().with(store).hasStorePO();         
        ItemPO i1 = mainPO.startSubPattern().hasHas().startCreate().createValue(5).endCreate().endSubPattern();
        ItemPO i2 = mainPO.hasHas().hasValue(3);
        i2.hasNext(i1); 
         
        assertTrue( ! mainPO.allMatches().isEmpty());    
        assertEquals(5, optionalItem.getValue());  
    }  
          
      
      
    @Test 
    public void generatedMatcherTest() {  
        createStoreWithOptional();  
        
        boolean empty = new MatchClassoptionalSetValue().findMatch(store).isEmpty();
        assertFalse(empty);    
        assertEquals(5, optionalItem.getValue());  
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
