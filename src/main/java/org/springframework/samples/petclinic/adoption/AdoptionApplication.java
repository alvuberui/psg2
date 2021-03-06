/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.adoption;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.owner.Owner;


@Entity
@Table(name = "adoption_application")
public class AdoptionApplication extends BaseEntity {

	@NotNull
	protected String description;
	
	@OneToOne
	@JoinColumn(name = "adoption_request_id")
	protected AdoptionRequest adoptionRequest;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private Owner owner;
	
	
	
	public AdoptionApplication() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AdoptionRequest getAdoptionRequest() {
		return adoptionRequest;
	}

	public void setAdoptionRequest(AdoptionRequest adoptionRequest) {
		this.adoptionRequest = adoptionRequest;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	

}
