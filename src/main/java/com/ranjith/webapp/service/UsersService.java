package com.ranjith.webapp.service;

import java.util.List;

import com.ranjith.webapp.model.Users;

public interface UsersService {
	
	public void addUser(Users user);
	public List<Users> findAllUsers();
}
