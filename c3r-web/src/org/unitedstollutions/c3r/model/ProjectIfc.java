package org.unitedstollutions.c3r.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.unitedstollutions.c3r.utils.FileRWUtils;
import org.unitedstollutions.c3r.utils.URLUtils;

import fr.inria.acacia.corese.api.EngineFactory;

/**
 * @author user
 * 
 */

public class ProjectIfc {

	Logger logger = Logger.getLogger(ProjectIfc.class);


	// first alternative to create a singleton
	private static ProjectIfc projectIfcSingleton;

	// second alternative to create a simple, fast and thread safe singleton
	// public final static C3REngine c3rEngSingleton = new C3REngine();
	
	private String ifcFile;
	private String ifcLocation;
	private static HashMap<String,String> fileNames = initProjectIfcFileNames();

	public static  HashMap<String,String> initProjectIfcFileNames() {

		fileNames = new HashMap<String, String>();
		fileNames.put("default", "defaultIfc.rdf");
		fileNames.put("default2", "defaultIfc2.rdf");
		fileNames.put("default3", "defaultIfc3.rdf");
		fileNames.put("custom", "customIfc.xml");
		
		return fileNames;
		
	}
	

	/**
	 * @return a single ProjectIfc instance
	 */
	public static ProjectIfc getInstance() {
		if (projectIfcSingleton == null) {
			synchronized (ProjectIfc.class) {
				projectIfcSingleton = new ProjectIfc();
			}
		}
		return projectIfcSingleton;
	}
	
	
	// TODO find a workaround to instantiate a singleton class as a jsp bean 
	//  protected doesn't let the main jsp
	//  create an instance of this class.  Find a work around. It should be possible
	// to create an instance of a singleton and still be a bean
//	protected ProjectIfc() {
//		// Exists only to defeat instantiation.	Note that it is protected.
//		// Access only to methods in this class and to classes in the same package.
//	}
	
	/**
	 * @return the ifcFile
	 */
	public String getIfcFile() {
		return ifcFile;
	}

	/**
	 * @param ifcFile
	 *            the ifcFile to set
	 */
	public void setIfcFile(String ifcFile) {
		
		// this is not the best implementation. The disadvantage of this method is that
		// the user or programmer must know the name of the map key in order to get 
		// the appropriate file name.  This method gets called from the c3r form controller
		
		// TODO change this to a better implementation
		if(ifcFile.equalsIgnoreCase("custom")) {
			this.ifcFile = ProjectIfc.fileNames.get("custom");
			// TODO add the xslt transformation here
		} else if(ifcFile.equalsIgnoreCase("default2")) {
			this.ifcFile = ProjectIfc.fileNames.get("default2");
		} else if(ifcFile.equalsIgnoreCase("default3")) {
			this.ifcFile = ProjectIfc.fileNames.get("default3");
		} else if(ifcFile.equalsIgnoreCase("default")) {
			this.ifcFile = ProjectIfc.fileNames.get("default");
		} else {
//			this.ifcFile = ProjectIfc.fileNames.get("default");
			this.ifcFile = ifcFile;
		}
	}

	/**
	 * @return the ifcLocation
	 */
	public String getIfcLocation() {
		return ifcLocation;
	}

	/**
	 * @param ifcLocation
	 *            the ifcLocation to set
	 */
	public void setIfcLocation(String ifcLocation) {
		this.ifcLocation = ifcLocation;
	}

	/**
	 * Reads in a specified file from a client and writes the contents to a
	 * custom IFC file.
	 * 
	 * @param ifcFile
	 */
	// TODO remove the reading from this class
	public void createCustomIfc(String ifcFile) {
		
		this.ifcFile = ProjectIfc.fileNames.get("custom");
		FileRWUtils frw = new FileRWUtils();

		File ifcFile2read = new File(ifcFile);
		String processedIfcFile = frw.readFile(ifcFile2read);
		File customIfcFile = new File(getIfcLocation() + "/"
				+ ProjectIfc.fileNames.get("custom"));
		frw.writeFile(customIfcFile, processedIfcFile);
	}


}
