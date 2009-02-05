/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author yurchyshyna
 * 
 */
public class DomainApplicationQueryGroupConfiguration extends
		QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set
	 * the default value and label for the query group
	 * group configuration. 
	 * The value and label represent the value and label in 
	 * a drop down list
	 */
	public DomainApplicationQueryGroupConfiguration() {
		// TODO - finish list once doc is finished (C3Rdescription.doc)
		String [][] contents = new String[][] {
				{ "none_selected", "" },
				{ "Cheminement", "Cheminements extérieures" },
				{ "CirculationInterieureVerticale",
						"Circulations intérieures verticales" },
				{ "TapisRoulants",
						"Tapis, escaliers et plans inclinés mécaniques" },
				{ "Porte", "Portes, portiques et sas" },
				{ "SalleHygiene", "Sanitaires" }, { "Sortie", "Sortie" },
				{ "Eclairage", "Eclairage" } };
		
		setContents(contents);
		
		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
		
	}

}
