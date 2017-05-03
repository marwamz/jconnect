package com.jconnect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jconnect.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{


	public List<User> findByEmail(String email);
	public List<User> findByLastName(String lastName);
	@Query("SELECT u FROM User u WHERE u.lastName like %:value% or u.firstName like %:value%  or u.email like %:value% ")
	    public List<User> search(@Param("value") String value);
	
	//public List<User> search(String lastName);
	
}
