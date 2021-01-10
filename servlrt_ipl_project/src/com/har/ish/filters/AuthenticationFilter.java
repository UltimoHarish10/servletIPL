package com.har.ish.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.har.ish.dto.PasswordModel;
import com.har.ish.model.passwordModel;
import com.har.ish.service.newUserService;
import com.har.ish.utilities.CommonMethods;

public class AuthenticationFilter implements Filter {
	
	public static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);
	public void init(FilterConfig config) {
		System.out.println("Auth filter class is called");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws java.io.IOException, ServletException {
		try{
		logger.info("Inside the AuthenticationFilter class is started");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		logger.info("doFilter method is called here in authentication Filter");
		logger.info("---------------Checking the req.getAttribute(isAuthenticated)-------------------");
		if(session.getAttribute("isAuthenticated")!=null){
			logger.info("-----------------Inside If condition and the value is "+session.getAttribute("isAuthenticated")+"-----------------");
			chain.doFilter(req, res);
		}
		else{
		logger.info("-------------------Inside Else condition and the value is "+ null +"----------------------");
		PasswordModel pass = new Gson().fromJson(req.getReader(), PasswordModel.class);
		String user = pass.getUsername();
		String password = pass.getPassword();
		logger.debug("The user name here in filter class is {}",user);
		System.out.println("User Principal here is "+req.getUserPrincipal());
		newUserService userService = new newUserService();
		passwordModel model = new passwordModel();
		model = userService.getUser(user, password, model);
		if (model.getUserName() != null && model.getPassword() != null) {
			System.out.println(model.getUserName() + " " + model.getPassword());
			req.setAttribute(CommonMethods.ISAUTHENTICATED, true);
			req.setAttribute(CommonMethods.USER, model.getUserName());
			req.setAttribute(CommonMethods.PASSWORD, model.getPassword());
			session.setAttribute(CommonMethods.USERNAME, model.getUserName());
			session.setAttribute(CommonMethods.ISAUTHENTICATED, true);
			session.setMaxInactiveInterval(CommonMethods.HUNDRED);
			chain.doFilter(req, res);
		}
		else{
			res.setStatus(CommonMethods.FOURHUNDREDANDSEVENTY);
		}
		logger.info("Authentication Filter class is completed successfully");
		}
		}
		catch(Exception e){
			logger.error("Error occured in the authentication filter class : {}",e);
			e.printStackTrace();
		}
	}

	public void destroy() {

	}

}