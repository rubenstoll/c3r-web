/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 * 
 */
public class ExtraiteTypeLEGQueryGroupConfiguration extends
		QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set
	 * the default value and label for the query
	 * group configuration. 
	 * The value and label represent the value and label in 
	 * a drop down list
	 */
	public ExtraiteTypeLEGQueryGroupConfiguration() {
		String [][] contents = new String[][] { 
				{ "none_selected", "" },
				{ "decret", "Décret" },
				{ "arrete", "Arrêté" }, 
				{ "circulaire", "Circulaire" },
				{ "Norme", "Norme" },
				{ "arrete_municipal", "Arrêté municipal" },
				{ "arrete_prefectoral", "Arrêté préfectoral" },
				{ "code", "Code" }, 
				{ "lois", "Lois" },
				{ "question_ecrite", "Question écrite" },
				{ "texte_informatif", "Texte informaitf" } };
		
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);

	}

}
