/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 * 
 */
public class ThematicQueryGroupConfiguration extends QueryGroupConfiguration {

	public ThematicQueryGroupConfiguration() {
		String[][] contents = new String[][] {
				{ "accessibilite_des_handicapes",
						"accessibilite_des_handicapes" },
				{ "acoustique", "acoustique" }, { "aeration", "aeration" },
				{ "energies_durables", "Durable Engergies" },
				{ "plomb_saturnisme", "plomb_saturnisme" },
				{ "securite_incendie", "securite_incendie" } };
		
		setContents(contents);
	}

}
