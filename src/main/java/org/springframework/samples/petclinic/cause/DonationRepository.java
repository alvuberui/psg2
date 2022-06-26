package org.springframework.samples.petclinic.cause;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface DonationRepository extends CrudRepository<Donation, Integer> {
	
	@Transactional
	@Modifying
	@Query(name = "DELETE FROM donation d WHERE d.owner_id =:owner_id")
	public void deleteDonationByOwnerId(@Param("owner_id") int ownerId);
	
	@Query(name = "SELECT d FROM donation d WHERE d.owner_id =:owner_id", nativeQuery = true)
	public Collection<Donation> findAllDonationByOwnerId(@Param("owner_id") int ownerId);
}
