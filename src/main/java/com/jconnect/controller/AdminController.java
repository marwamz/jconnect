package com.jconnect.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jconnect.entities.Jet;
import com.jconnect.entities.Pilote;
import com.jconnect.entities.Reservation;
import com.jconnect.entities.TypeJet;
import com.jconnect.entities.User;
import com.jconnect.enums.Authorities;
import com.jconnect.form.JetForm;
import com.jconnect.form.PiloteForm;
import com.jconnect.form.TypeJetForm;
import com.jconnect.form.UserForm;
import com.jconnect.service.JetService;
import com.jconnect.service.PiloteService;
import com.jconnect.service.ReservationService;
import com.jconnect.service.TypeJetService;
import com.jconnect.service.UserService;
import com.jconnect.validator.JetFormValidator;
import com.jconnect.validator.PiloteFormValidator;
import com.jconnect.validator.UserFormValidator;

@Controller
@ComponentScan("com.jconnect.service")
@RequestMapping("/admin")
public class AdminController {
	int a =9;
	@Resource(name = "userService")
	protected UserService userService;
	@Resource(name = "userFormValidator")
	protected UserFormValidator validatorUser;
	@Resource(name = "reservationService")
	protected ReservationService reservationService;
	@Resource(name = "piloteService")
	protected PiloteService piloteService;
	@Resource(name = "piloteFormValidator")
	protected PiloteFormValidator validatorPilote;
	@Resource(name = "typejetservice")
	protected TypeJetService typejetservice;

	@Resource(name = "jetService")
	protected JetService jetService;
	@Resource(name = "jetFormValidator")
	protected JetFormValidator validatorJet;

	@RequestMapping("/index")
	public String indexAdmin(Model model) {
		return "dashbored";

	}

	@RequestMapping(value = "/manageUser")
	public String home(final Model model) {
		model.addAttribute("allUsers", userService.findAll());
		return "manageUsers";
	}

	@RequestMapping(value = "/managePilotes")
	public String home1(final Model model) {
		model.addAttribute("allPilotes", piloteService.findAll());
		return "managePilotes";
	}

	@RequestMapping(value = "/manageJets")
	public String home2(final Model model) {
		model.addAttribute("allJets", jetService.findAll());
		return "manageJets";
	}

	@RequestMapping(value = "/manageReservation")
	public String reservation(final Model model) {
		model.addAttribute("allreservations", reservationService.findAll());
		return "manageReservation";
	}

	@RequestMapping(value = "/user/delete/{id}")
	public String deleteUser(final Model model, @PathVariable final Long id) {
		userService.deleteUser(id);

		return "redirect:/admin/manageUser";
	}

	@RequestMapping(value = "/pilote/delete/{id}")
	public String deletePilote(final Model model, @PathVariable final Long id) {
		piloteService.deletePilote(id);

		return "redirect:/admin/managePilotes";
	}

