/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author yurchyshyna
 * this class contains the information concerning the Query 
 * extracted from the annotation
 */
public class QueryInfo {
	private String contenuRegle;
	private String textRegle;
	
	public String getText(){
		return textRegle;
	}
	
	public String getContenu(){
		return contenuRegle;
	}
	
	public QueryInfo(String icontenuRegle, String itextRegle) {
		contenuRegle = icontenuRegle;
		textRegle = itextRegle;
	}
}
