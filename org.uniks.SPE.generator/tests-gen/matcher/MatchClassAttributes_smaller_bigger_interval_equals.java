package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for Attributes_smaller_bigger_interval_equals.spe diagram.
*/
public class MatchClassAttributes_smaller_bigger_interval_equals {
	
	/**
	* matches and applies the in Attributes_smaller_bigger_interval_equals.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas().hasValue(2 - 1 ,Integer.MAX_VALUE);
		ItemPO i2PO = thisPO.hasHas().hasValue(3 - 1, 1 + 1);
		ItemPO i4PO = thisPO.hasHas().hasValue(Integer.MIN_VALUE, 3 + 1);
		ItemPO i3PO = thisPO.hasHas().hasValue(3);
		
		//match objects of subgroups 
		
		//matching missing links to known
		
		//update model 
		boolean hasMatch = thisPO.getHasMatch();
		if(hasMatch){
		}
		return hasMatch;
	}
}
