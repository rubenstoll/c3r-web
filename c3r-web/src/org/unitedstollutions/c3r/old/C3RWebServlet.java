/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

package org.unitedstollutions.c3r.old;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * An HTTP Servlet that overrides the service method to return a simple web
 * page.
 */
public class C3RWebServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		ResourceBundle messages = (ResourceBundle) session
				.getAttribute("messages");

		if (messages == null) {
			Locale locale = request.getLocale();
			messages = ResourceBundle.getBundle(
					"org.unitedstollutions.c3r.messages.C3RWebMessages", locale);
			session.setAttribute("messages", messages);
		}

		// set content-type header before accessing the Writer
		response.setContentType("text/html");
		response.setBufferSize(8192);

		PrintWriter out = response.getWriter();

		// then write the data of the response
		out.println("<html>" + "<head><title>C3RWeb</title></head>");

		out.close();
	}

	public String getServletInfo() {
		return "The BookStore servlet returns the main web page "
				+ "for C3R Web Construction Rule Checker.";
	}
}
