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

public class UsersServiceImp implements UsersService {
	
	private Connection connection = null;

	@Override
	public void addUser(Users user) {
		String INSERT_INTO_USERS = "INSERT INTO users (name,email,gender,phone) VALUES (?,?,?,?);";
		
        try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample","ranjith","ranjith@123");
			PreparedStatement prepareStatement = connection.prepareStatement(INSERT_INTO_USERS);
			prepareStatement.setString(1,user.getName() );
			prepareStatement.setString(2, user.getEmail());
			prepareStatement.setString(3, user.getGender());
			prepareStatement.setString(4, user.getPhone());
			prepareStatement.executeUpdate();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Users> findAllUsers() {
		String FIND_ALL_USERS = "SELECT * FROM users;";
		List<Users> listUsers = new ArrayList();
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/sample","ranjith","ranjith@123");
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(FIND_ALL_USERS);
			while(rs.next()) {
				Users user = new Users();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				listUsers.add(user);
			}
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return listUsers;
	}

}
