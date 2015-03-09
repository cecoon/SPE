package tests;
  
import matcher.MatchClassMyDiagram;
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

public class GeneratorTests {
    private Store store;
    
    @Before
    public void createStore() {
        store = new Store();        
        Item i1 = store.createHas().withValue(23);        
        Item i2 = i1.createNext().withValue(19); 
        Person p = store.createCustomer().withBalance(42).withHas(i1, i2);     
    } 
    
    @Test
    public void modelTest() {        
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
        
        PersonPO person = mainPO.hasCustomer().hasBalance(42);           
        ItemPO item1 = person.hasHas().hasValue(23);  
        ItemPO item2 = item1.hasNext().hasValue(19);
        
        mainPO.hasCustomer(person);
        person.hasHas(item2);        
        item1.hasNext(item2);
        
        assertTrue( ! mainPO.allMatches().isEmpty());              
    }
    
    
    @Test
    public void generatedMatcherTest() {         
        boolean empty = new MatchClassMyDiagram().findMatch(store).isEmpty();
        assertFalse(empty);              
    }
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
