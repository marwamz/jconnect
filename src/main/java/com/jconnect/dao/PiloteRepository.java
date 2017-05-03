package com.jconnect.dao;

	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;
	import org.springframework.data.jpa.repository.Query;
	import org.springframework.data.repository.query.Param;

import com.jconnect.entities.Jet;
import com.jconnect.entities.Pilote;


	public interface PiloteRepository extends JpaRepository<Pilote, Long>{


		public List<Pilote> findByEmail(String email);
		public List<Pilote> findByLastName(String lastName);
		@Query("SELECT p FROM Pilote p WHERE p.lastName like %:value% or p.firstName like %:value%  or p.email like %:value% ")
		    public List<Pilote> search(@Param("value") String value);
		
		@Query("SELECT p FROM Pilote p WHERE p.disponible= true ")
		public List<Pilote> findDispoPilotes();
		
	}


