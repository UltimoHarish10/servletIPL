package com.har.ish.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutClassServlet extends HttpServlet{
	
	public void init(){
		
	}
	public void service(HttpServletRequest req,HttpServletResponse res){
		HttpSession session = req.getSession();
		session.invalidate();
	}
	public void destroy(){
		
	}
}
