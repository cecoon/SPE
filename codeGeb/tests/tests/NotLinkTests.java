package tests;
   
import matcher.MatchClassNotLink; 
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

import com.google.inject.spi.Dependency;

public class NotLinkTests {  
    private Store store;
    
    @Before
    public void createStore() { 
        store = new Store();        
        Item i1 = store.createHas().withValue(23);        
        Item i2 = i1.createNext().withValue(19); 
        Person p = store.createCustomer().withBalance(42).withHas(i1);     
    } 
     
    @Test
    public void modelTest() {        
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
        
        PersonPO person = mainPO.hasCustomer().hasBalance(42);           
        ItemPO item1 = person.hasHas().hasValue(23);  
        ItemPO item2 = item1.hasNext().hasValue(19);
         
        mainPO.hasCustomer(person);
        person.startNAC().hasHas(item2).endNAC();     //negative links can only be checked if the targetObj is known.   
        item1.hasNext(item2); 
        System.out.println();
        assertTrue( ! mainPO.allMatches().isEmpty());              
    }
         
      
      
    @Test
    public void generatedMatcherTest() {         
        boolean empty = new MatchClassNotLink().findMatch(store).isEmpty();
        assertFalse(empty);    
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
