package com.jconnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.jconnect.dao.UserRepository;
import com.jconnect.entities.User;

@Service("userService")
@ComponentScan("com.jconnect.dao")
public class UserServiceImpl implements UserService {
	
    @Autowired
	protected UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.delete(id);
		
	}

	@Override
	public User addUser(User user) {
	   return userRepository.save(user);
	  
	}

	@Override
	public User editUser(User user) {
		
		return  userRepository.save(user);
	}

	@Override
	public User findUserById(Long id) {
		
		return userRepository.findOne(id);
	}

	@Override
	public ArrayList<User> searchUser(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByEmail(String email) {
	return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return userRepository.findByLastName(lastName);
	}

	@Override
	public List<User> search(String lastName) {
		// TODO Auto-generated method stub
		return userRepository.search(lastName);
	}
}
