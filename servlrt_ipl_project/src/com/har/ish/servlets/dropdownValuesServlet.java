package com.har.ish.servlets;

import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.har.ish.dto.FilterDto;
import com.har.ish.service.PersonDetailsService;
import com.har.ish.utilities.CommonMethods;

public class dropdownValuesServlet extends HttpServlet{
	
	public void init(){
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res){
		FilterDto dto = new FilterDto();
		PersonDetailsService personService = new PersonDetailsService();
		HttpSession session = req.getSession();
		try{
			Object obj = session.getAttribute(CommonMethods.DROPDOWNVALUES);
			if(obj instanceof FilterDto){
				dto = (FilterDto)obj;
			}
			if(dto.getCountries() == null){
				dto = personService.populateFilterDtoForPerson(dto);
				session.setAttribute(CommonMethods.DROPDOWNVALUES, dto);
			}
			res.sendRedirect(req.getContextPath()+"/personalDetailsListing");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void destroy(){
		
	}
}
