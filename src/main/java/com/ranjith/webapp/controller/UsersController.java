package com.ranjith.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.ranjith.webapp.model.Users;
import com.ranjith.webapp.service.UsersService;
import com.ranjith.webapp.service.UsersServiceImp;

@WebServlet(urlPatterns = "/users")
public class UsersController extends HttpServlet{
	
	private Gson gson = new Gson();
	private UsersService userService = new UsersServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		List<Users> list = userService.findAllUsers();
		out.print(gson.toJson(list));
		out.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		
		userService.addUser(new Users(null,name,email,phone,gender));
	}
	
}
