package org.springframework.samples.petclinic.adoption;


import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;



public interface AdoptionApplicationRepository extends  CrudRepository<AdoptionApplication, Integer>{

	
	@Query(name = "SELECT a FROM Adoption_application a WHERE a.adoption_request_id =:adoptionRequestId", nativeQuery = true)
	public Collection<AdoptionApplication> findAdoptionApplicationByAdoptionRequestId(@Param("adoptionRequestId") int adoptionRequestId);
	
	@Transactional
	@Modifying
	@Query(name = "DELETE FROM Adoption_application a WHERE a.adoption_request_id =:adoptionRequestId")
	public void deleteAdoptionApplicationById(@Param("adoptionRequestId") int adoptionRequestId);
	
	@Modifying
	@Query(name = "DELETE FROM Adoption_application a WHERE a.owner_id =:owner_id")
	public void deleteAdoptionApplicationByOwnerId(@Param("owner_id") int ownerId);
	
	@Query(name = "SELECT a FROM Adoption_application a WHERE a.owner_id =:owner_id", nativeQuery = true)
	public Collection<AdoptionApplication> findAdoptionApplicationByOwnerId(@Param("owner_id") int ownerId);
	
}
