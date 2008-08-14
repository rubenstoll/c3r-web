/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 * 
 */
public class ExtraiteTitreQueryGroupConfiguration extends
		QueryGroupConfiguration {

	public ExtraiteTitreQueryGroupConfiguration() {
		
		String [][] cont = new String[][]{ { "arrete", "arrete" },
				{ "circulaire", "circulaire" } };
		
		setContents(cont);
		
	}


}
