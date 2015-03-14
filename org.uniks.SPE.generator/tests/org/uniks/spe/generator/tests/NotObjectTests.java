package org.uniks.spe.generator.tests;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import matcher.MatchClassNotObject;
import model.Store;
import model.util.StorePO;
import model.util.StoreSet;

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

        assertTrue(!mainPO.allMatches().isEmpty());
    }
 
    @Test 
    public void generatedMatcherTest() {
        assertTrue(new MatchClassNotObject().execute(store));
    }
}
    