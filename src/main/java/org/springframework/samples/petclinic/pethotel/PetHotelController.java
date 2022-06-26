package org.springframework.samples.petclinic.pethotel;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PetHotelController {

	private static final String FORM_PET_HOTEL = "petHotel/createOrUpdatePetHotelForm";

	@Autowired
	private final PetHotelService petHotelService;
	private final PetService petService;

	@Autowired
	public PetHotelController(PetHotelService petHotelService, PetService petService, UserService userService) {
		this.petHotelService = petHotelService;
		this.petService = petService;
	}
	

	@GetMapping(value = { "/petHotel" })
	public String getAllPetHotel(Map<String, Object> model) {
		Iterable<PetHotel> petHotel = this.petHotelService.getAll();
		
		model.put("listPetHotel", petHotel);
		
		return "petHotel/petHotelList";
	}
	
	@ModelAttribute("pet")
    public Iterable<Pet> populatePetTypes() {
        return this.petHotelService.getAllPets();
    }
	
	@InitBinder("petHotel")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetHotelValidator());
	}
	@GetMapping(value = { "/petHotel/new" })
	public String newAchievementForm(Map<String, Object> model) {
		PetHotel petHotel = new PetHotel();
		model.put("petHotel", petHotel);
		Iterable<Pet> pets = petService.findAllpets();
		model.put("pets", pets);
		return FORM_PET_HOTEL;
	}

	@PostMapping(value = "/petHotel/new")
	public String processCreationForm(@Valid PetHotel petHotel, BindingResult result, ModelMap model) {
		if (result.hasErrors() || petHotelService.duplicatedPet(petHotel.getPet())) {
			model.put("petHotel", petHotel);
			Iterable<Pet> pets = petService.findAllpets();
			if(petHotelService.duplicatedPet(petHotel.getPet())) {
				model.addAttribute("duplicatedPet", "No puedes tener dos reservas de una misma mascota a la vez");
			}
			model.put("pets", pets);
			return FORM_PET_HOTEL;
		} else {
			petHotelService.save(petHotel);
			return "redirect:/petHotel";
		}
	}

	
	@GetMapping(value = { "/petHotel/delete/{id_pet_hotel}" })
	public String deletePetHotel(@PathVariable("id_pet_hotel") int idPetHotel) {
		petHotelService.deletePetHotel(idPetHotel);
		return "redirect:/petHotel";
	}
}