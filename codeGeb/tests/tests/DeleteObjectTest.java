package tests;
   
import matcher.MatchClassDeleteObject;
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

public class DeleteObjectTest {  
    private Store store;
    Item deleteItem;
    
    @Before
    public void createStore() {  
        store = new Store();    
        deleteItem = store.createHas();
        Item i2 = store.createHas().withValue(3);        
        i2.setNext(deleteItem);
    } 
      
    @Test
    public void modelTest() {          
        StorePO mainPO = new StoreSet().with(store).hasStorePO();           
        ItemPO i1 = mainPO.hasHas();
        ItemPO i2= mainPO.hasHas().hasValue(3);                     
        i2.hasNext(i1);
        
        i1.destroy();
         
        assertTrue( ! mainPO.allMatches().isEmpty());  
        assertFalse(store.getHas().contains(deleteItem));     
    }  
           
      
      
    @Test 
    public void generatedMatcherTest() {          
        boolean empty = new MatchClassDeleteObject().findMatch(store).isEmpty();
        assertFalse(empty);     
        assertFalse(store.getHas().contains(deleteItem));  
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
