package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassAttributes_smaller_bigger_interval_equals {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas().hasValue(2 - 1 ,Integer.MAX_VALUE);
		ItemPO i2PO = thisPO.hasHas().hasValue(3 - 1, 1 + 1);
		ItemPO i4PO = thisPO.hasHas().hasValue(Integer.MIN_VALUE, 3 + 1);
		ItemPO i3PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups
		
		
		//matching missing links to known					
		
		//update model 
		
		return thisPO.allMatches();
	} 
	
}
