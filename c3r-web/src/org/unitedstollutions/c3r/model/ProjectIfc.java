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
	 * @return a single C3REngine instance
	 */
	public static ProjectIfc getInstance() {
		if (projectIfcSingleton == null) {
			synchronized (ProjectIfc.class) {
				projectIfcSingleton = new ProjectIfc();
			}
		}
		return projectIfcSingleton;
	}
	
	public ProjectIfc() {
		// Exists only to defeat instantiation.	
	}
	
	public ProjectIfc(String ifcFile) {
		// this constructor is only used when the context is initialized
		// by the context listener
		this.ifcFile = ifcFile;
	}
	
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
		} else {
			this.ifcFile = ProjectIfc.fileNames.get("default");						
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
	public void createCustomIfc(String ifcFile) {
		
		this.ifcFile = ProjectIfc.fileNames.get("custom");

		File ifcFile2read = new File(ifcFile);
		String processedIfcFile = readFile(ifcFile2read);
		File customIfcFile = new File(getIfcLocation() + "/"
				+ ProjectIfc.fileNames.get("custom"));
		writeFile(customIfcFile, processedIfcFile);
	}

	/**
	 * @param source
	 * @return
	 */
	public void readFromUri(String source) throws IOException {
		
		// TODO add a check that the target file is not the defaultIfc
		//  file to avoid overwriting the default project file.
		String target = getIfcLocation() + getIfcFile();
		
		readFromUri(source, target);
		
	}
	
	
	/**
	 * @param source
	 * @return
	 */
	public void readFromUri(String source, String destination) throws IOException {

		BufferedReader in = null;
		BufferedWriter fw = null;
		String inputLine = null;
		File writeFile = null;
		// String wFileName = destination + File.separator +
		// this.customIfcFileName;
		String wFileName = destination;

		try {

			writeFile = new File(wFileName);
			fw = new BufferedWriter(new FileWriter(writeFile
					.toString()));
			URL url = new URL(source);
			in = new BufferedReader(new InputStreamReader(url.openStream()));

			if (in == null) {
				
			} else {
				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
					fw.write(inputLine + "\n");
				}
			}
			
		} catch (MalformedURLException mue) {
			System.out.println("URI is not correct");
		} catch (IOException ioe) {
			System.out.println("IO Exception - Connection timed out or couldn't create file writer");
			throw new IOException();
		} finally {
			if(in != null) {
				System.out.println("url buffered reader object exists and is being closed");
				in.close();
			}
			if(fw != null) {
				System.out.println("output buffered writer object exists and is being closed");
				fw.close();
			}
		}
		
	}

	
	/**
	 * Reads the contents of a specified file and returns the contents in a long
	 * string.
	 * 
	 * NOTE: This is probably not the good way to do this. I creates a big
	 * string object. Might as well read and write to the end file directly or
	 * just return a StringBuffer instead of a big String
	 * 
	 * @param file
	 * @return String returns entire read in file in one string
	 */
	private String readFile(File file) {

		StringBuffer fileBuffer;
		String fileString = null;
		String line;

		try {
			FileReader in = new FileReader(file);
			BufferedReader dis = new BufferedReader(in);
			fileBuffer = new StringBuffer();

			while ((line = dis.readLine()) != null) {
				fileBuffer.append(line + "\n");
			}

			in.close();
			fileString = fileBuffer.toString();
		} catch (IOException e) {
			return null;
		}
		return fileString;
	}

	/**
	 * Writes a file with the contents of a passed in string.
	 * 
	 * @param File
	 *            file name to write to
	 * @param String
	 *            long string of read in file contents
	 */
	private void writeFile(File file, String dataString) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file)));
			out.print(dataString);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}

}
