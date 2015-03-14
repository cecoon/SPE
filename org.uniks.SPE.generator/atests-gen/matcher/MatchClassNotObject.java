package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for NotObject.spe diagram.
*/
public class MatchClassNotObject {
	
	/**
	* matches and applies the in NotObject.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO iPO = thisPO.startNAC().hasHas().hasValue(2).endNAC();
		
		//match objects of subgroups 
		
		//matching missing links to known
		
		//update model 
	
		return ! thisPO.allMatches().isEmpty();
	}
}
