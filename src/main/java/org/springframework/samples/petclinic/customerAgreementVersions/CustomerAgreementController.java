package org.springframework.samples.petclinic.customerAgreementVersions;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerAgreementController {
	
	private final CustomerAgreementService customerAgreementService;
	
	@Autowired
	public CustomerAgreementController(CustomerAgreementService customerAgreementService) {
		this.customerAgreementService = customerAgreementService;
	}
	
	
	@GetMapping({"/custumer-agreement"})
	public String custumerAgreement(Map<String, Object> model) {	    
		
		List<CustomerAgreementVersions>CAv = customerAgreementService.findAll();
		if(CAv.size()==3) {
			model.put("firstPlan", CAv.get(0).getCurrentplan().getId());
			model.put("secondPlan", CAv.get(1).getCurrentplan().getId());
			model.put("actualPlan", CAv.get(2).getCurrentplan().getId());
		}
		if(CAv.size()==2) {
			model.put("firstPlan", 9);
			model.put("secondPlan", CAv.get(0).getCurrentplan().getId());
			model.put("actualPlan", CAv.get(1).getCurrentplan().getId());
		}
		if(CAv.size()==1) {
			model.put("firstPlan", 9);
			model.put("secondPlan", 9);
			model.put("actualPlan", CAv.get(0).getCurrentplan().getId());
		}
		
		boolean isAut = false;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal instanceof UserDetails) {
			isAut = true;
		}
		model.put("isAut", isAut);
		return "plan/costumerAgreementList";
	}
}
