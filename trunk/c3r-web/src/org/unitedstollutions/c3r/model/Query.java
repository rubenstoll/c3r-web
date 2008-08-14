/*
 * Copyright 2008 UnitedStollutions, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://solutions.unitedstollutions.net/berkeley_license.html
 */
package org.unitedstollutions.c3r.model;

import java.util.List;

/**
 * @author yurchyshyna
 * 
 */

/*
 * Used as a javaBean
 */
public class Query {

	private String value;
	private List properties;
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
	 * @return the properties
	 */
	public List getProperties() {
		return properties;
	}
	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List properties) {
		this.properties = properties;
	}

}
