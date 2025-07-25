package com.siva.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Eligible")
public class Eligible extends HttpServlet {
	Connection con = null;
	PreparedStatement pstm=null,pstm1=null;
	Statement stmt=null;
	ResultSet rest=null,rest1=null;
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	          throws ServletException, IOException {
		try {
			resp.setContentType("text/html");
			String username=req.getParameter("username");
			String password=req.getParameter("password1");
			PrintWriter writer=resp.getWriter();
			
			String query="select *from siva1 where username=? and password1=?";
			pstm=con.prepareStatement(query);
			pstm.setString(1, username);
			pstm.setString(2, password);
			rest=pstm.executeQuery();
			rest.next();
			float mark1_10=rest.getFloat(5);
			float mark1_12=rest.getFloat(6);
			float btech1=rest.getFloat(7);
			String query1= "select *from Drive where marks_10<=? and marks_12<=? and marks_btech<=?";
			pstm1=con.prepareStatement(query1);
			pstm1.setFloat(1, mark1_10);
			pstm1.setFloat(2, mark1_12);
			pstm1.setFloat(3, btech1);
			rest1=pstm1.executeQuery();
			
			writer.println("<h2>Drives "+username+" eligible for:</h2>");
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
			while(rest1.next()==true) {
				int id =rest1.getInt(1);
				String name=rest1.getString(2);
				float mark10=rest1.getFloat(3);
				float mark12=rest1.getFloat(4);
				float markBtech=rest1.getFloat(5);
				float package1=rest1.getFloat(6);
				String skills=rest1.getString(7);
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
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
