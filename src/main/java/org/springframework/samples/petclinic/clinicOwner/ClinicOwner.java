package org.springframework.samples.petclinic.clinicOwner;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;


import org.springframework.samples.petclinic.clinic.Clinic;
import org.springframework.samples.petclinic.model.Person;

import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ClinicOwner extends Person {
	
	@Column(name = "address")
	@NotEmpty
	private String address;
	
	@Column(name = "telephone")
	@NotEmpty
	@Digits(fraction = 0, integer = 10)
	private String telephone;
	
	@Column(name = "city")
	@NotEmpty
	private String city;
	
	@OneToOne(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    @JoinColumn(name = "username", referencedColumnName = "username")
	private User user;
	
	@OneToMany(mappedBy = "clinicOwner", cascade = CascadeType.REMOVE)
	private Set<Clinic> clinics;
}
