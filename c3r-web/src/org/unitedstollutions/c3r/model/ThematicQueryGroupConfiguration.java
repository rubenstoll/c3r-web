/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author yurchyshyna
 * 
 */
public class ThematicQueryGroupConfiguration extends QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set
	 * the default value and label for the query
	 * group configuration. 
	 * The value and label represent the value and label in 
	 * a drop down list
	 */
	public ThematicQueryGroupConfiguration() {
		
		String[][] contents = new String[][] {
				{ "none_selected", "" },
				{ "plomb_saturnisme", "Plomb / Saturnisme" },
				{ "accessibilite_des_handicapes","Accessability" },
				{ "acoustique", "Acoustics" }, 
				{ "aeration", "Aeration" },
				{ "energies_durables", "Sustainable Energy" },
				{ "securite_incendie", "Fire Security" } };
		
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "accessibilite_des_handicapes";
		setDefaults(defaultValue);
		
	}

}
