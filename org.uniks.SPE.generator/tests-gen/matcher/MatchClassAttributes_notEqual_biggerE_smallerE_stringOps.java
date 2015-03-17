package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for Attributes_notEqual_biggerE_smallerE_stringOps.spe diagram.
*/
public class MatchClassAttributes_notEqual_biggerE_smallerE_stringOps {
	
	/**
	* matches and applies the in Attributes_notEqual_biggerE_smallerE_stringOps.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		ItemPO i1PO = thisPO.hasHas().startNAC().hasValue(1).endNAC();
		ItemPO i2PO = thisPO.hasHas().hasValue(Integer.MIN_VALUE, 1);
		ItemPO i3PO = thisPO.hasHas().hasValue(1 ,Integer.MAX_VALUE);
		PersonPO p2PO = thisPO.hasCustomer().hasName("alfred");
		PersonPO p3PO = thisPO.hasCustomer().startNAC().hasName("alfred").endNAC();
		PersonPO p1PO = thisPO.hasCustomer().has(new Condition<Object>() {
		           @Override
		           public boolean check(Object value) {
		               if(value instanceof Person){
		                   boolean matches = ((Person)value).getName().matches("k+.*");
		                   return matches;
		               }
		               return false;
		            }
		        })
		;
		
		//match objects of subgroups 
		
		//matching missing links to known
		
		//update model 
		boolean hasMatch = thisPO.getHasMatch();
		if(hasMatch){
		}
		return hasMatch;
	}
}
