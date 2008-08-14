/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

package org.unitedstollutions.c3r.old;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CustomerDispatcher extends HttpServlet {

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession();

		/*
		 * These things here get the database context - class that creates the
		 * database context
		 * 
		 * BrokerDelegate delegate = BrokerDelegate.getInstance();
		 * BrokerDelegate delegate = new BrokerDelegate();
		 */
		String id = request.getParameter("customerIdentity");
		String name = request.getParameter("customerName");
		String address = request.getParameter("customerAddress");
		String submit = request.getParameter("submit");
		
		if (submit != null) {

			try {

				if (submit.equals("Get Customer")) {
					// Customer cust = delegate.getCustomer(id);
					Query cust = new Query();
					request.setAttribute("customer", cust);
				} else if (submit.equals("Update Customer")) {
					// delegate.updateCustomer(id, name, address);
					// Customer cust = delegate.getCustomer(id);
					Query cust = new Query();
					request.setAttribute("customer", cust);
				} else if (submit.equals("Add Customer")) {
					// delegate.addCustomer(id, name, address);
					// Customer cust = delegate.getCustomer(id);
					Query cust = new Query();
					request.setAttribute("customer", cust);
				} else if (submit.equals("Delete Customer")) {
					// delegate.deleteCustomer(id);
					request.removeAttribute("customer");
				}
			} catch (Exception be) {
				request.setAttribute("message", be.getMessage());
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("CustomerDetails");
			dispatcher.forward(request, response);

		} else {

			try {

				// Customer[] customers = delegate.getAllCustomers();
				ArrayList<Query> customers = new ArrayList<Query>();
				request.setAttribute("customers", customers);

			} catch (Exception be) {
				request.setAttribute("message", be.getMessage());
			}

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("checkrules.jsp");
			dispatcher.forward(request, response);

		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
		
//		HttpSession session = request.getSession();
//		String selectedScreen = request.getServletPath();
//
//		Query qry = (Query) session.getAttribute("query");
//
//		if (qry == null) {
//			qry = new Query();
//			session.setAttribute("query", qry);
//		}

		/*
		 * if (selectedScreen.equals("/bookcatalog")) { bookId =
		 * request.getParameter("Add");
		 * 
		 * if (!bookId.equals("")) { try { book = bookDBAO.getBook(bookId);
		 * 
		 * if (book.getOnSale()) { double sale = book.getPrice() .85; Float
		 * salePrice = new Float(sale); book.setPrice(salePrice.floatValue()); }
		 * 
		 * cart.add(bookId, book); } catch (BookNotFoundException ex) { // not
		 * possible } } } else if (selectedScreen.equals("/bookshowcart")) {
		 * bookId = request.getParameter("Remove");
		 * 
		 * if (bookId != null) { cart.remove(bookId); }
		 * 
		 * clear = request.getParameter("Clear");
		 * 
		 * if ((clear != null) && clear.equals("clear")) { cart.clear(); } }
		 * else if (selectedScreen.equals("/bookreceipt")) { // Update the
		 * inventory try { utx.begin(); bookDBAO.buyBooks(cart); utx.commit(); }
		 * catch (Exception ex) { try { utx.rollback();
		 * request.getRequestDispatcher("/bookordererror.jsp") .forward(request,
		 * response); } catch (Exception e) {
		 * System.out.println("Rollback failed: " + e.getMessage());
		 * e.printStackTrace(); } } }
		 */
//		try {
//			request.getRequestDispatcher("/jsp/check.jsp").forward(request,
//					response);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);

		
/*		request.setAttribute("selectedScreen", request.getServletPath());

		try {
			request.getRequestDispatcher("/jsp/checkrules.jsp").forward(
					request, response);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		*/
	}

    public String getServletInfo() {
        return "Short description";
    }
	
}
