package com.har.ish.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.har.ish.dto.AllPersonalDetailsDto;
import com.har.ish.dto.FilterDto;
import com.har.ish.service.PersonDetailsService;
import com.har.ish.utilities.CommonMethods;

public class PersonDetailsGetClass extends HttpServlet{
	private static final Logger logger = LoggerFactory.getLogger(PersonDetailsGetClass.class);
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		logger.info("PersonDetailsGetClass is started");
		System.out.println("Personal details getting servlet has been called here");
		if(req.getParameter("Id") != null){
			Integer personId = Integer.parseInt(req.getParameter("Id"));
			PersonDetailsService person = new PersonDetailsService();
			person.getPersonalDetails(personId);
		}
		else{
			PersonDetailsService person = new PersonDetailsService();
			Integer currentPage = null;
			Integer fromPage = null;
			if(req.getHeader(CommonMethods.CURRENTPAGE)!=null){
				currentPage = Integer.parseInt((String)req.getHeader(CommonMethods.CURRENTPAGE));
			}
			if(req.getHeader(CommonMethods.FROMPAGE)!=null){
				fromPage = Integer.parseInt((String)req.getHeader(CommonMethods.FROMPAGE));
			}
			ObjectMapper mapper = new ObjectMapper();
			AllPersonalDetailsDto personDto = null;
			List<AllPersonalDetailsDto> personalDetails= person.getAllPersonalDetails(personDto,currentPage,fromPage);
			if(personalDetails.size() < CommonMethods.SEVEN){
				res.setHeader("LastPage", "true");
			}
			else if(personalDetails.size() >= CommonMethods.SEVEN){
				boolean isLastPage = person.isLastPage(personalDetails.get(personalDetails.size()-1).getId());
				res.setHeader("LastPage", Boolean.toString(isLastPage));
			}
			else{
				res.setHeader("LastPage", "false");
			}
			String json = mapper.writeValueAsString(personalDetails);
			System.out.println(json);
			res.getWriter().write(json);
		    res.getWriter().flush();
		}
		
		
	}

}
