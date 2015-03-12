package org.uniks.spe.editor.features;
 

import model.IHasMatchTag;
import model.IHasOperation;
import model.MatchTag;
import model.Operations; 

public class Common {
     

    
    public static void setTagBasedOnPrefix(IHasMatchTag item, String prefixedString){
        if(prefixedString.contains("!")){
            item.setTag(MatchTag.NOT);
        } 
        if(prefixedString.contains("?")){
            item.setTag(MatchTag.OPTIONAL);
        }
        if(prefixedString.contains(".")){
            item.setTag(MatchTag.DEFAULT);
        } 
    }
    
    public static void setOperationBasedOnPrefix(IHasOperation item, String prefixedString){
        if(prefixedString.contains("+")){
            item.setOperation(Operations.CREATE);
        } else if(prefixedString.contains("-")){
            item.setOperation(Operations.DELETE);
        } else {
            item.setOperation(Operations.NOP);
        }   
    }

}
