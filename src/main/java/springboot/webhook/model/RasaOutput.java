package springboot.webhook.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RasaOutput {
    public Map<String, List<Map<String, String>>> output(String outputText) {

		List<Map<String, String>> events = new ArrayList<Map<String, String>>();
		Map<String, String> event = new HashMap<>();
		event.put("event", "action");
		events.add(event);
		
		List<Map<String, String>> responses = new ArrayList<Map<String, String>>();
		Map<String, String> text = new HashMap<>();
		text.put("text", outputText);
		responses.add(text);
		
		
        Map<String, List<Map<String, String>>> data = new HashMap<>();
        data.put("events", events);
        data.put("responses", responses);
        
        return data;
    }
}
