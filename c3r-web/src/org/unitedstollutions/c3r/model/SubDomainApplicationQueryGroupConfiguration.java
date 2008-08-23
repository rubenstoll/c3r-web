/**
 * 
 */
package org.unitedstollutions.c3r.model;


/**
 * @author ruben.stoll
 * 
 */
public class SubDomainApplicationQueryGroupConfiguration extends
		QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set
	 * the default value and label for the query
	 * group configuration. 
	 * The value and label represent the value and label in 
	 * a drop down list
	 */
	public SubDomainApplicationQueryGroupConfiguration() {
		/*
		 * The first array element is the composition of the parent domain
		 * application group and the tag name of the sub domain application
		 * group.
		 */
		// TODO - finish list
		String[][] contents = new String[][] {
				{ "Cheminement.generalities", "Généralités" },
				{ "Cheminement.Pente", "Pente" } };
		
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "Cheminement.Pente";
		setDefaults(defaultValue);
		
	}

}
