package org.springframework.samples.petclinic.customerAgreementVersions;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerAgreementService {

	private CustomerAgreementRepository CARepository;
	
	@Autowired
	public CustomerAgreementService(CustomerAgreementRepository CARepository) {
		this.CARepository = CARepository;
	}

	

	public List<CustomerAgreementVersions> findAll(){
		Iterable<CustomerAgreementVersions> CAs = CARepository.findAll();
		List<CustomerAgreementVersions>LCA = new ArrayList<CustomerAgreementVersions>();
		for(CustomerAgreementVersions CA : CAs) {
			LCA.add(CA);
			if(LCA.size()>3) {
				LCA.remove(0);
			}
		}
		return LCA;
	}

	
}
