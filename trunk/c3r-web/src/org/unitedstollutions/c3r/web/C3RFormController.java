package org.unitedstollutions.c3r.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.unitedstollutions.c3r.model.C3REngine;
import org.unitedstollutions.c3r.model.IfcReader;
import org.unitedstollutions.c3r.model.QueryResultsManager;

import fr.inria.acacia.corese.api.IResults;

/**
 * Servlet implementation class C3RFormController
 */
/**
 * @author ruben
 * 
 */
public class C3RFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String webRootDir = getServletContext().getRealPath("/");

		// Get the user's session
		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();
		String screen = "";
		String customIfcFileName = "customIfc.xml";
		String defaultIfcFileName = "defaultIfc.rdf";
		String customIfcFile = "data" + File.separator + "annotations"
				+ File.separator + customIfcFileName;
		String dataDir = webRootDir + File.separator + "data";
		// this engine data will be made define in an attribute
		// since there is an option to select the project being use - .ifc
		String engineData = dataDir + File.separator + "annotations"
				+ File.separator + "defaultIfc.rdf";
		String engineRule = dataDir + File.separator + "definition_rules";
		String engineSchema = dataDir + File.separator + "schemas"
				+ File.separator + "ontoCC.owl";

		if (selectedScreen.equals("/checker/runSelectedQueries")) {

			String[] selectedQueries = request
					.getParameterValues("selectedQueries");

			if (selectedQueries != null) {
				ArrayList<String> selectedQs = new ArrayList<String>(Arrays
						.asList(selectedQueries));

				// check to see if there are any query run results
				if ((IResults) session.getAttribute("tmpQueryResponse") != null) {
					IResults res = (IResults) session
							.getAttribute("tmpQueryResponse");
					QueryResultsManager qm;

					// create a query manager bean if one doesn't exist in the
					// session
					if (session.getAttribute("queryManager") == null) {
						qm = new QueryResultsManager();
						session.setAttribute("queryManager", qm);
					} else {
						// there exists one - get the instance by looking up
						// in the session through an attribute
						qm = (QueryResultsManager) session
								.getAttribute("queryManager");
					}
					// add the selected queries to the manager
					qm.setQueries(res, selectedQs);

					// use the corese engine to run the selected queries
					C3REngine engine = C3REngine.getInstance();

					// TODO replace engineData with an the projectIfc session
					// attribute
					engine.loadFile(engineData);
					engine.loadFile(engineRule);
					engine.loadFile(engineSchema);

				}
				request.setAttribute("message",
						"queries successfully processed");
			} else {
				request.setAttribute("message",
						"queries NOT successfully processed!");
			}
			screen = "/jsp/checker/runSelectedQueries.jsp";
		} else if (selectedScreen.equals("/checker/loadProject.form")) {
			// gets the root directory of the web application on the system
			// on which
			// it is running. Example:
			// /home/webserver/tomcat/webapps/c3r-web
			String projectFile = request.getParameter("projectIfc");
			// ifcReader is probably not the best name to give to this
			// object, but it will do for now
			// TODO create a new general object to deal with project files
			IfcReader ifc = new IfcReader();
			if (projectFile.equalsIgnoreCase("uri")) {
				System.out.println("!! Setting project from URI");
				String ifcWFile = webRootDir + customIfcFile;
				String uri = request.getParameter("ifcUri");
				try {
					ifc.readFromUri(uri, ifcWFile);
					session.setAttribute("projectIfc", customIfcFileName);
					// DEBUG remove when complete.
					System.out.println("!!!!!wrote to: \n" + ifcWFile);
				} catch (IOException ioe) {
					System.out
							.println("IO exception. You are probably behind a proxy or firewall.");
				}

			} else if (projectFile.equalsIgnoreCase("default2")) {
				String projectFileName = ifc.getAssignedFileName(projectFile);
				System.out.println("!!!! DEFAULT 2 IFC USED !!! ==> "
						+ projectFileName);
				session.setAttribute("projectIfc", projectFileName);
			} else if (projectFile.equalsIgnoreCase("default3")) {
				String projectFileName = ifc.getAssignedFileName(projectFile);
				System.out.println("!!!! DEFAULT 3 IFC USED !!! ==> "
						+ projectFileName);
				session.setAttribute("projectIfc", projectFileName);
			} else {
				String projectFileName = ifc.getAssignedFileName(projectFile);
				System.out.println("!!!! DEFAULT IFC USED !!! ==> "
						+ projectFileName);
				session.setAttribute("projectIfc", projectFileName);
			}
			// this function is useless cuz sometimes the next screen needs to
			// be with .jsp
			// and sometimes not. do not use.
			screen = setNextScreen("/checker/main.jsp");
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
	 * Returns a next screen path by prepending the current path with /jsp.
	 * Useless function. Preferably do not use.
	 * 
	 * @param currentScreen
	 * @return String of next screen
	 */
	private String setNextScreen(String currentScreen) {
		return "/jsp" + currentScreen;
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
