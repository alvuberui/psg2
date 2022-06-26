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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;


@Entity
@Table(name = "adoption_request")
public class AdoptionRequest extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "pet_id")
	protected Pet pet;
	
	
	public AdoptionRequest() {
		super();
	}

	
	public Pet getPets() {
		return pet;
	}

	public void setPets(Pet pets) {
		this.pet = pets;
	}


	

}
