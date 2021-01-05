package com.har.ish.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.service.PersonDetailsService;

public class PersonDetailsGetClass extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(PersonDetailsGetClass.class);
	
	public void service(HttpServletRequest req,HttpServletResponse res){
		logger.info("PersonDetailsGetClass is started");
		System.out.println("Personal details getting servlet has been called here");
		if(req.getParameter("Id") != null){
			Integer personId = Integer.parseInt(req.getParameter("Id"));
			PersonDetailsService person = new PersonDetailsService();
			person.getPersonalDetails(personId);
		}
		else{
			PersonDetailsService person = new PersonDetailsService();
			AllPersonalDetailsDto personDto = null;
			person.getAllPersonalDetails(personDto);
		}
		
		
	}

}
