package org.springframework.samples.petclinic.clinic;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.clinicOwner.ClinicOwner;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Clinic extends BaseEntity{
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	@ManyToOne
	@JoinColumn(name = "clinicOwner_id")
	private ClinicOwner clinicOwner;
}
