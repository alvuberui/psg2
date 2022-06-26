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
package org.springframework.samples.petclinic.pethotel;

import org.springframework.validation.Validator;

import org.springframework.validation.Errors;

/**
 * <code>Validator</code> for <code>Pet</code> forms.
 * <p>
 * We're not using Bean Validation annotations here because it is easier to
 * define such validation rule in Java.
 * </p>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 */

public class PetHotelValidator implements Validator {
	private static final String REQUIRED = "required";

	@Override
	public void validate(Object obj, Errors errors) {
		PetHotel petHotel = (PetHotel) obj;

		//initialDate null
		if (petHotel.getInitialDate() == null) {
			errors.rejectValue("initialDate", REQUIRED, REQUIRED);
		}
		
		//finalDate null
		if (petHotel.getFinalDate() == null) {
			errors.rejectValue("finalDate", REQUIRED, REQUIRED);
		}
		
		//finalDate before initialDate
		if (petHotel.getInitialDate() != null && petHotel.getFinalDate() != null
				&& petHotel.getFinalDate().isBefore(petHotel.getInitialDate())) {
			errors.rejectValue("finalDate", "Debe ser posterior a la fecha de entrada",
					"Debe ser posterior a la fecha de entrada");
		}
	}

	/**
	 * This Validator validates *just* Pet instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return PetHotel.class.isAssignableFrom(clazz);
	}
}
