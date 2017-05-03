package com.jconnect.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reservation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jet_id")
	private Jet jet;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pilote_id")
	private Pilote pilote;
	private String typeReservation;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vol_id")
	private Vol vol;
	
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Jet getJet() {
		return jet;
	}
	public void setJet(Jet jet) {
		this.jet = jet;
	}
	public Pilote getPilote() {
		return pilote;
	}
	public void setPilote(Pilote pilote) {
		this.pilote = pilote;
	}
	public String getTypeReservation() {
		return typeReservation;
	}
	public void setTypeReservation(String typeReservation) {
		this.typeReservation = typeReservation;
	}
	public Vol getVol() {
		return vol;
	}
	public void setVol(Vol vol) {
		this.vol = vol;
	}
	

}
