package com.jconnect.api.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jconnect.auth.validtor.UserValidator;
import com.jconnect.entities.Authority;
import com.jconnect.entities.Jet;
import com.jconnect.entities.Reservation;
import com.jconnect.entities.User;
import com.jconnect.entities.Vol;
import com.jconnect.enums.Authorities;
import com.jconnect.form.RegisterUserForm;
import com.jconnect.form.ReservationForm;
import com.jconnect.service.JetService;
import com.jconnect.service.ReservationService;
import com.jconnect.service.UserService;
import com.jconnect.validator.ReservationFormValidator;
@RestController
@RequestMapping("/api")
public class UserApiController {
	@Resource(name = "reservationFormValidator")
	protected ReservationFormValidator reservationFormValidator; 
	@Resource(name = "jetService")
    protected JetService jetService;
	@Resource(name = "userService")
    protected UserService userService;
	@Resource(name= "reservationService")
	protected ReservationService reservationService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserValidator userValidator;

	
	 @RequestMapping(value = "/reservation", method = RequestMethod.POST)
	 public ResponseEntity<ReservationForm> addReservation(@RequestBody ReservationForm reservationform) throws ParseException {
	    

	     final Reservation reservation = new Reservation();
	     Jet jet= jetService.findJetById(reservationform.getJet_id());
	     reservation.setJet(jet);
	    // reservation.setPilote(piloteService.findPiloteById(reservationform.getPilote_id()));
	     reservation.setUser(userService.findUserById(reservationform.getUser_id()));
	     reservation.setTypeReservation(reservationform.getTypeReservation());
	     
	     Vol vol =new Vol();
	     DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm");
	     vol.setDateDepart(df.parse(reservationform.getDateDepart()));
	     vol.setDateRetour(df.parse(reservationform.getDateRetour()));
	     vol.setNbPassager(reservationform.getNbPassager());
	     vol.setVilleArrivee(reservationform.getVilleArrivee());
	     vol.setVilleDepart(reservationform.getVilleDepart());
	     
	     reservation.setVol(vol);
	     reservation.setState("pending");
	     reservationService.save(reservation);

	    // HttpHeaders headers=new HttpHeaders();


	     return new ResponseEntity<ReservationForm>(reservationform,HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/jet", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<List<Jet>> returnJet(HttpServletRequest request){
//		 Principal principal = request.getUserPrincipal();
//		 String name = principal.getName();

		 List<Jet> jets = jetService.findAll();
		 
		 return new ResponseEntity<List<Jet>>(jets, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/me", method = RequestMethod.GET)
	 public User getCurrentCustomer(HttpServletRequest request){
		 Principal principal = request.getUserPrincipal();
		 String name = principal.getName();
		 return userService.findByEmail(name).get(0);
		 

		 
	 }
	 
	    @RequestMapping(value = "/registration", method = RequestMethod.POST)
	    public ResponseEntity<List<FieldError>>  registration(@RequestBody RegisterUserForm userForm, final BindingResult bindingResult) {
	    	List<FieldError> errors= null;
	    	userValidator.validate(userForm, bindingResult);
	        if (bindingResult.hasErrors()) {
	        	errors = bindingResult.getFieldErrors();
	        	return new ResponseEntity<List<FieldError>>(errors, HttpStatus.NOT_ACCEPTABLE);
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

	        //securityService.autologin(userForm.getEmail(), userForm.getPassword());

	        return new ResponseEntity<List<FieldError>>(errors, HttpStatus.OK);
	    }
	 
}
