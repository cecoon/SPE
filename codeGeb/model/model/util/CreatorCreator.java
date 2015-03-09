package model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new model.util.PersonCreator());
      jsonIdMap.withCreator(new model.util.PersonPOCreator());
      jsonIdMap.withCreator(new model.util.ItemCreator());
      jsonIdMap.withCreator(new model.util.ItemPOCreator());

      jsonIdMap.withCreator(new model.util.StoreCreator());
      jsonIdMap.withCreator(new model.util.StorePOCreator());
      return jsonIdMap;
   }
}
