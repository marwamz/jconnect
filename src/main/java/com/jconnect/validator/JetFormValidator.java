package com.jconnect.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jconnect.form.JetForm;
import com.jconnect.form.PiloteForm;
@Component("jetFormValidator")
public class JetFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return PiloteForm.class.equals( arg0);
	}
	@Override
	public void validate(Object obj, Errors errors) {
		JetForm jetform = (JetForm) obj;
//		
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "jet.name.required");
			
			
//				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "jet.type.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nbPlace", "jet.nbPlace.required");
			
			
		
	}

}
