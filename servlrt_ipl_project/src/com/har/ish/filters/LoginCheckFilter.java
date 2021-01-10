package com.har.ish.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.utilities.CommonMethods;

public class LoginCheckFilter implements Filter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
	
	public void init(FilterConfig config) {
		System.out.println("Login Checking Filter class is called");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain){
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		logger.info("Login checking filter class is called here");
		if(session.getAttribute(CommonMethods.ISAUTHENTICATED) != null){
			logger.info("Inside the username checking if condition.....");
			try {
				res.sendRedirect(req.getContextPath()+"/getpersonsbyfilters");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			logger.info("Inside Else condition of LoginCheckFilter.....");
			try {
				chain.doFilter(req, res);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public void destroy(){
		
	}

}
