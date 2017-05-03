package com.jconnect.service;

import java.util.List;

import com.jconnect.entities.TypeJet;

public interface TypeJetService {
	public TypeJet findByName(String Name);
	public TypeJet FindById(Long id);
	public List<TypeJet> FindAll();
	public TypeJet addTypeJet(TypeJet typejet);

}
