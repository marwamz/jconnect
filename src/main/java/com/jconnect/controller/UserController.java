package com.jconnect.controller;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.RequestWrapper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jconnect.entities.Jet;
import com.jconnect.entities.Reservation;
import com.jconnect.entities.TypeJet;
import com.jconnect.entities.User;
import com.jconnect.entities.Vol;
import com.jconnect.form.ReservationForm;
import com.jconnect.form.UserForm;
import com.jconnect.service.JetService;
import com.jconnect.service.PiloteService;
import com.jconnect.service.ReservationService;
import com.jconnect.service.TypeJetService;
import com.jconnect.service.UserService;
import com.jconnect.validator.ReservationFormValidator;
import com.jconnect.validator.UserFormValidator;

@Controller
@ComponentScan("com.jconnect.service")
public class UserController {
	@Resource(name = "userService")
    protected UserService userService;
	@Resource(name = "piloteService")
    protected PiloteService piloteService;
	@Resource(name = "jetService")
    protected JetService jetService;
	@Resource(name= "reservationService")
	protected ReservationService reservationService; 
	@Resource(name = "reservationFormValidator")
	protected ReservationFormValidator reservationFormValidator;
	@Resource(name = "typejetservice")
	protected TypeJetService typejetservice;

 @RequestMapping(value = "/")  
public String home(HttpServletRequest request, Model model , @ModelAttribute ("reservationform") ReservationForm reservationform, Authentication authentication){
//	if(authentication != null){
//		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//	}
//	 
//	 Principal user = request.getUserPrincipal();
	 model.addAttribute("pilotesDispo", piloteService.findDispoPilotes());
	 model.addAttribute("jetsDispo", jetService.findDispoJets());
	 Principal user = request.getUserPrincipal();
	 if(user != null){
		 User currentUser = userService.findByEmail(user.getName()).get(0);
	 model.addAttribute("user", currentUser);
	 }
	return "home";
}
 @RequestMapping(value = "/reservation/add", method = RequestMethod.POST)
 public String addReservation(final Model model, @ModelAttribute("reservationform") @Valid final ReservationForm reservationform, final BindingResult bindingResult, HttpServletRequest request) throws ParseException {
    
	 Principal user = request.getUserPrincipal();
	 
	 if(user== null){
		 return "redirect:/login";
	 }
	 reservationFormValidator.validate(reservationform, bindingResult);
     if (bindingResult.hasErrors()) {
         return "home";
     }
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



     return "redirect:/?success=true";
     
 }
    @RequestMapping("/jet/{id}")
    public String findJet(Model model , @PathVariable Long id){
    	model.addAttribute("jet", jetService.findJetById(id));
    	return "jet";
    	
    }
    @RequestMapping(value = "/nosJets")
    public String nosJets(final Model model) {
        
        return "nosJets";
    }
    
    @RequestMapping(value = "/nosJets", method = RequestMethod.GET)
    public String getAllJets(final Model model) {
    	

    	
//    		   model.addAttribute("allJets", jetService.findAll());
    	List<TypeJet> types = typejetservice.FindAll();
    	model.addAttribute("allTypes", typejetservice.FindAll());
    	
    	
        return "nosJets";
    }
    @RequestMapping(value = "/choixJet")
    public String choixJets(final Model model) {
        
        return "choixJet";
    }

}
