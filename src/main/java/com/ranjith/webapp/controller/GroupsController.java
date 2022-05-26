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

@WebServlet(urlPatterns = "/groups/*")
public class GroupsController extends HttpServlet{
	
	private Gson gson;
	private GroupsService groupsService;

	@Override
	public void init() throws ServletException {
		gson = new Gson();
		groupsService = new GroupsServiceImp();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = req.getPathInfo();
		
		switch(path) {
		case "/all":
			showAllGroups(req,resp);
			break;
		case "/form":
			showForm(req,resp);
			break;
		case "/user":
			showAllUserInGroup(req,resp);
			break;
			
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		groupsService.addGroup(name);
		
		out.println("<h2>Group Created!!</h2>");
	}
	
	private void showAllGroups(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		List<Groups> list = groupsService.findAllGroups();
		
		out.println("<table><thead><tr><th>ID</th><th>Name</th></tr></thead><tbody>");
		
		for(Groups group:list) {
			out.println("<tr>");
			out.println("<td>"+group.getId() +"</td>");
			out.println("<td>"+group.getName() +"</td>");
			out.println("</tr>");
		}
		
		out.println("</tbody></table>");
		out.close();
	}
	
	private void showAllUserInGroup(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		String id = req.getParameter("groupId");
		List<Users> list = groupsService.findAllUsersInGroups(Integer.parseInt(id));
		
		out.println("<table><thead><tr><th>ID</th><th>Name</th></tr></thead><tbody>");
		
		for(Users user:list) {
			out.println("<tr>");
			out.println("<td>"+user.getId() +"</td>");
			out.println("<td>"+user.getName() +"</td>");
			out.println("</tr>");
		}
		
		out.println("</tbody></table>");
		out.close();
	}
	
	private void showForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<h4>Add New Group</h4>");
		out.println("<form method=\"post\" action=\"\">"
				+ "  <div>\n"
				+ "    <label>Name:</label>\n"
				+ "    <input type=\"text\" name=\"name\" required />\n"
				+ "  </div>\n"
				+ "  <button>\n"
				+ "    submit\n"
				+ "  </button>\n"
				+ "</form>");
		out.close();
	}

	
}
