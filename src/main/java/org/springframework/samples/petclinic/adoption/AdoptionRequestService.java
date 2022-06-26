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
package org.springframework.samples.petclinic.adoption;


import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class AdoptionRequestService {

	private AdoptionRequestRepository adoptionRequestRepository;
	private AdoptionApplicationRepository adoptionApplicationRepository;
	private PetService petService;

	@Autowired
	public AdoptionRequestService(AdoptionRequestRepository adoptionRequestRepository,PetService petService, AdoptionApplicationRepository adoptionApplicationRepository) {
		this.adoptionRequestRepository = adoptionRequestRepository;
		this.petService = petService;
		this.adoptionApplicationRepository = adoptionApplicationRepository;
	}

	@Transactional
	public void adoptionRequestRepository(AdoptionRequest adoptionRequest) throws DataAccessException {
		adoptionRequestRepository.save(adoptionRequest);
	}
	
	@Transactional
	@Modifying
	public void acceptRequest(Integer adoptionRequestId, Integer adoptionAplicationId) throws DataAccessException {
		Optional<AdoptionRequest> adoptionRequest = adoptionRequestRepository.findById(adoptionRequestId);
		if(!adoptionRequest.isPresent()){
            throw new DataAccessException("AdoptionRequest not found") {
			};
        }

		Pet petToUpdate = adoptionRequest.get().getPets();

		Optional<AdoptionApplication> adoptionApplication = adoptionApplicationRepository.findById(adoptionAplicationId);
		if(!adoptionApplication.isPresent()){
            throw new DataAccessException("AdoptionApplication not found") {
			};
        }

		Owner newOwner = adoptionApplication.get().getOwner();
		petToUpdate.setOwner(newOwner);
		try {
			petService.savePet(petToUpdate);
		} catch (DataAccessException | DuplicatedPetNameException e) {
			e.printStackTrace();
		}
		deleteAdoptionRequest(adoptionRequestId);
		
	}
	
	@Transactional
	public void deleteAdoptionRequest(Integer adoptionRequest) {
		Collection<AdoptionApplication>listAplication = adoptionApplicationRepository.findAdoptionApplicationByAdoptionRequestId(adoptionRequest);
		for(AdoptionApplication a:listAplication) {
			adoptionApplicationRepository.deleteById(a.getId());
		}
		adoptionRequestRepository.deleteById(adoptionRequest);
	}

	public Iterable<AdoptionRequest> findAll(){
		return adoptionRequestRepository.findAll();
	}

	public Iterable<AdoptionApplication> findAllAdoptionApplications() {
		return adoptionApplicationRepository.findAll();
	}
	
	public Optional<AdoptionRequest> findById(int adoptionRequestId){
		return adoptionRequestRepository.findById(adoptionRequestId);
	}
	
	public Collection<AdoptionApplication> findAdoptionApplicationsById(int adoptionRequestId) {
		return adoptionApplicationRepository.findAdoptionApplicationByAdoptionRequestId(adoptionRequestId);
	}

	public void deleteAdoptionApplication(int adoptionApplicationId) {
		adoptionApplicationRepository.deleteById(adoptionApplicationId);
	}
	
	public String findPet(int adoptionRequestId) {
		Optional<AdoptionRequest> adoptionRequest = adoptionRequestRepository.findById(adoptionRequestId);
		if(!adoptionRequest.isPresent()){
            throw new DataAccessException("AdoptionRequest not found") {
			};
        }

        return adoptionRequest.get().getPets().getName();
    }
	
	public void newPetInAdoption(int petId) {
		Pet p= petService.findPetById(petId);
		AdoptionRequest adoptReq = new AdoptionRequest();
		adoptReq.setPets(p);
		adoptionRequestRepository.save(adoptReq);
	}
	
	public void save(AdoptionApplication adopApplication) {
		adoptionApplicationRepository.save(adopApplication);
	}
	
	
	@Transactional
	public void deleteAdoptionApplicationByOwnerId(Integer ownerId) {		
		Collection<AdoptionApplication>listAplication = adoptionApplicationRepository.findAdoptionApplicationByOwnerId(ownerId);
		for(AdoptionApplication a:listAplication) {
			adoptionApplicationRepository.deleteById(a.getId());
		}
	}
	
	@Transactional
	public void deleteAdoptionRequestByPetId(Integer petId) {
		Optional<AdoptionRequest> adoptionR = adoptionRequestRepository.findAdoptionRequestByPetId(petId);
		if(adoptionR.isPresent()) {
			Collection<AdoptionApplication>listAplication = adoptionApplicationRepository.findAdoptionApplicationByAdoptionRequestId(adoptionR.get().getId());
			for(AdoptionApplication a:listAplication) {
				adoptionApplicationRepository.deleteById(a.getId());
			}
			adoptionRequestRepository.deleteById(adoptionR.get().getId());
		}
		
	}
	
}
