package com.ranjith.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/")
public class DashboardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<a href="+ req.getContextPath()+"/users/all>Show All Users</a>");
		out.println("<a href="+ req.getContextPath()+"/users/form>Register User</a>");
		out.println("<a href="+ req.getContextPath()+"/groups/all>Show All Groups</a>");
		out.println("<a href="+ req.getContextPath()+"/groups/form>Add Group</a>");
		out.close();
	}
	
	
}
