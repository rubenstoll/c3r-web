/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;
import java.util.Set;

/**
 * @author ruben.stoll
 *
 */
public abstract class QueryGroupConfiguration {

	private String value;
	private String label;
	private String[][] contents;
	
	/**
	 * Initializes the default values for the 
	 * query group
	 * 
	 * @param defaultValue
	 */
	public void setDefaults(String defaultValue) {
		
		setValue(defaultValue);
		HashMap<String, String> cnts = getContents();
		setLabel(cnts.get(defaultValue));

	}
	
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
	public HashMap<String,String> getContents() {
		
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

	/**
	 * @return the defaultTop
	 */
	public HashMap<String, String> getSelectList() {
		
		HashMap<String, String> contMap = getContents();
		
	    // rearrange the map to put the default value first
	    String defaultValue = getValue();
	    String defaultLabel = getLabel();
	    contMap.remove(defaultValue);
	    Set<String> keys = contMap.keySet();
	    HashMap<String, String> tmp = new HashMap<String,String>();
	    for(String key : keys) {
	    	tmp.put(key, contMap.get(key));
	    }
	    tmp.put(defaultValue, defaultLabel);
//	    return defaultTop;
		return tmp;
	}

}
