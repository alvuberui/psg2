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
package org.springframework.samples.petclinic.user;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.clinic.ClinicService;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwner;
import org.springframework.samples.petclinic.clinicOwner.ClinicOwnerService;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.owner.PlanType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";

	private final OwnerService ownerService;
	
	private final UserService userService;
	
	private final ClinicOwnerService clinicOwnerService;
	
	private final ClinicService clinicService;

	@Autowired
	public UserController(OwnerService ownerService, UserService userService, ClinicOwnerService clinicOwnerService, ClinicService clinicService) {
		this.ownerService = ownerService;
		this.userService = userService;
		this.clinicOwnerService = clinicOwnerService;
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Owner owner = new Owner();
		model.put("owner", owner);
		return VIEWS_OWNER_CREATE_FORM;
	}
	
	@GetMapping(value = "/users/clinicOwner/new")
	public String initCreationClinicOwnerForm(Map<String, Object> model) {
		ClinicOwner clinicOwner = new ClinicOwner();
		model.put("clinicOwner", clinicOwner);
		return "/users/createClinicOwnerForm";
	}
	
	@GetMapping(value = "/user/updatePlan/{userId}/{plan}")
	public String updatePlan(@PathVariable("userId") String userId, @PathVariable("plan") int plan, Model model) {
		User user = userService.findUser(userId).get();
		Integer clinicOwnerId = clinicOwnerService.findClinicOwnerByUsername(user.getUsername()).get().getId();
		
		
		
		if(plan == 0) {
			if(clinicService.findClinicsByClinicOwner(clinicOwnerId).size() > 1) {
				return "redirect:/clinicOwner/plan";
			}else {
				user.setPlan(PlanType.BASIC);
				user.setPrice(20.0);
			}
		}
		else if(plan == 1) {
			user.setPlan(PlanType.ADVANCED);
			user.setPrice(30.0);
		}
		else {
			user.setPlan(PlanType.PRO);
			user.setPrice(40.0);
		}
		userService.saveUser(user);
		return "redirect:/clinicOwner/plan";
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Owner owner, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		}
		else {
			//creating owner, user, and authority
			this.ownerService.saveOwner(owner);
	
			
			return "redirect:/";
		}
	}
	
	@PostMapping(value = "/users/clinicOwner/new")
	public String processCreationClinicOwnerForm(@Valid ClinicOwner ClinicOwner, BindingResult result) {
		if (result.hasErrors()) {
			return "/users/createClinicOwnerForm";
		}
		else {
			//creating owner, user, and authority
			this.clinicOwnerService.save(ClinicOwner);
	
			
			return "redirect:/";
		}
	}

}
