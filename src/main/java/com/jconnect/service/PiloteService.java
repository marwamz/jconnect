package com.jconnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.jconnect.entities.Pilote;

public interface PiloteService {
	public List<Pilote> findAll();
    public void deletePilote(Long id);
    public Pilote addPilote(Pilote pilote);
    public Pilote editPilote(Pilote pilote);
    public Pilote findPiloteById(Long id);
    public ArrayList<Pilote> searchPilote(String value);
  public List<Pilote> findByEmail(String email);
  public List<Pilote> findByLastName(String lastName);
  public List<Pilote> search(@Param("lastName") String lastName);
  public List<Pilote> findDispoPilotes();
	
}