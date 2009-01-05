package org.unitedstollutions.c3r.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.unitedstollutions.c3r.model.C3REngine;
import org.unitedstollutions.c3r.model.ProjectIfc;
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

	Logger logger = Logger.getLogger(C3RFormController.class);
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		String webRootDir = getServletContext().getRealPath("/");

		// Get the user's session
		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();
		String screen = "";

		if (selectedScreen.equals("/checker/runSelectedQueries")) {

			String dataDir = webRootDir + File.separator + "data";
			// this engine data will be made define in an attribute
			// since there is an option to select the project being use - .ifc
			String engineData = dataDir + File.separator + "annotations"
					+ File.separator + "defaultIfc.rdf";
			String engineRule = dataDir + File.separator + "definition_rules";
			String engineSchema = dataDir + File.separator + "schemas"
					+ File.separator + "ontoCC.owl";

			String[] selectedQueries = request
					.getParameterValues("selectedQueries");

			if (selectedQueries != null) {
				ArrayList<String> selectedQs = new ArrayList<String>(Arrays
						.asList(selectedQueries));

				// check to see if there are any query run results
				if ((IResults) session.getAttribute("tmpQueryResponse") != null) {
					IResults res = (IResults) session
							.getAttribute("tmpQueryResponse");

					QueryResultsManager qm = new QueryResultsManager();
					// add the selected queries to the manager
					qm.setQueries(res, selectedQs);
					// use the corese engine to run the selected queries
					C3REngine engine = C3REngine.getInstance();

					// TODO replace engineData with an the projectIfc session
					// attribute
					engine.loadFile(engineData);
					engine.loadFile(engineRule);
					engine.loadFile(engineSchema);

					HashMap<String, ArrayList<String>> results = engine
							.runMappedQueries(qm.getQueries());
					qm.setResults(results);
					session.setAttribute("subQueryRunResults", qm);

				}
				request.setAttribute("message",
						"queries successfully processed");
			} else {
				request.setAttribute("message",
						"queries NOT successfully processed!");
			}
			screen = "/jsp/checker/runSelectedQueries.jsp";
		} else if (selectedScreen.equals("/checker/loadProject.form")) {
			// the following parameter comes from loadProject.jsp
			String projectFile = request.getParameter("projectIfc");
			// TODO create a new general object to deal with project files
			ProjectIfc ifc = (ProjectIfc) getServletContext().getAttribute("project");
			if (projectFile.equalsIgnoreCase("uri")) {
				logger.debug("!! Setting project from URI");
				ifc.setIfcFile("custom");
				String uri = request.getParameter("ifcUri");
				try {
					ifc.readFromUri(uri);
					// DEBUG remove when complete.
					logger.debug("!!!!!wrote to: \n" + ifc.getIfcFile());
				} catch (IOException ioe) {
					System.out
							.println("IO exception. You are probably behind a proxy or firewall.");
				}

			} else if (projectFile.equalsIgnoreCase("default2")) {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger.debug("!!!! DEFAULT 2 IFC USED !!! ==> "
						+ projectFileName);
				// this session attribute is not needed because the projectIfc Object is available in the application
				session.setAttribute("projectIfc", projectFileName);
			} else if (projectFile.equalsIgnoreCase("default3")) {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger.debug("!!!! DEFAULT 3 IFC USED !!! ==> "
						+ projectFileName);
				// this session attribute is not needed because the projectIfc Object is available in the application
				session.setAttribute("projectIfc", projectFileName);
			} else {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger.debug("!!!! DEFAULT IFC USED !!! ==> "
						+ projectFileName);
				// this session attribute is not needed because the projectIfc Object is available in the application
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
			logger.debug("Form Controller Exception");
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
