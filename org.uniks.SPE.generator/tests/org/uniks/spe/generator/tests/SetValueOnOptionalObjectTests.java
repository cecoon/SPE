package org.uniks.spe.generator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import matcher.MatchClassoptionalSetValue;
import model.Item;
import model.Store;
import model.util.ItemPO;
import model.util.StorePO;
import model.util.StoreSet;

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

        assertTrue(!mainPO.allMatches().isEmpty());
        assertEquals(5, optionalItem.getValue());
    }

    @Test
    public void generatedMatcherTest() {
        createStoreWithOptional();

        assertTrue(new MatchClassoptionalSetValue().execute(store));
        assertEquals(5, optionalItem.getValue());
    }

}
