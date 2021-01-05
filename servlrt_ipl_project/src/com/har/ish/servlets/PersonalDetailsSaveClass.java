package com.har.ish.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.dto.PasswordModel;
import com.har.ish.service.PersonDetailsService;

public class PersonalDetailsSaveClass extends HttpServlet{
	
	private static final Logger logger = LoggerFactory.getLogger(PersonalDetailsSaveClass.class);
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		logger.info("Inside PersonalDetailsSaveClass is started");
		PersonDetailsService personService = new PersonDetailsService();
		try{
		AllPersonalDetailsDto personDet = new AllPersonalDetailsDto();
		personDet = new Gson().fromJson(req.getReader(), AllPersonalDetailsDto.class);
		personService.saveAllPersonalDetails(personDet);	
		}
		catch(Exception e){
			logger.error("Exception occured in the PersonalDetailsSaveClass : {}",e);
			e.printStackTrace();
		}
		
	}

}
