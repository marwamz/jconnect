package com.jconnect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.jconnect.entities.User;

public interface UserService {
	public List<User> findAll();
    public void deleteUser(Long id);
    public User addUser(User user);
    public User editUser(User user);
    public User findUserById(Long id);
    public ArrayList<User> searchUser(String value);
  public List<User> findByEmail(String email);
  public List<User> findByLastName(String lastName);
  public List<User> search(@Param("lastName") String lastName);
	
}