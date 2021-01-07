package com.har.ish.filters;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.SCLRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.har.ish.utilities.CommonMethods;

public class SessionCheckingFilter implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(SessionCheckingFilter.class);

	public void init(FilterConfig config) {
		System.out.println("Session Checking Filter class is called");
	}

	public void doFilter(ServletRequest request, ServletResponse Response, FilterChain chain) {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) Response;
		try {
			System.out.print("Session check filter is called here");
			HttpSession session = req.getSession();
			if (session != null) {
				if (session.getAttribute(CommonMethods.USERNAME) == null) {
					logger.info("Inside if condition of the CommonMethods.USERNAME");
					session.invalidate();
					res.sendError(CommonMethods.FIVEHUNDRED,
							"Session is invalidated Please login to do the actions in the application");
				} else {
					chain.doFilter(req, res);
				}
			} else {
				res.sendError(CommonMethods.FIVEHUNDRED, "Please Login into the application");
			}

		} catch (Exception e) {
			logger.error("Exception occured during session checking filter", e);
		}

	}

	public void destroy() {

	}

}
