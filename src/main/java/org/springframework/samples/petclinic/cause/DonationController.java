package org.springframework.samples.petclinic.cause;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/donations")
public class DonationController {

    private static final String VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM = "causes/createOrUpdateDonationForm";

    @Autowired
    private CauseService causeService;
    @Autowired
    private OwnerService ownerService;


    @Autowired
	public DonationController(CauseService causeService, OwnerService ownerService) {
		this.causeService = causeService;
        this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("donation")
	public Donation loadCauseWithDonation(@PathVariable("causeId") int causeId, @PathVariable("ownerId") int ownerId) throws NoSuchElementException {
		Optional<Cause> cause = this.causeService.findCauseById(causeId);
		
        if(cause.isPresent()) {
			Donation donation = new Donation();
            cause.get().addDonation(donation);
			donation.setCause(cause.get());
			Owner owner = this.ownerService.findOwnerById(ownerId);
			donation.setOwner(owner);
            return donation;
        }
        else {
            throw new NoSuchElementException("The specified cause id does not exists");
        }
	}

    @GetMapping(value = "/new/cause/{causeId}/owner/{ownerId}")
	public String newDonationForm(Map<String, Object> model) {
		return VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/new/cause/{causeId}/owner/{ownerId}")
	public String processNewDonationForm(@Valid Donation donation, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.put("donation", donation);
			return VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
		} else {
			causeService.saveDonation(donation);
			return "redirect:/causes";
		}
	}
    
}
