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
				{"Arrete_24_dec_1980_modifie", "Arr�t� du 24 d�cembre 1980 modifi�" },
				{"Circulaire_82-81_04_oct_1982", "Circulaire n� 82-81 du 4 octobre 1982" },
				{"Arrete_31_mai_1994", "Arr�t� du 31 mai 1994"},
				{"Arrete_27_jui_1994", "Arr�t� du 27 juin 1994"},
				{"Circulaire_94-55_07_jul_1994", "Circulaire n�94-55 du 7 juillet 1994"},
				{"NF_P_91-201_jul_1978", "NF P 91-201 (juillet 1978)"},
				{"Decret_2006-555_17_mai_2006", "D�cret n� 2006-555 du 17 mai 2006"},
				{"Arrete_01_aou_2006_consolide", "Arr�t� du 1 ao�t 2006 consolid�"},
				{"Arrete_21_mar_2007", "Arr�t� du 21 mars 2007"}};

		setContents(cont);

		// sets the default here from available list above
		String defaultValue = "none_selected";
		setDefaults(defaultValue);
	}

}
