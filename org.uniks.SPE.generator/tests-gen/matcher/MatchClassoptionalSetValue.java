package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassoptionalSetValue {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.startSubPattern().hasHas().endSubPattern();
		ItemPO i2PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups
		
		
		//matching missing links to known					
		i2PO.hasNext(i1PO);
		
		//update model 
		i1PO.startCreate().createValue(5).endCreate();
		
		return thisPO.allMatches();
	} 
	
}
