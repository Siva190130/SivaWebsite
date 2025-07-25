package com.siva.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class driver
 */
@WebServlet("/driver")
public class driver extends HttpServlet {
	Connection con = null;
	PreparedStatement pstm=null;
	Statement stmt=null;
	ResultSet rest=null;
	String url =null;
	String un=null;
	String psw=null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			url=config.getInitParameter("url");
			un=config.getInitParameter("username");
			psw=config.getInitParameter("password");
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection(url,un,psw);
			System.out.println("connection Establised");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
          throws ServletException, IOException {
		try {
			String query1="select *from Drive;";
			stmt=con.createStatement();
			rest=stmt.executeQuery(query1);
			PrintWriter writer =resp.getWriter();
			writer.println("<h1>Drives conducting in RGUKT</h1>");
			writer.println("<table border=\"1\" cellpadding=\"8\" cellspacing=\"0\" style=\"border-collapse: collapse; width: 100%; max-width: 600px; margin: 20px auto; font-family: Arial, sans-serif;\">\r\n" + 
					"  <thead style=\"background-color: #667eea; color: white;\">\r\n" + 
					"    <tr>\r\n" + 
					"      <th>ID</th>\r\n" + 
					"      <th>Company name</th>\r\n" + 
					"      <th>marks 10th</th>\r\n" + 
					"      <th>marks 12th</th>\r\n" + 
					"      <th>marks bteh</th>\r\n" +
					"      <th>package</th>\r\n"    +
					"      <th>Skills</th>\r\n"     +
					"    </tr>\r\n" + 
					"  </thead>\r\n");
					
		while(rest.next()==true) {
			int id =rest.getInt(1);
			String name=rest.getString(2);
			float mark10=rest.getFloat(3);
			float mark12=rest.getFloat(4);
			float markBtech=rest.getFloat(5);
			float package1=rest.getFloat(6);
			String skills=rest.getString(7);
			writer.println("<thead style=\"background-color: #667eea; color: white;\">\r\n<tr>" + 
					"      <th>"+id+"</th>\r\n" + 
					"      <th>"+name+"</th>\r\n" + 
					"      <th>"+mark10+"</th>\r\n" + 
					"      <th>"+mark12+"</th>\r\n" + 
					"      <th>"+markBtech+"</th>\r\n" +
					"      <th>"+package1+"</th>\r\n" +
					"      <th>"+skills+"</th>\r\n" +
					"    </tr>\r\n" + 
					"  </thead>\r\n"
					 
				);
			}
		writer.println("</table>");
		RequestDispatcher rd=req.getRequestDispatcher("/Serv4");
		rd.include(req, resp);
	}
		catch(Exception e) {
			e.printStackTrace();
		}
			
		}
		
	}
	
	

