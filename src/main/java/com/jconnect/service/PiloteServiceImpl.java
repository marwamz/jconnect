package com.jconnect.service;


	import java.util.ArrayList;
	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.ComponentScan;
	import org.springframework.stereotype.Service;

import com.jconnect.dao.PiloteRepository;

	import com.jconnect.entities.Pilote;

	@Service("piloteService")
	@ComponentScan("com.jconnect.dao")
	public class PiloteServiceImpl implements PiloteService {
		
	    @Autowired
		protected PiloteRepository piloteRepository;
		
		@Override
		public List<Pilote> findAll() {
			
			return piloteRepository.findAll();
		}

		@Override
		public void deletePilote(Long id) {
			piloteRepository.delete(id);
			
		}

		@Override
		public Pilote addPilote(Pilote pilote) {
		   return piloteRepository.save(pilote);
		  
		}

		@Override
		public Pilote editPilote(Pilote pilote) {
			
			return  piloteRepository.save(pilote);
		}

		@Override
		public Pilote findPiloteById(Long id) {
			
			return piloteRepository.findOne(id);
		}

		@Override
		public ArrayList<Pilote> searchPilote(String value) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Pilote> findByEmail(String email) {
		return piloteRepository.findByEmail(email);
		}

		@Override
		public List<Pilote> findByLastName(String lastName) {
			// TODO Auto-generated method stub
			return piloteRepository.findByLastName(lastName);
		}

		@Override
		public List<Pilote> search(String lastName) {
			// TODO Auto-generated method stub
			return piloteRepository.search(lastName);
		}

		@Override
		public List<Pilote> findDispoPilotes() {
			// TODO Auto-generated method stub
			return  piloteRepository.findDispoPilotes();
		}
	}



