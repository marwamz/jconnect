package com.jconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.jconnect.dao.ReservationRepository;
import com.jconnect.entities.Reservation;

@Service("reservationService")
@ComponentScan("com.jconnect.dao")
public class ReservationServiceImpl implements ReservationService {

	   @Autowired
			protected ReservationRepository reservationRepository;
			
	@Override
	public Reservation save(Reservation reservation) {
		
		return reservationRepository.save(reservation);
	}

	@Override
	public List<Reservation> findAll() {
		// TODO Auto-generated method stub
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findById(Long id) {
		// TODO Auto-generated method stub
		return reservationRepository.findOne(id);
	}

	@Override
	public List<Reservation> search(String Name) {
		return reservationRepository.search(Name);
	
	}
	

}
