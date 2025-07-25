package com.siva.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {

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
		resp.setContentType("text/html");
		String username=req.getParameter("username");
		String password=req.getParameter("password1");
		PrintWriter writer=resp.getWriter();
		String query="select * from siva1 where username=? and password1=?";
		try {
			pstm=con.prepareStatement(query);
			pstm.setString(1, username);
			pstm.setString(2,password);
			rest=pstm.executeQuery();
			if(rest.next()==true) {
				writer.println("<h1>"+username+" Your Login was Sucessfull </h1>");
				RequestDispatcher rd =req.getRequestDispatcher("/Serv3");
				rd.include(req, resp);
			}
			else {
				writer.println("<h2>Invalid Login try again</h2>");
				RequestDispatcher rd= req.getRequestDispatcher("/Invalid.html");
				rd.forward(req, resp);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

}
