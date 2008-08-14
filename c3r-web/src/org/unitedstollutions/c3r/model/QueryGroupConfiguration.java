/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author ruben.stoll
 *
 */
public abstract class QueryGroupConfiguration {

	private String value;
	private String label;
	private String[][] contents;
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the contents
	 */
	public HashMap getContents() {
	    HashMap<String, String> contMap = new HashMap<String,String>();
	    for (int i = 0; i < this.contents.length; i++) {
	      contMap.put(this.contents[i][0],this.contents[i][1]);
	    }
	    return contMap;
	}
	
	/**
	 * @param contents the contents to set
	 */
	public void setContents(String[][] contents) {
		this.contents = contents;
	}
}
