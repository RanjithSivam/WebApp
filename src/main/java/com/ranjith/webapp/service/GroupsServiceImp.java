package com.ranjith.webapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ranjith.webapp.model.Groups;
import com.ranjith.webapp.model.Users;

public class GroupsServiceImp implements GroupsService {
	
	private Connection connection = null;

	@Override
	public void addGroup(String name) {
		String INSERT_INTO_GROUPS = "INSERT INTO groups (name) VALUES (?);";
		
        try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample","ranjith","ranjith@123");
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_INTO_GROUPS);
			prepareStatement.setString(1,name );
			prepareStatement.executeUpdate();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Groups> findAllGroups() {
		String FIND_ALL_GROUPS = "SELECT * FROM GROUPS;";
		List<Groups> listGroups = new ArrayList();
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample","ranjith","ranjith@123");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_ALL_GROUPS);
			while(rs.next()) {
				Groups group = new Groups();
				group.setId(rs.getInt("id"));
				group.setName(rs.getString("name"));
				listGroups.add(group);
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return listGroups;
	}

	@Override
	public List<Users> findAllUsersInGroups(Integer id) {
		String FIND_ALL_USERS_IN_GROUP = "SELECT users.id,users.name FROM groups JOIN users_groups ON users_groups.groups_id = groups.id JOIN users ON users_groups.users_id = users.id WHERE groups.id = ?;";
		
		List<Users> users = new ArrayList();
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample","ranjith","ranjith@123");
			PreparedStatement prepareStatement = connection.prepareStatement(FIND_ALL_USERS_IN_GROUP);
			prepareStatement.setInt(1, id);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				Users user = new Users();
				user.setName(rs.getString("name"));
				user.setId(rs.getInt("id"));
				users.add(user);
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		return users;
	}

}
