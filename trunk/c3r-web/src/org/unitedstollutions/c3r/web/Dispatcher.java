package org.unitedstollutions.c3r.web;

/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unitedstollutions.c3r.model.Query;

public class Dispatcher extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String bookId = null;
		String clear = null;

		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();

		Query qry = (Query) session.getAttribute("cart");

		if (qry == null) {
			qry = new Query();
			session.setAttribute("cart", qry);
		}

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
			// RequestDispatcher dispatcher = request
			// .getRequestDispatcher("/jsp/validatormain.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(screen);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("hey hey!");
			ex.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String screen = request.getServletPath() + ".jsp";

		try {
			request.getRequestDispatcher(screen).forward(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
