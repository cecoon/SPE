package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
public class MatchClassNACGroupDiagram {
	
	/**
	* finds a match from a given start 
	*/
	public StoreSet findMatch(Store start){					
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		
		//match objects of subgroups
		thisPO.startNAC();
		ItemPO i1PO = thisPO.hasHas().hasValue(2);
		ItemPO i2PO = i1PO.hasNext();
		thisPO.hasHas(i2PO);
		thisPO.endNAC();
		
		
		//matching missing links to known					
		
		//update model 
		
		return thisPO.allMatches();
	} 
	
}
