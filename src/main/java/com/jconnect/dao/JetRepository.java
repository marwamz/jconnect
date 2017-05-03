package com.jconnect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jconnect.entities.Jet;





public interface JetRepository  extends JpaRepository<Jet, Long> {
	public List<Jet> findByType(String type);
	public List<Jet> findByName(String name);

	@Query("SELECT j FROM Jet j WHERE j.name like %:value% or j.type like %:value% ")
	  public List<Jet> search(@Param("value") String value);
	
	@Query("SELECT j FROM Jet j WHERE j.disponible= true ")
	public List<Jet> findDispoJets();
}
