package com.jconnect.validator;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jconnect.entities.Pilote;
import com.jconnect.form.PiloteForm;
import com.jconnect.service.PiloteService;
@Component ("piloteFormValidator")
public class PiloteFormValidator implements Validator {
	@Resource(name="piloteService")
	protected PiloteService piloteService;
	@Override
	public boolean supports(Class<?> arg0) {
		return PiloteForm.class.equals( arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		PiloteForm piloteform = (PiloteForm) obj;
//		
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
				if(!errors.hasFieldErrors("email")){
				
				List<Pilote> Pilotes=piloteService.findByEmail(piloteform.getEmail());
				
				if(!CollectionUtils.isEmpty(Pilotes)){
					errors.rejectValue("email","email.exist");
				}
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel", "tel.required");
				
				
				
			
		
	}

}