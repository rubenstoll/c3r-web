/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ruben
 *
 */
public class SemQryFileBuilder {
	
	private HashMap<String, String> queryGroups;
	private ArrayList<String> rdfData;
	
	/**
	 * @return the queryGroups
	 */
	public HashMap<String, String> getQueryGroups() {
		return queryGroups;
	}
	/**
	 * @param queryGroups the queryGroups to set
	 */
	public void setQueryGroups(HashMap<String, String> queryGroups) {
		this.queryGroups = queryGroups;
	}
	/**
	 * @return the rdfData
	 */
	public ArrayList<String> getRdfData() {
		return rdfData;
	}
	/**
	 * @param rdfData the rdfData to set
	 */
	public void setRdfData(ArrayList<String> rdfData) {
		this.rdfData = rdfData;
	}
	
	/**
	 * Get rdf files in a directory
	 * 
	 * @param path
	 * @return a list of rdf files all annotations are supposed to be stored at
	 *         the same directory
	 */
	private ArrayList<String> getRdfFiles(File path) {

		ArrayList<String> rdfFiles = new ArrayList<String>();

		if (path.exists()) {
			String[] directoryListing = path.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					rdfFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("\n\nNumber of RDF found: " + rdfFiles.size());
		System.out.println("\n\n");
		return rdfFiles;
	}
	
}
