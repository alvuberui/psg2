package org.springframework.samples.petclinic.adoption;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface AdoptionRequestRepository extends  CrudRepository<AdoptionRequest, Integer>{

	
	@Query(name = "SELECT a FROM Adoption_request a WHERE a.pets =:pet")
	public Optional<AdoptionRequest> findAdoptionRequestByPetId(@Param("pet") int petId);
	
}
