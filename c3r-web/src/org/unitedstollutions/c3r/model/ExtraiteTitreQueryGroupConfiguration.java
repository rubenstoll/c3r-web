/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

public class ExtraiteTitreQueryGroupConfiguration extends
		QueryGroupConfiguration {

	/*
	 * Set the available types of configurations and set the default value and
	 * label for the query group configuration. The value and label represent
	 * the value and label in a drop down list
	 */
	public ExtraiteTitreQueryGroupConfiguration() {

		String[][] cont = new String[][] { 
				{"none_selected", "" },
				{"Arrete_24_dec_1980_modifie", "Arrete_24_12_1980_modifie" },
				{"Circulaire_82-81_04_oct_1982", "Circulaire_82-81_04_oct_1982" },
				{"Arrete_31_mai_1994", "Arrete_31_mai_1994"},
				{"Arrete_27_jui_1994", "Arrete_27_jui_1994"},
				{"Circulaire_94-55_07_jul_1994", "Circulaire_94-55_07_jul_1994"},
				{"NF_P_91-201_jul_1978", "NF_P_91-201_jul_1978"},
				{"Decret_2006-555_17_mai_2006", "Decret_2006-555_17_mai_2006"},
				{"Arrete_01_aou_2006_consolide", "Arrete_01_aou_2006_consolide"},
				{"Arrete_21_mar_2007", "Arrete_21_mar_2007"}};

		setContents(cont);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
	}

}
