package com.har.ish.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.har.ish.dto.PasswordModel;

public class loginclass extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(loginclass.class); 
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		logger.info("Inside the login servlet");
		System.out.println("backend called here");
		boolean attr = (boolean)req.getAttribute("isAuthenticated");
		logger.debug("The user is authenticated {}",attr);
		if(attr){
			HttpSession session = req.getSession();
			String user = req.getAttribute("User").toString();
			logger.info("The user name here is {}",user);
			String password = req.getAttribute("Password").toString();
			logger.info("The Password here is {}",password);
			session.setAttribute("userName", user);
			System.out.println("The session id here is"+session.getId());
			res.setHeader("isAuthenticated", "true");
		}
	}

}
