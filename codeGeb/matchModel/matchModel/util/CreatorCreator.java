package matchModel.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new matchModel.util.PersonCreator());
      jsonIdMap.withCreator(new matchModel.util.PersonPOCreator());
      jsonIdMap.withCreator(new matchModel.util.ItemCreator());
      jsonIdMap.withCreator(new matchModel.util.ItemPOCreator());

      return jsonIdMap;
   }
}
