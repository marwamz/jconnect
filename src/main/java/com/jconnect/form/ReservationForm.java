package com.jconnect.form;

import java.io.Serializable;
import java.util.Date;


public class ReservationForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long user_id;
	private Long jet_id;

	private String typeReservation;
	private String dateDepart;
	private String dateRetour;
	private int nbPassager;
	private String villeDepart;
	private String villeArrivee;
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}
	public String getDateRetour() {
		return dateRetour;
	}
	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}
	public int getNbPassager() {
		return nbPassager;
	}
	public void setNbPassager(int nbPassager) {
		this.nbPassager = nbPassager;
	}
	public String getVilleDepart() {
		return villeDepart;
	}
	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}
	public String getVilleArrivee() {
		return villeArrivee;
	}
	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}
	public Long getJet_id() {
		return jet_id;
	}
	public void setJet_id(Long jet_id) {
		this.jet_id = jet_id;
	}

	
	public String getTypeReservation() {
		return typeReservation;
	}
	public void setTypeReservation(String typeReservation) {
		this.typeReservation = typeReservation;
	}
	

}
