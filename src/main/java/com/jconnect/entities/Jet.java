package com.jconnect.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Jet")
public class Jet implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "typeJet_id")
	private TypeJet type;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TypeJet getType() {
		return type;
	}
	public void setType(TypeJet type) {
		this.type = type;
	}
	

}
