/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

package org.unitedstollutions.c3r.listeners;

import javax.servlet.*;
import javax.persistence.*;

import org.unitedstollutions.c3r.model.ProjectIfc;

public final class C3RContextListener implements ServletContextListener {
	
	// @PersistenceUnit(unitName = "dukebookstore")
	// use this definition when there are more than one
	// persistence units defined in the persistence.xml file
	// @PersistenceUnit(unitName = "dukebookstore")
	// private EntityManagerFactory emf;

	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

		/*
		 * try { BookDBAO bookDBAO = new BookDBAO(emf);
		 * context.setAttribute("bookDBAO", bookDBAO); } catch (Exception ex) {
		 * System.out.println( "Couldn't create bookstore database bean: " +
		 * ex.getMessage()); }
		 */
		
		// defined in the data descriptor (web.xml)
		String projectIfc = context.getInitParameter("projectIfc");
		String projectDataLocation = context.getInitParameter("projectData");
		
		ProjectIfc pi = ProjectIfc.getInstance();
		pi.setIfcFile(projectIfc);
		pi.setIfcLocation(projectDataLocation);
		
		context.setAttribute("project", pi);
		
	}

	public void contextDestroyed(ServletContextEvent event) {
		context = event.getServletContext();

		/*
		 * BookDBAO bookDBAO = (BookDBAO) context.getAttribute("bookDBAO");
		 * 
		 * if (bookDBAO != null) { bookDBAO.remove(); }
		 * 
		 * context.removeAttribute("bookDBAO");
		 */
	}
}
