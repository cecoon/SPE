package tests;
   
import matcher.MatchClassNotLink; 
import matcher.MatchClassNotObject;
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

public class NotObjectTests {  
    private Store store;
    
    @Before
    public void createStore() { 
        store = new Store();    
        store.createHas().withValue(14);  
        store.createHas().withValue(34); 
    } 
      
    @Test
    public void modelTest() {         
        StorePO mainPO = new StoreSet().with(store).hasStorePO(); 
        
        mainPO.startNAC().hasHas().hasValue(2).endNAC();   
          
        assertTrue( ! mainPO.allMatches().isEmpty());               
    }  
          
      
      
    @Test
    public void generatedMatcherTest() {         
        boolean empty = new MatchClassNotObject().findMatch(store).isEmpty();
        assertFalse(empty);    
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
