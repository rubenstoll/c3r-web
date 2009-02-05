package org.unitedstollutions.c3r.web;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.unitedstollutions.c3r.model.C3REngine;
import org.unitedstollutions.c3r.model.IfcTransformer;
import org.unitedstollutions.c3r.model.ProjectIfc;
import org.unitedstollutions.c3r.model.QueryResultsManager;
import org.unitedstollutions.c3r.utils.URLUtils;

import fr.inria.acacia.corese.api.IResults;
import fr.inria.acacia.corese.exceptions.EngineException;

/**
 * Servlet implementation class C3RFormController
 */
/**
 * @author yurchyshyna
 * 
 */
public class C3RFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Logger logger = Logger.getLogger(C3RFormController.class);

	private String webRootDir;
	
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) {

		// Get the user's session
		HttpSession session = request.getSession();
		String selectedScreen = request.getServletPath();
		String screen = "";

		this.webRootDir = getServletContext().getRealPath("/");
		
		if (selectedScreen.equals("/checker/runSelectedQueries")) {

			// project ifc object contains all the project information.
			// It has a default value but it can be configured in the load
			// project page.
			ProjectIfc ifc = (ProjectIfc) getServletContext().getAttribute(
					"project");

			String dataDir = webRootDir + File.separator + "data";
			String engineData = dataDir + File.separator + "annotations"
					+ File.separator + ifc.getIfcFile();
			String engineRule = dataDir + File.separator + "definition_rules";
			// TODO see if a schema property can be added to the ProjectIfc for
			// flexibility
			String engineSchema = dataDir + File.separator + "schemas"
					+ File.separator + "ontoCC.owl";

			String[] selectedQueries = request
					.getParameterValues("selectedQueries");

			logger.debug("processing selected queries");

			if (selectedQueries != null) {
				ArrayList<String> selectedQs = new ArrayList<String>(Arrays
						.asList(selectedQueries));

				IResults res = (IResults) session
						.getAttribute("tmpQueryResponse");

				logger.debug("getting results from attribute");
				// check to see if there are any query run results
				if ( res != null) {

					// this engine was initialized in the context listener
//					C3REngine engine = (C3REngine) getServletContext()
//							.getAttribute("c3r_engine");
					
					// this had to be done because sewese changes the behavior of log4j.
					C3REngine engine = new C3REngine();
					String log4jConf = webRootDir + getServletContext().getInitParameter("ENGINE_LOG4J");
					if(log4jConf != null) {
						engine.setLog4jProperties(log4jConf);
					}
					engine.createIEngineInstance();

					QueryResultsManager qm = new QueryResultsManager();
					// add the selected queries to the manager
					qm.setQueries(res, selectedQs);
					
					// use the corese engine to run the selected queries
					logger.debug("Loading engine with the following data");
					logger.debug("engine data: " + engineData);
					logger.debug("engine rules: " + engineRule);
					logger.debug("engine schemas: " + engineSchema);

					try {
						engine.loadFile(engineData);
						engine.loadFile(engineRule);
						engine.loadFile(engineSchema);

						HashMap<String, ArrayList<String>> results = engine
								.runMappedQueries(qm.getQueries());
						qm.setResults(results);
						session.setAttribute("subQueryRunResults", qm);
					} catch (EngineException ee) {
						ee.printStackTrace();
					}

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
			// ProjectIfc holds a project's definitions/settings
			ProjectIfc ifc = (ProjectIfc) getServletContext().getAttribute(
					"project");
			if (projectFile.equalsIgnoreCase("uri")) {
				logger.debug("Setting project from URI");
				String uri = request.getParameter("ifcUri");
				// read ifc project file from uri and process it for correctness
				// String dataDir = webRootDir + File.separator + "data";
				// String annoDir = dataDir + File.separator + "annotations";
				// ifc.setIfcLocation(annoDir);
				ifc.setIfcFile("custom");
				processIfcFromUri(uri, ifc);
				// ifc.setIfcFile("custom");
			} else if (projectFile.equalsIgnoreCase("default2")) {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger.debug("!!!! DEFAULT 2 IFC USED !!! ==> "
						+ projectFileName);
				// this session attribute is not needed because the projectIfc
				// Object is available in the application
				session.setAttribute("projectIfc", projectFileName);
			} else if (projectFile.equalsIgnoreCase("default3")) {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger.debug("!!!! DEFAULT 3 IFC USED !!! ==> "
						+ projectFileName);
				// this session attribute is not needed because the projectIfc
				// Object is available in the application
				session.setAttribute("projectIfc", projectFileName);
			} else {
				ifc.setIfcFile(projectFile);
				String projectFileName = ifc.getIfcFile();
				logger
						.debug("!!!! DEFAULT IFC USED !!! ==> "
								+ projectFileName);
				// this session attribute is not needed because the projectIfc
				// Object is available in the application
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
	 * Reads in a file from a remote location (uri) and transforms the file to a
	 * format which can be understood by the corese engine.
	 * 
	 * @param uri
	 *            location of the project file to read.
	 * @param ifc
	 *            the project settings object from which to get projects
	 *            specifcs and set the new file name
	 */
	private void processIfcFromUri(String uri, ProjectIfc ifc) {

		logger.debug("Reading from URI and writing to local file");
		URLUtils ut = URLUtils.URLUTIL_INSTANCE;

		// TODO add a check that the target file is not the defaultIfc
		// file to avoid overwriting the default project file.
		String target = ifc.getIfcLocation() + File.separator
				+ ifc.getIfcFile();
		logger.debug("reading from: " + uri);
		logger.debug("writing to: " + target);

		try {
			ut.rFromUri(uri, target);
			// ut.readFromUri(source, target);
			// if there were no exceptions then transform the read in ifc file
			transformExtIfc(ifc);
		} catch (MalformedURLException e) {
			logger.debug("URI reading problem", e);
			resetProjectIfc(ifc);
		} catch (IOException e) {
			logger.debug("reading/writing exception", e);
			resetProjectIfc(ifc);
		} catch (TransformerException e) {
			logger.error("++ Transformer Exception thrown", e);
			resetProjectIfc(ifc);
		}

	}

	/**
	 * Resets the project to it's default values. Used in case of an error
	 * during setting a project file other than the default.
	 * 
	 * @param prjIfc
	 *            project settings
	 */
	private void resetProjectIfc(ProjectIfc prjIfc) {

		logger.debug("Resetting the project to its default state");
		prjIfc.setIfcFile("default");

	}

	/**
	 * @param prj
	 * @throws TransformerException
	 */
	private void transformExtIfc(ProjectIfc prj) throws TransformerException {

		IfcTransformer it = new IfcTransformer();
		String transformDir = webRootDir + File.separator + "data"
				+ File.separator + "transforms";
		String annoDir = webRootDir + File.separator + "data" + File.separator
				+ "annotations";

		String xslt = transformDir + File.separator + "attrIntoElem.xslt";
		File xsltFile = new File(xslt);
		it.setXsltFile(xsltFile);

		String uxf = prj.getIfcFile();
		String u = annoDir + File.separator + uxf;
		File untrauntransformedFile = new File(u);
		it.setUntransformedFile(untrauntransformedFile);

		// define the name for the transformed file - original with .t.xml
		// appended to it
		String txf = uxf + ".t.xml";
		String tx = annoDir + File.separator + txf;
		File transformedFile = new File(tx);
		it.setTransformedFile(transformedFile);

		it.transform();

		// set the project ifc with the transformed file
		prj.setIfcFile(transformedFile.getName());

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
