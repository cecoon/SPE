package model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import model.Store;

public class StorePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StorePO(new Store[]{});
      } else {
          return new StorePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return model.util.CreatorCreator.createIdMap(sessionID);
   }
}
