package org.springframework.samples.petclinic.clinicOwner;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import org.springframework.samples.petclinic.user.AuthoritiesService;

import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClinicOwnerService {
	
	@Autowired
	private ClinicOwnerRepository clinicOwnerRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
    public Optional<ClinicOwner> findClinicOwnerByUsername(String username){
        return clinicOwnerRepository.findClinicOwnerByUsername(username);
    }
    
    @Transactional
	public void save(ClinicOwner clinicOwner) throws DataAccessException {
    	
		clinicOwnerRepository.save(clinicOwner);
		
		userService.saveUser(clinicOwner.getUser());
		
		authoritiesService.saveAuthorities(clinicOwner.getUser().getUsername(), "clinicOwner");
	}
	
	
}
