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
	}

}
