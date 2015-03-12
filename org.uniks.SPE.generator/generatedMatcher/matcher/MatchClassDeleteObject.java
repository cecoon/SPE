package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassDeleteObject {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects 
		ItemPO i1PO = thisPO.hasHas();
		ItemPO i2PO = thisPO.hasHas().hasValue(3);
		
		//matching missing links to known					
		i2PO.hasNext(i1PO);
		
		//update model
		
		i1PO.startCreate().createValue(5).endCreate();
		i1PO.destroy();
		
		return thisPO.allMatches();
	}
	
}  
