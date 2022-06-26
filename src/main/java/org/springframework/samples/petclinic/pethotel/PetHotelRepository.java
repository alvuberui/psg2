package org.springframework.samples.petclinic.pethotel;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.transaction.annotation.Transactional;



public interface PetHotelRepository extends  CrudRepository<PetHotel, Integer>{
	
	@Query("SELECT ph from PetHotel ph WHERE ph.pet.owner.user.username =: username")
	public Collection<PetHotel> findAllByUsername(@Param("username") String username);
	
	@Query("SELECT ph from PetHotel ph WHERE ph.pet.id =: petId")
	public List<PetHotel> findPetHotelByPetId(@Param("petId") Integer petId);
	
	@Query("SELECT ph from PetHotel ph WHERE ph.pet =: pet")
	public List<PetHotel> findPetHotelByPet(@Param("pet") Pet pet);
	
	@Transactional
	@Modifying
	@Query(name = "DELETE FROM PetHotel ph WHERE ph.pet_id =:pet_id")
	public void deletePetHotelByPetId(@Param("pet_id") int petId);
}
