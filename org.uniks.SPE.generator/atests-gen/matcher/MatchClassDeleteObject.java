package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for DeleteObject.spe diagram.
*/
public class MatchClassDeleteObject {
	
	/**
	* matches and applies the in DeleteObject.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas();
		ItemPO i2PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups 
		
		//matching missing links to known
		i2PO.hasNext(i1PO);
		
		//update model 
		i1PO.startCreate().createValue(5).endCreate();
		i1PO.destroy();
	
		return ! thisPO.allMatches().isEmpty();
	}
}
