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
import com.ranjith.webapp.model.Groups;
import com.ranjith.webapp.model.Users;
import com.ranjith.webapp.service.GroupsService;
import com.ranjith.webapp.service.GroupsServiceImp;

@WebServlet(urlPatterns = "/groups")
public class GroupsController extends HttpServlet{
	
	private Gson gson = new Gson();
	private GroupsService groupsService = new GroupsServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		String id = req.getParameter("groupId");
		if(id!=null) {
			List<Users> list = groupsService.findAllUsersInGroups(Integer.parseInt(id));
			out.print(gson.toJson(list));
			System.out.println(list);
			out.close();
		}else {
			List<Groups> list = groupsService.findAllGroups();
			out.print(gson.toJson(list));
			out.close();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println(name);
		groupsService.addGroup(name);
	}
	
}
