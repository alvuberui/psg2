package org.springframework.samples.petclinic.clinic;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicService {
	
	@Autowired
	private ClinicRepository clinicRepository;
	
	public Collection<Clinic> findClinicsByClinicOwner(int id){
        return clinicRepository.findClinicsByClinicOwner(id);
    }
	
	@Transactional
	@Modifying
	public void deleteClinic(int clinicId) {
		clinicRepository.deleteClinic(clinicId);
	}

	public void save(@Valid Clinic clinic) {
		this.clinicRepository.save(clinic);
	}
	
}
