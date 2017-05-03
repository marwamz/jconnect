package com.jconnect.service;

import java.util.List;

import com.jconnect.entities.Jet;
import com.jconnect.entities.Reservation;

public interface ReservationService {
	public Reservation save(Reservation reservation);
	public List<Reservation> findAll();
public Reservation findById(Long id);
public List<Reservation> search(String Name);
}
