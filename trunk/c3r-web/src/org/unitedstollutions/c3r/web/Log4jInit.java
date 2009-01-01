package org.unitedstollutions.c3r.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * Servlet implementation class Log4jInit
 */
public class Log4jInit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Log4jInit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		// TODO see why the commnted out functions do not work
//		String prefix =  getServletContext().getRealPath("/");
		String prefix =  "/c3r-web";
		System.out.println("LOG4JSERVLET: prefix is " + prefix);
		
//		String file = getInitParameter("log4j-init-file");
		String file = null;
		// if the log4j-init-file is not set, then no point in trying
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

}
