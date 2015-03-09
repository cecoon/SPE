package i.love.sdmlib.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new i.love.sdmlib.util.PersonCreator());
      jsonIdMap.withCreator(new i.love.sdmlib.util.PersonPOCreator());
      jsonIdMap.withCreator(new i.love.sdmlib.util.ItemCreator());
      jsonIdMap.withCreator(new i.love.sdmlib.util.ItemPOCreator());

      return jsonIdMap;
   }
}
