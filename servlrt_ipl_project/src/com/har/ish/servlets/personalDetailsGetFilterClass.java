package com.har.ish.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.har.ish.dto.FilterDto;
import com.har.ish.service.PersonDetailsService;
import com.har.ish.utilities.CommonMethods;
import com.har.ish.dto.AllPersonalDetailsDto;


public class personalDetailsGetFilterClass extends HttpServlet{
	
	public static final Logger logger = LoggerFactory.getLogger(personalDetailsGetFilterClass.class);
	
	public void doPost(HttpServletRequest req,HttpServletResponse res){
		logger.info("Inside the personalDetailsGetFilterClass");
		PersonDetailsService personalServices = new PersonDetailsService();
		try{
			FilterDto filterDto = new Gson().fromJson(req.getReader(),FilterDto.class);
			List<AllPersonalDetailsDto> personDetails = new ArrayList<>();
			if(filterDto != null){
				logger.info("Inside if condition of the personalDetailsGetFilterClass");
				Integer currentPage = null;
				Integer fromPage = null;
				if(req.getHeader(CommonMethods.CURRENTPAGE)!=null){
					currentPage = Integer.parseInt((String)req.getHeader(CommonMethods.CURRENTPAGE));
				}
				if(req.getHeader(CommonMethods.FROMPAGE)!=null){
					fromPage = Integer.parseInt((String)req.getHeader(CommonMethods.FROMPAGE));
				}
				ObjectMapper mapper = new ObjectMapper();
				List<AllPersonalDetailsDto> personalDetailsDto = personalServices.getPersonDetailsByFilters(filterDto,personDetails,currentPage,fromPage);
				if(personalDetailsDto.size() < CommonMethods.SEVEN){
					res.setHeader("LastPage", "true");
				}
				else if(personalDetailsDto.size() >= CommonMethods.SEVEN){
					boolean isLastPage = personalServices.isLastPage(personalDetailsDto.get(personalDetailsDto.size()-1).getId());
					res.setHeader("LastPage", Boolean.toString(isLastPage));
				}
				else{
					res.setHeader("LastPage", "false");
				}
				String json = mapper.writeValueAsString(personalDetailsDto);
				System.out.println(json);
				res.getWriter().write(json);
			    res.getWriter().flush();
			}
		}
		catch(Exception e){
			logger.error("Exception occured in the personalDetailsGetFilterClass : {}",e);
			e.printStackTrace();
		}
	}

}
