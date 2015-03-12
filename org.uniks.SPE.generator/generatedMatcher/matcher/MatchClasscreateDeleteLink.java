package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClasscreateDeleteLink {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects 
		ItemPO i1PO = thisPO.hasHas();
		ItemPO i2PO = i1PO.hasNext().hasValue(3);
		
		//matching missing links to known					
		thisPO.hasHas(i2PO);
		
		//update model
		i2PO.startCreate().hasNext(i1PO).endCreate();
		
		i1PO.startDestroy().hasNext(i2PO).endDestroy();
		
		return thisPO.allMatches();
	}
	
}  
