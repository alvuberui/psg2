package org.springframework.samples.petclinic.customerAgreementVersions;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;


@Entity
@Table(name = "CA_Version")
public class CustomerAgreementVersions extends BaseEntity {

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "currentplan_id")
	protected CAType currentplan;
	
	public CustomerAgreementVersions() {
		super();
	}

	public CAType getCurrentplan() {
		return currentplan;
	}

	public void setCurrentplan(CAType currentplan) {
		this.currentplan = currentplan;
	}

}
