package org.springframework.samples.petclinic.cause;


import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CauseRepository extends CrudRepository<Cause, Integer> {

    @Query(value = "SELECT * FROM Causes where id LIKE ?1", nativeQuery = true) 
	Optional<Cause> findCauseById(int id);

}
