package matcher;

import model.*; 
import model.util.*;
 
public class MatchClassMyDiagram {
	
	public StoreSet findMatch(Store start){
		StoreSet startSet = new StoreSet().with(start);
		StorePO this_1687662712 = startSet.hasStorePO();
			
		PersonPO p_1103243338 = this_1687662712.hasCustomer().hasBalance(42);
		ItemPO i1_1600667055 = p_1103243338.hasHas().hasValue(23);
		ItemPO i2_137460818 = i1_1600667055.hasNext().hasValue(19);
		this_1687662712.hasHas(i1_1600667055);
		p_1103243338.hasHas(i2_137460818);
		return this_1687662712.allMatches();
	}
}  
