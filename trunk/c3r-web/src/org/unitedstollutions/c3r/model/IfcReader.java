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

/**
 * @author user
 * 
 */

public class IfcReader {

	private String ifcFile;
	private String ifcLocation;
	private String assignedFileName;
	private String defaultIfcFileName = "defaultIfc.rdf";
	private String customIfcFileName = "customIfc.rdf";
	private static HashMap<String,String> fileNames;

	
	public IfcReader() {
	
		fileNames = new HashMap<String, String>();
		fileNames.put("default", "defaultIfc.rdf");
		fileNames.put("default2", "defaultIfc2.rdf");
		fileNames.put("default3", "defaultIfc3.rdf");
		fileNames.put("custom", "customIfc.xml");
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
		this.ifcFile = ifcFile;
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
	 * @return the projectFileName
	 */
	public String getAssignedFileName(String fieldValue) {
		//return assignedFileName;
		return fileNames.get(fieldValue);
	}

	/**
	 * @param projectFileName the projectFileName to set
	 */
	public void setAssignedFileName(String projectFileName) {
		this.assignedFileName = projectFileName;
	}

	/**
	 * Reads in a specified file from a client and writes the contents to a
	 * custom IFC file.
	 * 
	 * @param ifcFile
	 */
	public void createCustomIfc(String ifcFile) {
		this.ifcFile = this.customIfcFileName;

		File ifcFile2read = new File(ifcFile);
		String processedIfcFile = readFile(ifcFile2read);
		File customIfcFile = new File(getIfcLocation() + "/"
				+ customIfcFileName);
		writeFile(customIfcFile, processedIfcFile);
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
