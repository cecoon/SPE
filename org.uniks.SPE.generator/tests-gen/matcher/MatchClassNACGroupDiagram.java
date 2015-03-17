package matcher; 

import model.*; 
import model.util.*;	
import de.uniks.networkparser.logic.Condition;

@SuppressWarnings("all")
/**
* generated Matchclass for NACGroupDiagram.spe diagram.
*/
public class MatchClassNACGroupDiagram {
	
	/**
	* matches and applies the in NACGroupDiagram.spe action to a given start object.
	* @returns true if match was successfull
	*/
	public boolean execute(Store start){
		//grep start point
		StoreSet startSet = new StoreSet().with(start);
		StorePO thisPO = startSet.hasStorePO();
		
		//matching objects of root grp
		
		//match objects of subgroups 
		//subgroup
		thisPO.startNAC();
		ItemPO i1PO = thisPO.hasHas().hasValue(2);
		ItemPO i2PO = i1PO.hasNext();
		thisPO.hasHas(i1PO);
		thisPO.hasHas(i2PO);
		thisPO.endNAC();
		
		//matching missing links to known
		
		//update model 
		boolean hasMatch = thisPO.getHasMatch();
		if(hasMatch){
		}
		return hasMatch;
	}
}
