package org.springframework.samples.petclinic.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RestSpringBootController {
	
//	@Autowired(required=true)
//	RestTemplate restTemplate;
	
	
	
	@GetMapping(value = "/api/search/find")
	public String findForm(Map<String, Object> model) {
		model.put("search", new HashMap<String, Object>());
		return "api/searchFind";
	}

	@GetMapping(value = "/api/search/list")
	public String findApi(Map<String, Object> model, @RequestParam(value="query") String query) {
		String url2 = "https://google-search26.p.rapidapi.com/search?q=" + query +"&hl=es&tbs=qdr%3Aa&cr=countryES";
		String url = "https://google-search3.p.rapidapi.com/api/v1/search/q=" + query;
		model.put("query", url);
		model.put("query2", url2);
		return "api/searchList";
	}
	
	

}
