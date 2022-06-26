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
package org.springframework.samples.petclinic.vet;


import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;
	private static final String VIEWS_VETS_CREATE_FORM = "vets/createVetForm";
	private static final String VIEWS_VETS = "redirect:/vets/";
	
	@Autowired
	public VetController(VetService clinicService) {
		this.vetService = clinicService;
	}
	

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}
	

	@GetMapping("/vets/{vetId}/delete")
	public String deleteVet(@PathVariable("vetId") int vetId) {
		vetService.deleteVet(vetId);
		return VIEWS_VETS;
	}

	@GetMapping(value = "/vets/new")
	public String initCreationForm(Map<String, Object> model) {
		Vet vet = new Vet();
		Collection<Specialty> s = vetService.findAllSpecialties();
		model.put("types", s);
		model.put("vet", vet);
		return VIEWS_VETS_CREATE_FORM;
	}
	
	@PostMapping(value = "/vets/new")
	public String processCreationForm(@ModelAttribute("vet") @Valid Vet vet, BindingResult result, Model model, String[] specialties) {
		if (result.hasErrors()) {
			return VIEWS_VETS_CREATE_FORM;
		}
		else {
			if(specialties != null) {
				int i = specialties.length;
				while(i>0) {
					i -= 1;
					Specialty specialty = vetService.findSpecialtyByName(specialties[i]);
					if(specialty != null) {
						vet.addSpecialty(specialty);
					}
				}
			}
			this.vetService.saveVet(vet);
			
			return VIEWS_VETS;
		}
	}
	
	@GetMapping(value = "/vets/{vetId}/")
	public String initUpdateOwnerForm(@PathVariable("vetId") int vetId, Model model, RedirectAttributes redirectAttrs) {
		
		Optional<Vet> vet = this.vetService.findVetById(vetId);
		if(!vet.isPresent()){
            redirectAttrs.addFlashAttribute("message", "Veterinario no encontrado");
            return VIEWS_VETS;
        }
		
		Collection<Specialty> s = vetService.findAllSpecialties();
		model.addAttribute("types", s);
		model.addAttribute(vet.get());
		return VIEWS_VETS_CREATE_FORM;
	}
	
	@PostMapping(value = "/vets/{vetId}/")
	public String processUpdateVetForm(@Valid Vet vet, BindingResult result,
			@PathVariable("vetId") int vetId,  String[] specialties) {
		if (result.hasErrors()) {
			return VIEWS_VETS_CREATE_FORM;
		}
		else {
			vet.setId(vetId);
			int i = specialties.length;
			while(i>0) {
				i -= 1;
				Specialty specialty = vetService.findSpecialtyByName(specialties[i]);
				if(specialty != null) {
					vet.addSpecialty(specialty);
				}
			}
			this.vetService.saveVet(vet);
			return VIEWS_VETS;
		}

	}

}
