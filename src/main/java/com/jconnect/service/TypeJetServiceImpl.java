package com.jconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.jconnect.dao.ReservationRepository;
import com.jconnect.dao.jetTypeRepository;
import com.jconnect.entities.TypeJet;
@Service("typejetservice")
@ComponentScan("com.jconnect.dao")
public class TypeJetServiceImpl implements TypeJetService {

	 @Autowired
		protected jetTypeRepository jetTypeRepository;
		 
	@Override
	public TypeJet findByName(String Name) {
		// TODO Auto-generated method stub
		return jetTypeRepository.findByName(Name);
	}

	@Override
	public TypeJet FindById(Long id) {
		// TODO Auto-generated method stub
		return jetTypeRepository.findOne(id);
	}

	@Override
	public List<TypeJet> FindAll() {
		// TODO Auto-generated method stub
		return jetTypeRepository.findAll();
	}

	@Override
	public TypeJet addTypeJet(TypeJet typejet) {
		// TODO Auto-generated method stub
		return jetTypeRepository.save(typejet);
	}

}
