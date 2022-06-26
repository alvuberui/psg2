package org.springframework.samples.petclinic.adoption;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.user.Authorities;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class AdoptionRequestController {
	
	private static final String CREATE_ADOPTION_APPLICATION = "adoption/createAdoptionApplicationForm";
	private static final String MODELADMIN = "isAdmin";
	private static final String ADMIN = "admin";

	private final AdoptionRequestService adoptionRequestService;
	private final UserService userService;
	private final OwnerService ownerService;
	

	@Autowired
	public AdoptionRequestController(AdoptionRequestService adoptionRequestService, UserService userService, OwnerService ownerService) {
		this.adoptionRequestService = adoptionRequestService;
		this.userService = userService;
		this.ownerService = ownerService;
	}

	@GetMapping(value = { "/adoptionRequest" })
	public String listAdoptionRequest(Map<String, Object> model, RedirectAttributes redirectAttrs) {
		Iterable<AdoptionRequest> listAdoptionRequest = adoptionRequestService.findAll();
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userService.findUser(userDetails.getUsername());
		
		if(!user.isPresent()){
            redirectAttrs.addFlashAttribute("message", "Usuario no encontrado");
            return "redirect:/welcome";
        }
		
		List<String> listAuthorities = new ArrayList<>();
		for(Authorities a : user.get().getAuthorities()) {
			listAuthorities.add(a.getAuthority());
		}
		boolean isAdmin = false;
		if(listAuthorities.contains(ADMIN)) {
			isAdmin = true;
		}
		model.put("listAdoptionRequest", listAdoptionRequest);
		model.put(MODELADMIN, isAdmin);
		return "adoption/adoptionRequestList";
	}
	
	@GetMapping(value = { "/adoptionApplication/{adoptionRequestId}" })
	public String listAdoptionApplication(Map<String, Object> model, @PathVariable("adoptionRequestId") int adoptionRequestId, RedirectAttributes redirectAttrs) {
		Iterable<AdoptionApplication> listAdoptionApplication = adoptionRequestService.findAdoptionApplicationsById(adoptionRequestId);
		Boolean anyAdoptionApplication = listAdoptionApplication.iterator().hasNext();
		String petName = adoptionRequestService.findPet(adoptionRequestId);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userService.findUser(userDetails.getUsername());
		
		if(!user.isPresent()){
            redirectAttrs.addFlashAttribute("message", "Usuario no encontrado");
            return "redirect:/welcome";
        }

		List<String> listAuthorities = new ArrayList<>();
		for(Authorities a : user.get().getAuthorities()) {
			listAuthorities.add(a.getAuthority());
		}
		boolean isAdmin = false;
		if(listAuthorities.contains(ADMIN)) {
			isAdmin = true;
		}
		else {
			Integer oId = ownerService.findByUserName(user.get().getUsername());
			Owner o = ownerService.findOwnerById(oId);

			Optional<AdoptionRequest> adoptionRequest = adoptionRequestService.findById(adoptionRequestId);

			if(!adoptionRequest.isPresent()){
				redirectAttrs.addFlashAttribute("message", "Petición de adopción no encontrada");
				return "redirect:/adoptionRequest";
			}

			if(adoptionRequest.get().getPets().getOwner()==o) {
				isAdmin = true;
			}
		}
		model.put(MODELADMIN, isAdmin);
		model.put("listAdoptionApplication", listAdoptionApplication);
		model.put("anyAdoptionApplication", anyAdoptionApplication);
		model.put("petName", petName);
		return "adoption/adoptionApplicationList";
	}
	
	@GetMapping(value = { "adoptionRequest/delete/{adoptionRequestId}" })
	public String deleteAdoptionRequest(@PathVariable("adoptionRequestId") int adoptionRequestId) {
		adoptionRequestService.deleteAdoptionRequest(adoptionRequestId);
		return "redirect:/adoptionRequest";
	}

	@GetMapping(value = { "adoptionApplication/{adoptionRequestId}/delete/{adoptionApplicationId}" })
	public String deleteAdoptionApplication(@PathVariable("adoptionApplicationId") int adoptionApplicationId) {
		adoptionRequestService.deleteAdoptionApplication(adoptionApplicationId);
		return "redirect:/adoptionApplication/{adoptionRequestId}";
	}
	
	@GetMapping(value = { "adoptionApplication/{adoptionRequestId}/accept/{adoptionApplicationId}" })
	public String acceptAdoptionApplication(@PathVariable("adoptionRequestId") int adoptionRequestId, @PathVariable("adoptionApplicationId") int adoptionApplicationId) {
		adoptionRequestService.acceptRequest(adoptionRequestId, adoptionApplicationId);
		return "redirect:/adoptionRequest";
	}
	
	@GetMapping(value = {"adoptionRequest/new/{petId}"})
	public String newAdoptionRequest(Map<String, Object> model, @PathVariable("petId") int petId) {
		adoptionRequestService.newPetInAdoption(petId);
		return "redirect:/adoptionRequest";
	}
	
	@GetMapping(value = {"adoptionApplication/new/{adoptionRequestId}"})
	public String newAdoptionApplication(Map<String, Object> model, @PathVariable("adoptionRequestId") int adoptionRequestId, RedirectAttributes redirectAttrs) {
		AdoptionApplication adoptionApplication = new AdoptionApplication();
		Collection<Owner> owners = ownerService.findOwnerByLastName("");
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<User> user = userService.findUser(userDetails.getUsername());
		
		if(!user.isPresent()){
            redirectAttrs.addFlashAttribute("message", "Usuario no encontrado");
            return "redirect:/welcome";
        }
		
		List<String> listAuthorities = new ArrayList<>();
		for(Authorities a : user.get().getAuthorities()) {
			listAuthorities.add(a.getAuthority());
		}
		boolean isAdmin = false;
		if(listAuthorities.contains(ADMIN)) {
			isAdmin = true;
		}
		else {
			Integer oId = ownerService.findByUserName(user.get().getUsername());
			Owner o = ownerService.findOwnerById(oId);
			model.put("owner", o);
		}
		model.put("adoptionRequestId", adoptionRequestId);
		model.put("adoptionApplication", adoptionApplication);
		model.put("owners", owners);
		model.put(MODELADMIN, isAdmin);
		return CREATE_ADOPTION_APPLICATION;
	}
	
	@PostMapping(value = "adoptionApplication/new/{adoptionRequestId}")
	public String processCreationForm(@Valid AdoptionApplication adoptionApplication, Map<String, Object> model, @PathVariable("adoptionRequestId") int adoptionRequestId, BindingResult result, RedirectAttributes redirectAttrs) {
		if (result.hasErrors()) {
			model.put("adoptionApplication", adoptionApplication);
			return CREATE_ADOPTION_APPLICATION;
		} else {

			Optional<AdoptionRequest> adoptionRequest = adoptionRequestService.findById(adoptionRequestId);
			if(!adoptionRequest.isPresent()){
				redirectAttrs.addFlashAttribute("message", "Solicitud de adopción no encontrado");
				return "redirect:/adoptionRequest";
			}

			adoptionApplication.setAdoptionRequest(adoptionRequest.get());
			adoptionRequestService.save(adoptionApplication);
			return "redirect:/adoptionApplication/" + adoptionRequestId;
		}
	}
	
	
}
