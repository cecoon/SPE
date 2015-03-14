package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for createDeleteLink.spe diagram.
*/
public class MatchClasscreateDeleteLink {
	
	/**
	* matches and applies the in createDeleteLink.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas();
		ItemPO i2PO = i1PO.hasNext().hasValue(3);
		
		//match objects of subgroups 
		
		//matching missing links to known
		thisPO.hasHas(i2PO);
		
		//update model 
		i2PO.startCreate().hasNext(i1PO).endCreate();
		i1PO.startDestroy().hasNext(i2PO).endDestroy();
	
		return ! thisPO.allMatches().isEmpty();
	}
}
