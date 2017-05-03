package com.jconnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.jconnect.dao.JetRepository;
import com.jconnect.entities.Jet;

@Service("jetService")
@ComponentScan("com.jconnect.dao")
public class JetServiceImpl implements JetService {
	
	 @Autowired
		protected JetRepository jetRepository;

	@Override
	public List<Jet> findAll() {
	
		return jetRepository.findAll();
	}

	@Override
	public void deleteJet(Long id) {
	   jetRepository.delete(id);;
		
	}

	@Override
	public Jet addJet(Jet jet) {
		
		return jetRepository.save(jet);
	}

	@Override
	public Jet editJet(Jet jet) {
		return jetRepository.save(jet);
	}

	@Override
	public Jet findJetById(Long id) {
		return jetRepository.findOne(id);
	}

	@Override
	public ArrayList<Jet> searchJet(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Jet> findByType(String type) {
		return jetRepository.findByType(type);
	}

	@Override
	public List<Jet> findByName(String name) {
		return jetRepository.findByName(name);
	}

	@Override
	public List<Jet> search(String Name) {
		return jetRepository.search(Name);
	}

	@Override
	public List<Jet> findDispoJets() {
		// TODO Auto-generated method stub
		return jetRepository.findDispoJets();
	}

}
