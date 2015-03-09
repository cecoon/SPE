package demo.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new demo.util.PersonCreator());
      jsonIdMap.withCreator(new demo.util.PersonPOCreator());
      jsonIdMap.withCreator(new demo.util.ItemCreator());
      jsonIdMap.withCreator(new demo.util.ItemPOCreator());

      return jsonIdMap;
   }
}
