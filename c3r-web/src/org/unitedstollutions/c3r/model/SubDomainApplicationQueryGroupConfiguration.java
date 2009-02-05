/**
 * 
 */
package org.unitedstollutions.c3r.model;


/**
 * @author yurchyshyna
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
		String[][] contents = new String[][] {
				{ "none_selected", "" },
				{ "Cheminement.generalities", "Généralités" },
				{ "Cheminement.palierRepos", "Palier de repos" },
				{ "Cheminement.seuilsRessauts", "Seuils et ressauts" },
				{ "Cheminement.reperageCheminement", "Repérage du cheminement" },
				{ "Cheminement.espaceManoeuvre ", "Espace de manoeuvre " },
				{ "Cheminement.espaceUsage", "Espace d'usage" },
				{ "Cheminement.sol", "Sol" },
				{ "Cheminement.cheminementLibreObstacle", "Cheminement libre de tout obstacle" },
				{ "Cheminement.voleeEscalierPlusTroisMarches", "Volée d'escalier de 3 marches ou plus" },
				{ "Cheminement.voleeEscalierMoinsTroisMarches", "Volée d'escalier de moins de 3 marches" },
				{ "Cheminement.eclairage", "Eclairage" },
				};
		// TODO - define list for each DomaineApplication
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
		
	}

}
