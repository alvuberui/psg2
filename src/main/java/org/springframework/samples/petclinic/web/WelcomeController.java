package org.springframework.samples.petclinic.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwner;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwnerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@Autowired
    private ClinicOwnerService clinicOwnerService;
	
	@GetMapping({"/","/welcome"})
	public String welcome(Map<String, Object> model, ModelMap modelMap) {
		   
	String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
	try {
		ClinicOwner cw = clinicOwnerService.findClinicOwnerByUsername(username).get();
		modelMap.addAttribute("clinicOwner", cw);
	} catch (Exception e){
		return "welcome";
	}
	      
	    return "welcome"; 
	  }
}
