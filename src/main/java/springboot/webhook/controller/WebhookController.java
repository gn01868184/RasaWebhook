package springboot.webhook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import ntou.cs.springboot.entity.Pharmacy;
import ntou.cs.springboot.model.MaskHandler;
import springboot.webhook.model.RasaInput;
import springboot.webhook.model.RasaOutput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Controller
public class WebhookController {
	private static final String List = null;
	private static final int String = 0;

	@ResponseBody
    @PostMapping(path="/webhook", produces="application/json")
    public Map<String, List<Map<String, String>>> test(@RequestBody Map<String, Object> json) {
		
		RasaOutput rasaOutput = new RasaOutput();
		RasaInput rasaInput = new RasaInput();
		
		
		
		System.out.println("WebHook entities JSON: " + rasaInput.getEntities(json));
	    System.out.println("WebHook intent : " + rasaInput.getIntent(json));
	    
	    String nextAction = (String) json.get("next_action");
	    
	    switch (nextAction) {
	    	case "action_request_mask":
	    		String cityZone = "";
	    		String pharmacyName = "";
	    		try {
	    			cityZone = (java.lang.String) rasaInput.getEntities(json).get("cityZone");
	    			pharmacyName = (java.lang.String) rasaInput.getEntities(json).get("pharmacyName");
		    		MaskHandler handler = new MaskHandler();
					handler.initialize();
		    		List<Pharmacy> filteredClinicList = handler.findPharmacies(pharmacyName, cityZone);
		    		String maskInformation = "";
		    		for(int i=0;i<filteredClinicList.size();i++) {
		    			maskInformation = maskInformation + filteredClinicList.get(i).getName() + "剩餘" + filteredClinicList.get(i).getNumberOfAdultMasks() + "\n";
		    		}
		    		System.out.println(maskInformation);
		    		Map<String, List<Map<String, String>>> data = rasaOutput.output(maskInformation);
		    		return data;		
	    		} catch (IOException | URISyntaxException e) {
	    			// TODO Auto-generated catch block
	    			System.err.println(e);
	    		}
	    }
	    
	    Map<String, List<Map<String, String>>> nullText = rasaOutput.output("No response found");
        return nullText;
    }
}
