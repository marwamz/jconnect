package com.jconnect.register.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jconnect.auth.service.SecurityService;
import com.jconnect.auth.validtor.UserValidator;
import com.jconnect.entities.Authority;
import com.jconnect.entities.User;
import com.jconnect.enums.Authorities;

import com.jconnect.form.RegisterUserForm;
import com.jconnect.service.UserService;



@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(final Model model) {
        model.addAttribute("userForm", new RegisterUserForm());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String
            registration(@ModelAttribute("userForm") final RegisterUserForm userForm, final BindingResult bindingResult, final Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        final User user = new User();
        user.setEmail(userForm.getEmail());
        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        Authority authority = new Authority();
        authority.setName(Authorities.ROLE_USER.name());
        HashSet<Authority> authorities = new HashSet<Authority>();
        authorities.add(authority);
        user.setAuthorities(authorities);
        userService.addUser(user);

        securityService.autologin(userForm.getEmail(), userForm.getPassword());

        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(final Model model) {

        return "login";
    }


}
