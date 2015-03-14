package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for CreateObjectAndLink.spe diagram.
*/
public class MatchClassCreateObjectAndLink {
	
	/**
	* matches and applies the in CreateObjectAndLink.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups 
		
		//matching missing links to known
		
		//update model 
		 ItemPO i2PO = new ItemSet().with(new Item()).hasItemPO();
		i1PO.startCreate().hasNext(i2PO).endCreate();
		i2PO.startCreate().createValue(4).endCreate();
	
		return ! thisPO.allMatches().isEmpty();
	}
}
