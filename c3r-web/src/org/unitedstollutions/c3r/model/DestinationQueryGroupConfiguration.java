/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 * 
 */
public class DestinationQueryGroupConfiguration extends QueryGroupConfiguration {


	public DestinationQueryGroupConfiguration() {

		String [][] conts = new String[][] {
				{ "ERP ", "Etablissements Recevant du Public" },
				{ "BHCN", "Bâtiments d'habitation collectifs neufs" },
				{ "CHP", "Constructions pour handicapés physiques" },
				{ "Agricole", "Installations agricoles" }, { "GA", "GA" },
				{ "Habitation_collective", "Habitation_collective" },
				{ "Habitation_individuelle", "Habitation_individuelle" },
				{ "IGH", "IGH" }, { "Industriel", "Industriel" },
				{ "LT", "Lieux de travail" } };
		
		setContents(conts);
	}

}
