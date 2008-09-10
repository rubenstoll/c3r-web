/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author ruben.stoll
 * 
 */
public class ExtraiteTitreQueryGroupConfiguration extends
		QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set the default value and
	 * label for the query group configuration. The value and label represent
	 * the value and label in a drop down list
	 */
	public ExtraiteTitreQueryGroupConfiguration() {

		String[][] cont = new String[][] { 
				{ "none_selected", "" },
				{ "arrete", "arrete" },
				{ "circulaire", "circulaire" } };

		setContents(cont);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
	}

}
