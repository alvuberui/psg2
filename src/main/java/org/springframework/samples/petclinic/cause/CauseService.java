package org.springframework.samples.petclinic.cause;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CauseService {

    @Autowired
	private CauseRepository causeRepository;
    @Autowired
    private DonationRepository donationRepository;
	
	@Transactional
	public void saveDonation(Donation donation) throws DataAccessException {
		donationRepository.save(donation);
	}

	@Transactional
    public Optional<Cause> findCauseById(int id){
        return causeRepository.findCauseById(id);
    }

    @Transactional()	
	public Iterable<Cause> findAll(){
		return causeRepository.findAll();
	}

    @Transactional
	public void save(Cause cause){
		causeRepository.save(cause);
	}
    
    @Transactional
	public void deleteDonationByOwnerId(Integer ownerId) {
    	Collection<Donation> donations = donationRepository.findAllDonationByOwnerId(ownerId);
    	for (Donation d : donations) {
    		donationRepository.deleteById(d.getId());
    	}
		
	}
}
