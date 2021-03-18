package com.har.ish.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PersonalDetailListingClassServlet extends HttpServlet {
	
	public void init(){
		
	}
	public void service(HttpServletRequest req,HttpServletResponse res) {
		RequestDispatcher rd = req.getRequestDispatcher("/VIEWS/welcome.jsp");
		try {
			rd.forward(req, res);
		} catch (ServletException | IOException e) {
			try {
				res.sendError(400);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public void destroy(){
		
	}
}
