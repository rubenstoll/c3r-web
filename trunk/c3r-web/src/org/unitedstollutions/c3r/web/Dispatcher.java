package org.unitedstollutions.c3r.web;

/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Dispatcher extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

//		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();

		String screen= "/jsp/checker/main.jsp";

		// like this it works
		if (selectedScreen.equals("/checker/date")) {
			screen = "/jsp/checker/date.jsp";
		} else if(selectedScreen.equals("/checker/processQueryGroupConfigurations")) {
			String submit = request.getParameter("submit");
			if(submit.equalsIgnoreCase("Cancel")) {
				screen = "/jsp/checker/main.jsp";
			} else {
				screen = "/jsp/checker/processQueryGroupConfigurations.jsp";
			}
		} else {
			screen = "/jsp" + selectedScreen + ".jsp";
		}
		

		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(screen);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("hey hey!");
			ex.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		processRequest(request, response);
		/*
		 * String screen = request.getServletPath() + ".jsp"; try {
		 * request.getRequestDispatcher(screen).forward(request, response); }
		 * catch (Exception ex) { ex.printStackTrace(); }
		 */
	}

	public String getServletInfo() {
		return "Short description";
	}
}
