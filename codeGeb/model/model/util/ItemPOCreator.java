package model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import model.Item;

public class ItemPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ItemPO(new Item[]{});
      } else {
          return new ItemPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return model.util.CreatorCreator.createIdMap(sessionID);
   }
}
