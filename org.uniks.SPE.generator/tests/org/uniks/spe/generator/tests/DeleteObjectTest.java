package org.uniks.spe.generator.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import matcher.MatchClassDeleteObject;
import model.Item;
import model.Store;
import model.util.ItemPO;
import model.util.StorePO;
import model.util.StoreSet;

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
        ItemPO i2 = mainPO.hasHas().hasValue(3);
        i2.hasNext(i1);

        i1.destroy();

        assertTrue(!mainPO.allMatches().isEmpty());
        assertFalse(store.getHas().contains(deleteItem));
    }

    @Test
    public void generatedMatcherTest() {
        assertTrue(new MatchClassDeleteObject().execute(store));
        assertFalse(store.getHas().contains(deleteItem));
    }

}
