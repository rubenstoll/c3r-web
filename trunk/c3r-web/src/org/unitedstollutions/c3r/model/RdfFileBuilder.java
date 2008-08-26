/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author ruben
 *
 */
public class RdfFileBuilder {
	
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
	
	
}
