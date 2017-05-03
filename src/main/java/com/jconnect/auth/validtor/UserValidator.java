package com.jconnect.auth.validtor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.jconnect.entities.User;
import com.jconnect.form.RegisterUserForm;
import com.jconnect.service.UserService;


@Service
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(final Class<?> arg0) {
        return User.class.equals(arg0);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        final RegisterUserForm user = (RegisterUserForm) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");
        if (user.getLastName().length() < 3 || user.getLastName().length() > 32) {
            errors.rejectValue("lastName", "Size.userForm.username");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");
        if (user.getFirstName().length() < 3 || user.getFirstName().length() > 32) {
            errors.rejectValue("firstName", "Size.userForm.username");
        }
        if (!CollectionUtils.isEmpty(userService.findByEmail(user.getEmail()))) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }

    }

}
