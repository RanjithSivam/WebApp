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
import com.ranjith.webapp.service.GroupsServiceImp;
import com.ranjith.webapp.service.UsersService;
import com.ranjith.webapp.service.UsersServiceImp;

@WebServlet(urlPatterns = "/users/*")
public class UsersController extends HttpServlet{
	
	private Gson gson;
	private UsersService userService;
	
	@Override
	public void init() throws ServletException {
		gson = new Gson();
		userService = new UsersServiceImp();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		
		switch(path) {
		case "/all":
			showAllUsers(req,resp);
			break;
		case "/form":
			showForm(req,resp);
			break;
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String gender = req.getParameter("gender");
		
		userService.addUser(new Users(null,name,email,phone,gender));
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		out.println("<h2>Registeration Successfull!!!</h2>");
	}
	
	private void showAllUsers(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Users> list = userService.findAllUsers();
		
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<table><thead><tr><th>ID</th><th>Name</th><th>Email</th><th>Gender</th><th>Phone</th></tr></thead><tbody>");
		
		for(Users user:list) {
			out.println("<tr>");
			out.println("<td>"+user.getId() +"</td>");
			out.println("<td>"+user.getName() +"</td>");
			out.println("<td>"+user.getEmail() +"</td>");
			out.println("<td>"+user.getGender() +"</td>");
			out.println("<td>"+user.getPhone() +"</td>");
			out.println("</tr>");
		}
		
		out.println("</tbody></table>");
		out.close();
	}
	
	private void showForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<h4>User Registeration Form</h4>");
		out.println("<form method=\"post\" action=\"\">"
				+ "  <div>\n"
				+ "    <label>Name:</label>\n"
				+ "    <input type=\"text\" name=\"name\" required />\n"
				+ "  </div>\n"
				+ "  <div>\n"
				+ "    <label>email:</label>\n"
				+ "    <input type=\"text\" name=\"email\" required/>\n"
				+ "  </div>\n"
				+ "  <div>\n"
				+ "    <small>\n"
				+ "      Gender\n"
				+ "    </small>\n"
				+ "    <input type=\"radio\" value=\"Male\" name=\"gender\" required />\n"
				+ "    <label>Male</label>\n"
				+ "    \n"
				+ "    <input type=\"radio\" value=\"Female\" name=\"gender\"/>\n"
				+ "    <label>Female</label>\n"
				+ "  </div>\n"
				+ "  <div>\n"
				+ "    <label>Phone:</label>\n"
				+ "    <input type=\"text\" name=\"phone\" required/>\n"
				+ "  </div>\n"
				+ "  <button>\n"
				+ "    submit\n"
				+ "  </button>\n"
				+ "</form>");
		out.close();
	}
}
