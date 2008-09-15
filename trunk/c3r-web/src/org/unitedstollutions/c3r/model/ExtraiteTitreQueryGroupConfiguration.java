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
				{ "Arrete_24_dec_1980_modifie", "Arrete_24_12_1980_modifie" },
				{ "Circulaire_82-81_04_oct_1982", "Circulaire_82-81_04_oct_1982" }};

		setContents(cont);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
	}

}
