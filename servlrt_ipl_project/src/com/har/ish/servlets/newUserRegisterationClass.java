package com.har.ish.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class newUserRegisterationClass extends HttpServlet{
	
	public void init(){
		
	}
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/VIEWS/newUser.jsp");
		rd.forward(req, res);	
	}
	public void destroy(){
		
	}

}
