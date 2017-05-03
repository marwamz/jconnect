package com.jconnect.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jconnect.entities.TypeJet;

public interface jetTypeRepository extends JpaRepository<TypeJet , Long>{
	public TypeJet findByName(String Name);

}
