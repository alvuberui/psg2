package org.springframework.samples.petclinic.clinic;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwner;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwnerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClinicController {
	
    private final ClinicService clinicService;
    private final ClinicOwnerService clinicOwnerService;
    
    @Autowired
	public ClinicController(ClinicService clinicService, ClinicOwnerService clinicOwnerService) {
    	this.clinicService = clinicService;
    	this.clinicOwnerService = clinicOwnerService;
    }
	
	@GetMapping("/clinic/{clinicId}/delete")
	public String deleteClinic(@PathVariable("clinicId") int clinicId) {
		clinicService.deleteClinic(clinicId);
		return "redirect:/clinicOwner/plan";
	}
	
	@GetMapping(value = "/clinic/new")
	public String initCreationForm(ModelMap model) {
		Clinic clinic = new Clinic();

		model.put("clinic", clinic);
		return "clinic/createClinicForm";
	}
	
	@PostMapping(value = "/clinic/new")
	public String processCreationForm(@Valid Clinic clinic, BindingResult result, ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        ClinicOwner cw = clinicOwnerService.findClinicOwnerByUsername(username).get();
		
		if (result.hasErrors()) {
			model.put("clinic", clinic);
			return "clinic/createClinicForm";
		}
		else {
        	clinic.setClinicOwner(cw);
        	this.clinicService.save(clinic);
            return "redirect:/clinicOwner/plan";
		}
	}
}