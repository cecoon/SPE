package org.uniks.SPE.generator.tests;
  
import matcher.MatchClassAttributes_notEqual_biggerE_smallerE_stringOps;
import matcher.MatchClassAttributes_smaller_bigger_interval_equals; 
import model.Item;
import model.Person;
import model.Store;  
import model.util.ItemPO;
import model.util.StorePO;
import model.util.StoreSet;
import static org.junit.Assert.*;

import org.junit.Before; 
import org.junit.Test;   
 



import de.uniks.networkparser.logic.Condition;

public class AttributesTest {  
    private Store store;
    
    public void createStore() { 
        store = new Store();        
        store.createHas().withValue(2); 
        store.createHas().withValue(1);  
        store.createHas().withValue(1);  
        
        store.createCustomer().withName("alfred");
        store.createCustomer().withName("jodokus");
        store.createCustomer().withName("kwak");//kinda schizophrenic person
       }  
     
    @Test
    public void modelTest1() {  
        createStore();
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
         
        mainPO.hasHas().startNAC().hasValue(1).endNAC();  // != 1   
        mainPO.hasHas().hasValue(Integer.MIN_VALUE, 2);  // <= 2
        mainPO.hasHas().hasValue(2, Integer.MAX_VALUE);  // >= 2 
        
        mainPO.hasCustomer().hasName("alfred"); // == alfred          
        mainPO.hasCustomer().has(new Condition<Object>() {            
            @Override
            public boolean check(Object value) {
                if(value instanceof Person){
                    boolean matches = ((Person)value).getName().matches("[k]+.*");
                    return matches;
                }
                return false; 
            }   
        });       
                 
        assertTrue( ! mainPO.allMatches().isEmpty());              
    }
      
      
    @Test 
    public void generatedMatcherTest1() {   
        createStore();      
        boolean empty = new MatchClassAttributes_notEqual_biggerE_smallerE_stringOps().findMatch(store).isEmpty();
        assertFalse(empty);              
    } 
    
    public void createStore2() { 
        store = new Store();         
        Item i1 = store.createHas().withValue(1);           
        Item i2 = store.createHas().withValue(2);
        Item i3 = store.createHas().withValue(3);         
        Item i4 = store.createHas().withValue(4);    
    }  
     
    @Test
    public void modelTest2() {   
        createStore2();
        StorePO mainPO = new StoreSet().with(store).hasStorePO();  
        
        ItemPO item1 = mainPO.hasHas().hasValue(Integer.MIN_VALUE, 1);  // < 2
        ItemPO item2 = mainPO.hasHas().hasValue(2, 2);  // <3>1 
        ItemPO item3 = mainPO.hasHas().hasValue(3);  // ==3
        ItemPO item4 = mainPO.hasHas().hasValue(4, Integer.MAX_VALUE);  // >3 
        
        assertTrue( ! mainPO.allMatches().isEmpty());              
    }
      
      
    @Test
    public void generatedMatcherTest2() {      
        createStore2();   
        boolean empty = new MatchClassAttributes_smaller_bigger_interval_equals().findMatch(store).isEmpty();
        assertFalse(empty);              
    }
    

    
    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

} 
