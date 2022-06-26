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
package org.springframework.samples.petclinic.pethotel;


import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetRepository;
import org.springframework.stereotype.Service;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class PetHotelService {

	@Autowired
	private PetHotelRepository petHotelRepository;
	
	@Autowired
	private PetRepository petRepository;
	
	public Iterable<PetHotel> getAll(){
		return petHotelRepository.findAll();
	}
	
	public Iterable<Pet> getAllPets(){
		return petRepository.findAll();
	}
	
	public void save(PetHotel petHotel) {
		petHotelRepository.save(petHotel);
	}

	public List<PetHotel> findPetHotelByPetId(Integer petId){
		return petHotelRepository.findPetHotelByPetId(petId);
	}
	
	public void deletePetHotel(Integer petHotelId) {
		petHotelRepository.deleteById(petHotelId);
	}

	public Collection<PetHotel> getAllByUsername(String username){
		return petHotelRepository.findAllByUsername(username);
	}
	
	public boolean duplicatedPet(Pet pet){
		boolean res = false;
		Iterable<PetHotel> petHotel = petHotelRepository.findAll();
		for(PetHotel ph:petHotel) {
			res = ph.getPet().equals(pet);
			if(res) {
				break;
			}
		}
		return res;
	}
	
}