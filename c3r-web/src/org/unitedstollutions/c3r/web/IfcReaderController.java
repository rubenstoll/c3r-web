package org.unitedstollutions.c3r.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.unitedstollutions.c3r.model.IfcReader;

/**
 * Servlet implementation class IfcReaderController
 */
public class IfcReaderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String ifcProject = request.getParameter("ProjectIfc");
		String ifcFile;
		String ifcFileUri;
		
		// Get the user's session and shopping cart
		HttpSession session = request.getSession();
		IfcReader ir = (IfcReader)session.getAttribute("ifcReader");
		if(ir == null) {
			ir = new IfcReader();
			session.setAttribute("ifcReader", ir);
		}
		
		if (ifcProject.equals("Custom")) {
			ifcFile = request.getParameter("ifcFileName");
			ir.createCustomIfc(ifcFile);
		} else if(ifcProject.equals("Uri")) {
			ifcFileUri = request.getParameter("ifcUri");
			ir.readFromUri(ifcFileUri);
		} else {
			ir.setDefaultIfcfile();
		}

		String screen = "/jsp/checker/main.jsp";
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher(screen);
			dispatcher.forward(request, response);
		} catch (Exception ex) {
			System.out.println("IfcReaderController Exception");
			ex.printStackTrace();
		}	
		
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
