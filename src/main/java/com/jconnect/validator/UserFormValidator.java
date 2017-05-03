package com.jconnect.validator;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jconnect.entities.User;
import com.jconnect.form.UserForm;
import com.jconnect.service.UserService;
@Component ("userFormValidator")
public class UserFormValidator implements Validator {
	@Resource(name="userService")
	protected UserService userService;
	@Override
	public boolean supports(Class<?> arg0) {
		return UserForm.class.equals( arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserForm userform = (UserForm) obj;
//		
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.required");
			
			
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
				if(!errors.hasFieldErrors("email")){
				
				List<User> users=userService.findByEmail(userform.getEmail());
				
				if(!CollectionUtils.isEmpty(users)){
					errors.rejectValue("email","email.exist");
				}
				}
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel", "tel.required");
				
			
		
	}

}
