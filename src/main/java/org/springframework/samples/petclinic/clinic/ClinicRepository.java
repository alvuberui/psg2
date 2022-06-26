package org.springframework.samples.petclinic.clinic;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClinicRepository extends CrudRepository<Clinic, Integer>{
	
	@Query("SELECT c FROM Clinic c where c.clinicOwner.id = :id") 
	Collection<Clinic> findClinicsByClinicOwner(@Param("id") int id);
	
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Clinic clinic WHERE clinic.id=:clinicId")
	void deleteClinic(@Param("clinicId")int clinicId);
		
}
