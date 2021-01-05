package com.har.ish.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.har.ish.dto.PasswordModel;
import com.har.ish.service.newUserService;

public class newUserClass extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(newUserClass.class); 
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("The new user class called here");
		logger.info("New user Servlet class is called here");
		PasswordModel pass = new Gson().fromJson(req.getReader(), PasswordModel.class);
		String user = pass.getUsername();
		logger.debug("The username of the new user here is {}",user);
		String password = pass.getPassword();
		logger.debug("The password of the new user here is {}",password);
		if (user != null && password != null) {
			logger.info("Inside if block of the new user Servlet");
			try {
				newUserService a = new newUserService();
				System.out.println("The new user service called here");
				a.addUser(user, password);
			} catch (Exception e) {
				logger.error("Exception occured while creating new User : {}",e);
				e.printStackTrace();
			}
		}
		res.setHeader("UserAdd", String.valueOf(1));
	}

}
