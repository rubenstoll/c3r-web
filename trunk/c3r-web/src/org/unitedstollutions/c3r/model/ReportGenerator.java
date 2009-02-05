/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.io.File;

/**
 * @author Anastasiya
 *
 */
public class ReportGenerator {
	
	// properties
	private String locationReportGeneratorXslt = "data/reportGenerator.xslt";
		File xsltFile = new File(locationReportGeneratorXslt);
	private File resultsFromCorese;
	private File generatedFile;
	

}
