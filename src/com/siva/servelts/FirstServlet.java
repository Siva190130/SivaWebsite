package com.siva.servelts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class FirstServlet extends HttpServlet{
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("The init method gets called");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("doGet() method gets called");
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		writer.println("<h1> Hello Siva You have Created your First Servlet</h1>");
		String name=req.getParameter("name");
		String desig=req.getParameter("Designation");
	    String[] pN = req.getParameterValues("skills");
		System.out.println("Name:" +name);
		System.out.println("Designation:" +desig);
		System.out.println("Skills:");
		for(String i:pN) {
			System.out.print(i+"  ");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/Serv5");
		rd.forward(req, resp);
		writer.println("<h1>Servlet 1 Response2</h1>");
	
}
	@Override
	public void destroy() {
		System.out.println("The destroy Method gets called");
	}
}