	@RequestMapping(value = "/jet/delete/{id}")
	public String deleteJet(final Model model, @PathVariable final Long id) {
		jetService.deleteJet(id);

		return "redirect:/admin/manageJets";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String addUserView(final Model model, @ModelAttribute("userform") final UserForm userform) {
		// userService.addUser(user);

		return "addUser";
	}

	@RequestMapping(value = "/pilote/add", method = RequestMethod.GET)
	public String addPiloteView(final Model model, @ModelAttribute("piloteform") final PiloteForm piloteform) {

		return "addPilote";
	}

	@RequestMapping(value = "/jet/add", method = RequestMethod.GET)
	public String addJetView(final Model model, @ModelAttribute("jetform") final JetForm jetform) {

		model.addAttribute("allTypes", typejetservice.FindAll());
		return "addJet";
	}

	@RequestMapping(value = "/jetType", method = RequestMethod.GET)
	public String addJetTypeView(final Model model, @ModelAttribute("typejetform") final TypeJetForm typejetform) {
		// userService.addUser(user);

		return "addTypeJet";
	}

	@RequestMapping(value = "/JetType/add", method = RequestMethod.POST)
	public String addJetType(final Model model, @ModelAttribute("typejetform") final TypeJetForm typejetform) {
		TypeJet typejet = new TypeJet();
		typejet.setName(typejetform.getName());
		typejet.setDescription(typejetform.getDescription());
		typejetservice.addTypeJet(typejet);

		return "redirect:/admin/AllJetTypes";
	}

	@RequestMapping(value = "/JetType/edit/{id}", method = RequestMethod.GET)
	public String editTypeView(final Model model, @ModelAttribute final TypeJet typejet) {

		return "editTypeJet";
	}

	@RequestMapping(value = "/JetType/edit", method = RequestMethod.POST)
	public String editJetType(final Model model, @ModelAttribute("typejetform") final TypeJet typejet) {
		TypeJet savedtypejet = typejetservice.FindById(typejet.getId());
		savedtypejet.setName(typejet.getName());
		savedtypejet.setDescription(typejet.getDescription());
		typejetservice.addTypeJet(typejet);

		return "redirect: /admin/AllJetTypes";
	}

	@RequestMapping(value = "/AllJetTypes")
	public String findAllJetTypes(Model model) {
		model.addAttribute("allJetTypes", typejetservice.FindAll());
		return "allJetTypes";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addUser(final Model model, @ModelAttribute("userform") @Valid final UserForm userform,
			final BindingResult bindingResult) {

		validatorUser.validate(userform, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addUser";
		}
		final User user = new User();
		user.setLastName(userform.getLastName());
		user.setFirstName(userform.getFirstName());

		user.setEmail(userform.getEmail());
		user.setTel(userform.getTel());
		userService.addUser(user);

		return "redirect:/admin/manageUser";
	}

	@RequestMapping(value = "/pilote/add", method = RequestMethod.POST)
	public String addPilote(final Model model, @ModelAttribute("piloteform") @Valid final PiloteForm piloteform,
			final BindingResult bindingResult) {

		validatorPilote.validate(piloteform, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addPilote";
		}
		final Pilote pilote = new Pilote();
		pilote.setLastName(piloteform.getLastName());
		pilote.setFirstName(piloteform.getFirstName());
		pilote.setEmail(piloteform.getEmail());
		pilote.setTel(piloteform.getTel());
		pilote.setDisponible(piloteform.isDisponible());
		piloteService.addPilote(pilote);

		return "redirect:/admin/managePilotes";
	}

	@RequestMapping(value = "/jet/add", method = RequestMethod.POST)
	public String addPilote(final Model model, @ModelAttribute("jetform") @Valid final JetForm jetform,
			final BindingResult bindingResult) {

		validatorJet.validate(jetform, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addJet";
		}
		Long id = jetform.getTypejet();
		TypeJet typejet = typejetservice.FindById(id);
		final Jet jet = new Jet();
		jet.setName(jetform.getName());
		jet.setType(typejet);
		jet.setNbPlace(jetform.getNbPlace());
		jet.setDisponible(jetform.isDisponible());
		jet.setImageUrl(jetform.getImageUrl());
		jet.setDescription(jetform.getDescription());
		jetService.addJet(jet);

		return "redirect:/admin/manageJets";
	}

	@RequestMapping(value = "/user/edit/{user}", method = RequestMethod.GET)
	public String editUserView(final Model model, @ModelAttribute final User user) {
		// userService.editUser(id);
List<String> authorities = new ArrayList<>();
authorities.add(Authorities.ROLE_ADMIN.name());
authorities.add(Authorities.ROLE_USER.name());
authorities.add(Authorities.ROLE_ANONYMOUS.name());
model.addAttribute("authorities", authorities);
		return "edit";
	}

	@RequestMapping(value = "/pilote/editPilote/{pilote}", method = RequestMethod.GET)
	public String editPiloteView(final Model model, @ModelAttribute final Pilote pilote) {

		return "editPilote";
	}

	@RequestMapping(value = "/jet/editJet/{jet}", method = RequestMethod.GET)
	public String editJetView(final Model model, @ModelAttribute final Jet jet) {

		model.addAttribute("allTypes", typejetservice.FindAll());
		return "editJet";
	}

	@RequestMapping(value = "/reservation/editReservation/{reservation}", method = RequestMethod.GET)
	public String editReservationView(final Model model, @ModelAttribute final Reservation reservation) {

		model.addAttribute("pilotesDispo", piloteService.findDispoPilotes());
		return "editReservation";
	}

	@RequestMapping(value = "/user/edit", method = RequestMethod.POST)
	public String editUser(final Model model, @ModelAttribute(value = "user") final User user) {
		final User savedUser = userService.findUserById(user.getId());
		savedUser.setLastName(user.getLastName());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setEmail(user.getEmail());
		savedUser.setTel(user.getTel());
		userService.editUser(savedUser);

		return "redirect:/admin/manageUser";
	}

	@RequestMapping(value = "/pilote/editPilote", method = RequestMethod.POST)
	public String editPilote(final Model model, @ModelAttribute(value = "pilote") final Pilote pilote) {
		final Pilote savedPilote = piloteService.findPiloteById(pilote.getId());
		savedPilote.setLastName(pilote.getLastName());
		savedPilote.setFirstName(pilote.getFirstName());
		savedPilote.setEmail(pilote.getEmail());
		savedPilote.setTel(pilote.getTel());
		savedPilote.setDisponible(pilote.isDisponible());
		piloteService.editPilote(savedPilote);

		return "redirect:/admin/managePilotes";
	}

	@RequestMapping(value = "/jet/editJet", method = RequestMethod.POST)
	public String editJet(final Model model, @ModelAttribute(value = "jet") final Jet jet) {
		final Jet savedJet = jetService.findJetById(jet.getId());
		savedJet.setName(jet.getName());
		savedJet.setType(jet.getType());
		savedJet.setNbPlace(jet.getNbPlace());
		savedJet.setDisponible(jet.isDisponible());
		savedJet.setImageUrl(jet.getImageUrl());
		savedJet.setDescription(jet.getDescription());
		jetService.addJet(savedJet);

		return "redirect:/admin/manageJets";
	}

	@RequestMapping(value = "/user/searchUser", method = RequestMethod.GET)
	public String searchUserView(final Model model, @RequestParam(value = "userSearch") String userSearch) {
		if (!userSearch.isEmpty()) {
			List<User> users = userService.search(userSearch);

			model.addAttribute("allUsers", users);

		}

		else {
			model.addAttribute("allUsers", userService.findAll());
		}

		return "manageUsers :: result";
	}

	@RequestMapping(value = "/pilote/searchPilote", method = RequestMethod.GET)
	public String searchPiloteView(final Model model, @RequestParam(value = "piloteSearch") String piloteSearch) {
		if (!piloteSearch.isEmpty()) {
			List<Pilote> pilotes = piloteService.search(piloteSearch);

			model.addAttribute("allPilotes", pilotes);

		}

		else {
			model.addAttribute("allPilotes", piloteService.findAll());
		}

		return "managePilotes :: result";
	}

	@RequestMapping(value = "/jet/searchJet", method = RequestMethod.GET)
	public String searchJetView(final Model model, @RequestParam(value = "jetSearch") String jetSearch) {
		if (!jetSearch.isEmpty()) {
			List<Jet> jets = jetService.search(jetSearch);

			model.addAttribute("allJets", jets);

		}

		else {
			model.addAttribute("allJets", jetService.findAll());
		}

		return "manageJets :: result";
	}
	@RequestMapping(value = "/reservation/searchResrvation", method = RequestMethod.GET)
	public String searchReservationView(final Model model, @RequestParam(value = "reservationSearch") String reservationSearch) {
		if (!reservationSearch.isEmpty()) {
			List<Reservation> reservations = reservationService.search(reservationSearch);

			model.addAttribute("allreservations", reservations);

		}

		else {
			model.addAttribute("allreservations", reservationService.findAll());
		}

		return "manageReservation :: result";
	}

}
