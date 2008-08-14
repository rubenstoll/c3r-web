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

	public ExtraiteTypeLEGQueryGroupConfiguration() {
		String [][] contents = new String[][] { { "decret", "D�cret" },
				{ "arrete", "Arr�t�" }, { "circulaire", "Circulaire" },
				{ "Norme", "Norme" },
				{ "arrete_municipal", "Arr�t� municipal" },
				{ "arrete_prefectoral", "Arr�t� pr�fectoral" },
				{ "code", "Code" }, { "lois", "Lois" },
				{ "question_ecrite", "Question �crite" },
				{ "texte_informatif", "Texte informaitf" } };
		
		setContents(contents);
	}

}
