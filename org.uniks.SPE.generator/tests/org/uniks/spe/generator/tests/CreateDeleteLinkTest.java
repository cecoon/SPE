package org.uniks.spe.generator.tests;
    
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import matcher.MatchClasscreateDeleteLink;
import model.Item;
import model.Store;
import model.util.ItemPO;
import model.util.StorePO;
import model.util.StoreSet;

import org.junit.Before;
import org.junit.Test;
 

public class CreateDeleteLinkTest {  
    private Store store;
    private Item i2;
    private Item i1;
    
    @Before
    public void createStore() { 
        store = new Store();        
        i2 = store.createHas().withValue(3);       
        i1 = store.createHas();      
        i1.setNext(i2);    
    } 
     
    @Test
    public void modelTest() {        
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
        ItemPO item1 = mainPO.hasHas();
        ItemPO item2= mainPO.hasHas().hasValue(3);                     
        item1.hasNext(item2);
        
        item1.startDestroy().hasNext(item2).endDestroy();
        item2.startCreate().hasNext(item1).endCreate();
            
        assertTrue( ! mainPO.allMatches().isEmpty());      
        assertNull(i1.getNext());
        assertEquals(i1,i2.getNext());
    } 
           
         
    
        
    @Test
    public void generatedMatcherTest() {         
        boolean empty = new MatchClasscreateDeleteLink().findMatch(store).isEmpty();
        assertFalse(empty);   
        assertNull(i1.getNext());
        assertEquals(i1,i2.getNext());
        
    } 
    
    
    

    
    
    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
