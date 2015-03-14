package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for NotLink.spe diagram.
*/
public class MatchClassNotLink {
	
	/**
	* matches and applies the in NotLink.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		PersonPO pPO = thisPO.hasCustomer().hasBalance(42);
		ItemPO i1PO = pPO.hasHas().hasValue(23);
		ItemPO i2PO = i1PO.hasNext().hasValue(19);
		
		//match objects of subgroups 
		
		//matching missing links to known
		thisPO.hasHas(i1PO);
		pPO.startNAC().hasHas(i2PO).endNAC();
		
		//update model 
	
		return ! thisPO.allMatches().isEmpty();
	}
}
