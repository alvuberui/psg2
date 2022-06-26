package org.springframework.samples.petclinic.clinicOwner;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.clinic.Clinic;
import org.springframework.samples.petclinic.clinic.ClinicService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClinicOwnerController {
	
	@Autowired
    private ClinicOwnerService clinicOwnerService;
	
	@Autowired
    private ClinicService clinicService;
	

	@GetMapping(path="/clinicOwner/plan")
    public String showClinicOwnerDetails(ModelMap modelMap, RedirectAttributes redirectAttrs) {
        String view = "/clinicOwner/plan";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        ClinicOwner currentClinicOwner = clinicOwnerService.findClinicOwnerByUsername(username).get();
        modelMap.addAttribute("clinicOwner", currentClinicOwner);
        
        List<Clinic> clinics = (List<Clinic>) clinicService.findClinicsByClinicOwner(currentClinicOwner.getId());
        modelMap.addAttribute("clinics", clinics);
        modelMap.addAttribute("numberClinics", clinics.size());
        
        
        return view;
    }
}
