package org.springframework.samples.petclinic.cause;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.util.SecurityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/causes")
public class CauseController {

	private static final String MODELCAUSES = "causes";
	
    @Autowired
    private CauseService causeService;
    @Autowired
    private SecurityService securityService;

    @Autowired
	public CauseController(CauseService causeService) {
		this.causeService = causeService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @GetMapping()
    public String showCauseList(ModelMap modelMap, HttpServletRequest request){
        String view = "causes/causes";
        Iterable<Cause> causes = this.causeService.findAll();
        modelMap.addAttribute(MODELCAUSES, causes);

        boolean isOwner = securityService.isOwner();
        if(isOwner) {
            securityService.insertOwnerIdInModel(modelMap);
        }

        return view;
    }

    @GetMapping(path="/details/{causeId}")
    public String showCauseDetails(@PathVariable("causeId") int causeId, ModelMap modelMap, RedirectAttributes redirectAttrs) {
        String view = "/causes/causeDetails";
        
        Optional<Cause> cause = causeService.findCauseById(causeId);
        if(!cause.isPresent()){
            redirectAttrs.addFlashAttribute("message", "Causa no encontrada");
            return "redirect:/causes";
        }

        List<Donation> donations = cause.get().getDonations().stream().collect(Collectors.toList());
        Double budgetAchieved = donations.stream().mapToDouble(x-> x.getAmount()).sum();
        modelMap.addAttribute("cause", cause.get());
        modelMap.addAttribute("donations", donations);
        modelMap.addAttribute("budgetAchieved", budgetAchieved.toString());

        boolean isOwner = securityService.isOwner();
        if(isOwner) {
            securityService.insertOwnerIdInModel(modelMap);
        }

        return view;
    }

    @GetMapping(value = "/new")
	public String initCreationCauseForm(ModelMap model) {
        String view = "/causes/createCauseForm";
        Cause cause = new Cause();
		model.put(MODELCAUSES, cause);
		return view;
	}

    @PostMapping(value = "/save")
	public String processCreationForm(@Valid Cause cause, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put(MODELCAUSES, cause);
			return "/causes/createCauseForm";
		}
		else {
            causeService.save(cause);
            return "redirect:/causes";
		}
	}
    
}
