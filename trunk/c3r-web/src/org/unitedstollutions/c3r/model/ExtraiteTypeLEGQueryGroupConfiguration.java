/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author yurchyshyna
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
				{ "decret", "Decree" },
				{ "arrete", "Arrêté" }, 
				{ "circulaire", "Circular" },
				{ "Norme", "Norm" },
				{ "arrete_municipal", "Municipal Arrêté" },
				{ "arrete_prefectoral", "Prefectoral Arrêté" },
				{ "code", "Code" }, 
				{ "lois", "Legal text" },
				{ "question_ecrite", "Written Question" },
				{ "texte_informatif", "Informative Text" } };
		
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);

	}

}
