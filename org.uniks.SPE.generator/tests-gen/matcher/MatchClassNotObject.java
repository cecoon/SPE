package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassNotObject {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO iPO = thisPO.startNAC().hasHas().hasValue(2).endNAC();
		
		//match objects of subgroups
		
		
		//matching missing links to known					
		
		//update model 
		
		return thisPO.allMatches();
	} 
	
}
