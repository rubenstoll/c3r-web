package org.unitedstollutions.c3r.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class C3RFormController
 */
public class C3RFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		
		// Get the user's session
		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();
		String screen = "";

		if (selectedScreen.equals("/checker/runSelectedQueries")) {
			String selectedQueries = (String)session.getAttribute("selectedQueries");
			String selectedQs = (String)request.getParameter("selectedQueries");
			request.setAttribute("mymessage", "this");
			if (selectedQueries != null) {
				String queries = "hey hey hey" + selectedQueries;
				session.setAttribute("checkedResults1", queries);			
				request.setAttribute("message1", "queries in session!");
			} else {
				session.setAttribute("checkedResults1", "No Results");
				request.setAttribute("message1", "No queries in session");
			}
			if (selectedQs != null) {
				String Qs =  selectedQs;
				session.setAttribute("checkedResults2", Qs);			
				request.setAttribute("message2", "queries in request");
			} else {
				session.setAttribute("checkedResults2", "No Results");
				request.setAttribute("message2", "No queries in request");
			}
			screen = "/jsp/checker/runSelectedQueries.jsp";
		} else {
			screen = "/jsp" + selectedScreen + ".jsp";
		}


		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(screen);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("Form Controller Exception");
			ex.printStackTrace();
		}

	}


	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO add servlet information data if needed
		return super.getServletInfo();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
