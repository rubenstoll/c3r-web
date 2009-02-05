/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author yurchyshyna
 * 
 */
public class DestinationQueryGroupConfiguration extends QueryGroupConfiguration {


	/*
	 * Set the available types of configurations and set
	 * the default value and label for the query
	 * group configuration. 
	 * The value and label represent the value and label in 
	 * a drop down list
	 */
	public DestinationQueryGroupConfiguration() {

		String [][] conts = new String[][] {
				{ "none_selected", "" },
				{ "ERP", "Etablissements Recevant du Public" },
				{ "BHCN", "Bâtiments d'habitation collectifs neufs" },
				{ "CHP", "Constructions pour handicapés physiques" },
				{ "Agricole", "Installations agricoles" }, { "GA", "GA" },
				{ "Habitation_collective", "Habitation_collective" },
				{ "Habitation_individuelle", "Habitation_individuelle" },
				{ "IGH", "IGH" }, { "Industriel", "Industriel" },
				{ "LT", "Lieux de travail" } };
		
		setContents(conts);
		
		// sets the default here from available list above
		String defaultValue = "ERP";
		setDefaults(defaultValue);
		
	}

}
