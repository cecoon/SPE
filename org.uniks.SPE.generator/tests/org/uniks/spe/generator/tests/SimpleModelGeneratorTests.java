package org.uniks.spe.generator.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import matcher.MatchClassSimpleDiagram;
import model.Item;
import model.Person;
import model.Store;
import model.util.ItemPO;
import model.util.PersonPO;
import model.util.StorePO;
import model.util.StoreSet;

import org.junit.Before;
import org.junit.Test;

public class SimpleModelGeneratorTests {  
    private Store store;

    @Before
    public void createStore() {
        store = new Store();
        Item i1 = store.createHas().withValue(23);
        Item i2 = i1.createNext().withValue(19);
        Person p = store.createCustomer().withBalance(42).withHas(i1, i2);
    }

    @Test
    public void generatedMatcherTest() {
        assertTrue(new MatchClassSimpleDiagram().execute(store));
    }
    @Test
    public void modelTest() {
        StorePO mainPO = new StoreSet().with(store).hasStorePO();
 
        PersonPO person = mainPO.hasCustomer().hasBalance(42);
        ItemPO item1 = person.hasHas().hasValue(23);
        ItemPO item2 = item1.hasNext().hasValue(19);

        mainPO.hasHas(item1);
        person.hasHas(item2);
        assertTrue(!mainPO.allMatches().isEmpty());
    }

}
  