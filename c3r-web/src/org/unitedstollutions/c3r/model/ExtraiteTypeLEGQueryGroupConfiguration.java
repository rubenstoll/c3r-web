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
		String [][] contents = new String[][] { { "decret", "Décret" },
				{ "arrete", "Arrêté" }, { "circulaire", "Circulaire" },
				{ "Norme", "Norme" },
				{ "arrete_municipal", "Arrêté municipal" },
				{ "arrete_prefectoral", "Arrêté préfectoral" },
				{ "code", "Code" }, { "lois", "Lois" },
				{ "question_ecrite", "Question écrite" },
				{ "texte_informatif", "Texte informaitf" } };
		
		setContents(contents);
	}

}
