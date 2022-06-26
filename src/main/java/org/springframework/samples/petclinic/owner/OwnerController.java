/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.adoption.AdoptionRequest;
import org.springframework.samples.petclinic.adoption.AdoptionRequestService;
import org.springframework.samples.petclinic.cause.CauseService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class OwnerController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
	private static final String VIEW_OWNERS = "redirect:/owners/";

	private final OwnerService ownerService;
	private final CauseService causeService;
	private final PetService petService;
	private final AdoptionRequestService adoptionRequestService;
	private final UserService userService;
	

	@Autowired
	public OwnerController(OwnerService ownerService,
			PetService petService,
			UserService userService,
			AuthoritiesService authoritiesService,
			AdoptionRequestService adoptionRequestService,
			CauseService causeService) {
		this.ownerService = ownerService;
		this.petService = petService;
		this.adoptionRequestService = adoptionRequestService;
		this.userService = userService;
		this.causeService = causeService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/owners/new")
	public String initCreationForm(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating owner, user and authorities
			this.ownerService.saveOwner(owner);
			
			return VIEW_OWNERS + owner.getId();
		}
	}

	@GetMapping(value = "/owners/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("owner", new Owner());
		return "owners/findOwners";
	}

	@GetMapping(value = "/owners")
	public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

		// allow parameterless GET request for /owners to return all records
		if (owner.getLastName() == null) {
			owner.setLastName(""); // empty string signifies broadest possible search
		}

		// find owners by last name
		Collection<Owner> results = this.ownerService.findOwnerByLastName(owner.getLastName());
		if (results.isEmpty()) {
			// no owners found
			result.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		}
		else if (results.size() == 1) {
			// 1 owner found
			owner = results.iterator().next();
			return VIEW_OWNERS + owner.getId();
		}
		else {
			// multiple owners found
			model.put("selections", results);
			return "owners/ownersList";
		}
	}

	@GetMapping(value = "/owners/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
		Owner owner = this.ownerService.findOwnerById(ownerId);
		model.addAttribute(owner);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/owners/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
			@PathVariable("ownerId") int ownerId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.setId(ownerId);
			this.ownerService.saveOwner(owner);
			return "redirect:/owners/{ownerId}";
		}
	}

	/**
	 * Custom handler for displaying an owner.
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/owners/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		Iterable<AdoptionRequest> listAdoption =  adoptionRequestService.findAll();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> userOpt = userService.findUser(userDetails.getUsername());
		if(userOpt.isPresent()){
			User user = userOpt.get();
		List<String>listAuthorities = new ArrayList<>();
		for(Authorities a : user.getAuthorities()) {
			listAuthorities.add(a.getAuthority());
		}
		boolean isAdmin = false;
		if(listAuthorities.contains("admin")) {
			isAdmin = true;
		}
		else {
			Integer oId = ownerService.findByUserName(user.getUsername());
			Owner o = ownerService.findOwnerById(oId);
			if(ownerService.findOwnerById(ownerId)==o) {
				isAdmin = true;
			}
		}
		mav.addObject("listAdoption", listAdoption);
		mav.addObject("isAdmin", isAdmin);
		}
		
		mav.addObject(this.ownerService.findOwnerById(ownerId));
		return mav;
	}
	
	@GetMapping("/owners/{ownerId}/delete")
	public String deleteOwner(@PathVariable("ownerId") int ownerId) {
		deletePet(ownerId);
		deleteDonation(ownerId);
		deleteAdoptionApplication(ownerId);
		ownerService.deleteOwner(ownerId);
		return VIEW_OWNERS;
	}
	
	public void deletePet(@PathVariable("ownerId") int ownerId) {
		Owner ow = ownerService.findOwnerById(ownerId);
		List<Pet> ls = ow.getPets();
		for(int i=0; i < ls.size(); i++) {
			deleteAdoptionRequest(ls.get(i).getId());
			petService.deletePetById(ls.get(i).getId());
		}
	}
	
	public void deleteDonation(@PathVariable("ownerId") int ownerId) {
		causeService.deleteDonationByOwnerId(ownerId);
	}

	public void deleteAdoptionApplication(@PathVariable("ownerId") int ownerId) {
		adoptionRequestService.deleteAdoptionApplicationByOwnerId(ownerId);
	}
	
	public void deleteAdoptionRequest(int petId) {
		adoptionRequestService.deleteAdoptionRequestByPetId(petId);
	}
	
}
