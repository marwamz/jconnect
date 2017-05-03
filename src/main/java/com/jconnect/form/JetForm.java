package com.jconnect.form;

import java.io.Serializable;

public class JetForm implements Serializable{
	
	private Long id;
	private String name;
	private Long typejet;
	private Long nbPlace;
	private boolean disponible;
	private String description;
	private String imageUrl;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getNbPlace() {
		return nbPlace;
	}
	public void setNbPlace(Long nbPlace) {
		this.nbPlace = nbPlace;
	}
	public boolean isDisponible() {
		return disponible;
	}
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	public Long getTypejet() {
		return typejet;
	}
	public void setTypejet(Long typejet) {
		this.typejet = typejet;
	}
	

}
