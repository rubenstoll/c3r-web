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
				{ "decret", "D�cret" },
				{ "arrete", "Arr�t�" }, 
				{ "circulaire", "Circulaire" },
				{ "Norme", "Norme" },
				{ "arrete_municipal", "Arr�t� municipal" },
				{ "arrete_prefectoral", "Arr�t� pr�fectoral" },
				{ "code", "Code" }, 
				{ "lois", "Lois" },
				{ "question_ecrite", "Question �crite" },
				{ "texte_informatif", "Texte informaitf" } };
		
		setContents(contents);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);

	}

}
