package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassCreateObjectAndLink {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups
		
		
		//matching missing links to known					
		
		//update model 
		 ItemPO i2PO = new ItemSet().with(new Item()).hasItemPO();
		i1PO.startCreate().hasNext(i2PO).endCreate();
		i2PO.startCreate().createValue(4).endCreate();
		
		return thisPO.allMatches();
	} 
	
}
