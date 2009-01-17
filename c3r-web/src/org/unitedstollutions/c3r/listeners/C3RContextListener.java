/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */

package org.unitedstollutions.c3r.listeners;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.unitedstollutions.c3r.model.C3REngine;
import org.unitedstollutions.c3r.model.ProjectIfc;

public final class C3RContextListener implements ServletContextListener {

	Logger logger = Logger.getLogger(C3RContextListener.class);

	private ServletContext context = null;

	public void contextInitialized(ServletContextEvent event) {

		context = event.getServletContext();

		// note: getRealPath returns the web app root directory WITH a slash at
		// the end
		String webRootDir = context.getRealPath("/");
		String projectDataLocation = webRootDir + File.separator
				+ context.getInitParameter("projectData");

		// initialize log4j
		// NOTE: logging context initialization is probably not needed. As long
		// as a log4j.properties
		// files is in the WEB-INF/classes directory it will be automatically
		// found by log4j.
		String file = context.getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if (file != null) {
			PropertyConfigurator.configure(webRootDir + file);
		}
		logger.debug("web root dir: " + webRootDir);
		logger.debug("project data location: " + projectDataLocation);

		// create a second query processing engine instance available
		// application wide
		// Note: this is a second engine using corese directly. The first
		// default engine is created with sewese.  The properties have to be set before an instance is created.
		// Once an instance is created the data can only be changed by using the load methods.
/*		
        C3REngine c3rEngine = new C3REngine();
		// log4j settings for engine
		String log4jConf = webRootDir + context.getInitParameter("ENGINE_LOG4J");
		if(log4jConf != null) {
			c3rEngine.setLog4jProperties(log4jConf);
		}
		// all engine settings but set with a property file.  This is the most flexible way.
		String coreseProperties = context.getInitParameter("PROPERTY_FILE");
		if (coreseProperties != null) {
			// TODO uncomment later if needed
			//c3rEngine.setPropertyFile(coreseProperties);
		}
		c3rEngine.createIEngineInstance();
		context.setAttribute("c3r_engine", c3rEngine);
*/

		// set project specific information
		// This object is used after an initial IEngine has been set up.
		// defined in the data descriptor (web.xml)
		String projectIfc = context.getInitParameter("projectIfc");
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
