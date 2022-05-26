package com.ranjith.webapp.service;

import java.util.List;

import com.ranjith.webapp.model.Groups;
import com.ranjith.webapp.model.Users;

public interface GroupsService {
	
	public void addGroup(String name);
	public List<Groups> findAllGroups();
	public List<Users> findAllUsersInGroups(Integer id);
}
