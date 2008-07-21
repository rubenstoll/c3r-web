/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package org.unitedstollutions.c3r.dispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unitedstollutions.c3r.model.Query;


public class Dispatcher extends HttpServlet {

    public void doGet(
        HttpServletRequest request,
        HttpServletResponse response) {
    	
        HttpSession session = request.getSession();
        String selectedScreen = request.getServletPath();

        Query qry = (Query) session.getAttribute("query");
        	
        if (qry == null) {
            qry = new Query();
            session.setAttribute("query", qry);
        }

/*        if (selectedScreen.equals("/bookcatalog")) {
            bookId = request.getParameter("Add");

            if (!bookId.equals("")) {
                try {
                    book = bookDBAO.getBook(bookId);

                    if (book.getOnSale()) {
                        double sale = book.getPrice() * .85;
                        Float salePrice = new Float(sale);
                        book.setPrice(salePrice.floatValue());
                    }

                    cart.add(bookId, book);
                } catch (BookNotFoundException ex) {
                    // not possible
                }
            }
        } else if (selectedScreen.equals("/bookshowcart")) {
            bookId = request.getParameter("Remove");

            if (bookId != null) {
                cart.remove(bookId);
            }

            clear = request.getParameter("Clear");

            if ((clear != null) && clear.equals("clear")) {
                cart.clear();
            }
        } else if (selectedScreen.equals("/bookreceipt")) {
            // Update the inventory
            try {
                utx.begin();
                bookDBAO.buyBooks(cart);
                utx.commit();
            } catch (Exception ex) {
                try {
                    utx.rollback();
                    request.getRequestDispatcher("/bookordererror.jsp")
                           .forward(request, response);
                } catch (Exception e) {
                    System.out.println("Rollback failed: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
*/
        try {
            request.getRequestDispatcher("/jsp/template/template.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response) {
        request.setAttribute(
            "selectedScreen",
            request.getServletPath());

        try {
            request.getRequestDispatcher("/template/template.jsp")
                   .forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
