package springboot.webhook.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RasaInput {
    public String getIntent(Map<String, Object> json) {

    	String intentName = (String) ((Map<String, Object>) ((Map<String, Object>) ((Map<String, Object>) json.get("tracker")).get("latest_message")).get("intent")).get("name");
        return intentName;
    }
    public Map<String, Object> getEntities(Map<String, Object> json) {

    	List<Map<String, Object>> entitiesList = (List<Map<String, Object>>) ((Map<String, Object>) ((Map<String, Object>) json.get("tracker")).get("latest_message")).get("entities");
    	Map<String, Object> entitiesJson = new HashMap<>();
    	
    	for(int i=0;i<entitiesList.size();i++) {
    		String entityName = (String) entitiesList.get(i).get("entity");
    		String entityValue = (String) entitiesList.get(i).get("value");
    		entitiesJson.put(entityName, entityValue);
    	}
    	
    	return entitiesJson;
    }
}
