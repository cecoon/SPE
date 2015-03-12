package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassSimpleDiagram {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects 
		PersonPO pPO = thisPO.hasCustomer().hasBalance(42);
		ItemPO i1PO = pPO.hasHas().hasValue(23);
		ItemPO i2PO = i1PO.hasNext().hasValue(19);
		
		//matching missing links to known					
		thisPO.hasHas(i1PO);
		pPO.hasHas(i2PO);
		
		//update model
		
		
		return thisPO.allMatches();
	}
	
}  
