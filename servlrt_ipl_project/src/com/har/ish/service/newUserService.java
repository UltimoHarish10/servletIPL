package com.har.ish.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dao.newUserDao;
import com.har.ish.model.passwordModel;
import com.har.ish.servlets.newUserClass;

public class newUserService {
	private static final Logger logger = LoggerFactory.getLogger(newUserService.class); 

	public void addUser(String User, String Password) {
		logger.info("Inside the new user service class addUser Method");
		System.out.println("Inside addUser");
		newUserDao userDao = new newUserDao();
		try {
			userDao.addUser(User, Password);
			logger.info("addUser Method in the newUserService class is completed");
		} catch (Exception e) {
			logger.error("Exception occured in addUserservice method of newUserService : {}",e);
			e.printStackTrace();
		}
		
	}

	public passwordModel getUser(String User, String Password, passwordModel model) {
		logger.info("Inside the new user service class passwordModel Method");
		System.out.println("Inside get user service");
		newUserDao userDao = new newUserDao();
		if (User != null && Password != null) {
			try {
				model = userDao.getUser(User, Password);
				logger.info("addUser Method in the newUserService class is completed");
			} catch (Exception e) {
				logger.error("Exception occured in getUser service method of newUserService : {}",e);
				e.printStackTrace();
			}
		}
		return model;
	}

}
