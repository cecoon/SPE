package org.uniks.spe.generator.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collection;
import java.util.stream.Stream;

import matcher.MatchClassCreateObjectAndLink;
import model.Item;
import model.Store;
import model.util.ItemPO;
import model.util.ItemSet;
import model.util.StorePO;
import model.util.StoreSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.junit.Before;
import org.junit.Test;

public class CreateObjectAndLinkTest  {  
    private Store store;
    private Item i1;

    @Before
    public void createStore() {
        store = new Store();
        i1 = store.createHas().withValue(3);
    }

    @Test
    public void modelTest() {
        StorePO mainPO = new StoreSet().with(store).hasStorePO();
        ItemPO item1 = mainPO.hasHas().hasValue(3);

        ItemPO i2PO = new ItemSet().with(new Item()).hasItemPO();

        i2PO.startCreate().hasValue(4).endCreate();
        item1.startCreate().hasNext(i2PO).endCreate();

        assertTrue(!mainPO.allMatches().isEmpty());
        assertNotNull(i1.getNext());
        assertEquals(4, i1.getNext().getValue()); 
    }

    @Test
    public void generatedMatcherTest() {
        boolean empty = new MatchClassCreateObjectAndLink().findMatch(store).isEmpty();
        assertFalse(empty);

        assertNotNull(i1.getNext());
        assertEquals(4, i1.getNext().getValue());

    }

}
