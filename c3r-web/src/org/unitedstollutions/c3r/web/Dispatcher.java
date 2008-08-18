package org.unitedstollutions.c3r.web;

/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unitedstollutions.c3r.old.Query;

public class Dispatcher extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String bookId = null;
		String clear = null;
		String submit = request.getParameter("submit");
//		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();

//		Query qry = (Query) session.getAttribute("cart");
		Query qry = (Query) getServletContext().getAttribute("cart");

		if (qry == null) {
			qry = new Query();
			getServletContext().setAttribute("cart", qry);
		}

		// never enters here for now
		if (selectedScreen.equals("/validator")) {
			bookId = request.getParameter("Add");
			bookId = "";

			if (!bookId.equals("")) {
				/*
				 * try { book = bookDBAO.getBook(bookId); cart.add(bookId,
				 * book); } catch (BookNotFoundException ex) { // not possible }
				 */

			}

		}

		String screen = "/jsp/" + selectedScreen + ".jsp";

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
