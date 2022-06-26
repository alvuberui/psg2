package org.springframework.samples.petclinic.clinicOwner;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicOwnerRepository extends CrudRepository<ClinicOwner, Integer>{
	
	@Query("SELECT cw FROM ClinicOwner cw where cw.user.username = :username") 
	Optional<ClinicOwner> findClinicOwnerByUsername(@Param("username") String username);
}
