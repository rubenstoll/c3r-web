/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 * 
 */
public class DomainApplicationQueryGroupConfiguration extends
		QueryGroupConfiguration {

	public DomainApplicationQueryGroupConfiguration() {
		// TODO - finish list once doc is finished (C3Rdescription.doc)
		String [][] contents = new String[][] {
				{ "Cheminement", "Cheminements ext�rieures" },
				{ "CirculationInterieureVerticale",
						"Circulations int�rieures verticales" },
				{ "TapisRoulants",
						"Tapis, escaliers et plans inclin�s m�caniques" },
				{ "Porte", "Portes, portiques et sas" },
				{ "SalleHygiene", "Sanitaires" }, { "Sortie", "Sortie" },
				{ "Eclairage", "Eclairage" } };
		
		setContents(contents);
	}

}
