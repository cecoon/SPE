package org.uniks.spe.generator.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import matcher.MatchClassNACGroupDiagram;
import matcher.MatchClassNotLink;
import model.Item;
import model.Person;
import model.Store;
import model.util.ItemPO;
import model.util.PersonPO;
import model.util.StorePO;
import model.util.StoreSet;

import org.junit.Before;
import org.junit.Test;

public class NACGrpTest  {  
    private Store store;
    private Store notMatchStore;

    @Before
    public void createStore() {
        {
        notMatchStore = new Store();
        
        Item i1 = notMatchStore.createHas().withValue(2);
        Item i2 = notMatchStore.createHas();        
        i1.setNext(i2);  
        }     
        
        {
        store = new Store();
        Item i1 = store.createHas().withValue(2);
        Item i2 = store.createHas();
        }
    }

    @Test
    public void modelTestMatch() {        
        StorePO mainPO = getMatch(store);    
        assertTrue(!mainPO.allMatches().isEmpty());        
    }
    
    @Test
    public void modelTestNotMatch() {        
        StorePO mainPO = getMatch(notMatchStore);            
        assertTrue(mainPO.allMatches().isEmpty());        
    }
    
    protected StorePO getMatch(Store storeToMatch) {
        StorePO mainPO = new StoreSet().with(storeToMatch).hasStorePO();
        
        mainPO.startNAC();
        //traverse objects in NAC
        ItemPO i1PO = mainPO.hasHas().hasValue(2); 
        ItemPO i2PO = i1PO.hasNext();
        
        //NAC internal links 
        
        //chk links to aliens
        mainPO.hasHas(i2PO); 
        
        mainPO.endNAC();  
        
        return mainPO;
    } 

    @Test
    public void generatedNotMatcherTest() {
        boolean empty = new MatchClassNACGroupDiagram().findMatch(notMatchStore).isEmpty();
        assertTrue(empty);
    }
    
    @Test
    public void generatedMatcherTest() {
        boolean empty = new MatchClassNACGroupDiagram().findMatch(store).isEmpty();
        assertFalse(empty);
    }
    

}
