package com.har.ish.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.har.ish.dto.FilterDto;
import com.har.ish.service.PersonDetailsService;
import com.har.ish.dto.AllPersonalDetailsDto;


public class personalDetailsGetFilterClass extends HttpServlet{
	
	public static final Logger logger = LoggerFactory.getLogger(personalDetailsGetFilterClass.class);
	
	public void service(HttpServletRequest req,HttpServletResponse res){
		logger.info("Inside the personalDetailsGetFilterClass");
		PersonDetailsService personalServices = new PersonDetailsService();
		try{
			FilterDto filterDto = new Gson().fromJson(req.getReader(),FilterDto.class);
			List<AllPersonalDetailsDto> personDetails = new ArrayList<>();
			if(filterDto != null){
				logger.info("Inside if condition of the personalDetailsGetFilterClass");
				personalServices.getPersonDetailsByFilters(filterDto,personDetails);
			}
		}
		catch(Exception e){
			logger.error("Exception occured in the personalDetailsGetFilterClass : {}",e);
			e.printStackTrace();
		}
	}

}
