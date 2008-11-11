/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.inria.acacia.corese.api.IResults;

/**
 * @author ruben.stoll
 * 
 */
public class C3REngineTest {

	private C3REngine engine;
	private String dataDirectory;

	@BeforeClass
	public static void runBeforeClass() {
	}

	@Before
	public void runBeforeEveryTest() {

		// run for one time before all test cases
		File currDir = new File(System.getProperty("user.dir"));
		System.out.println("current directory is:" + currDir);

		dataDirectory = currDir.toString() + File.separator + "war"
				+ File.separator + "data";

		String engineData = dataDirectory + File.separator + "annotations";
		String engineRule = dataDirectory + File.separator + "definition_rules";
		System.out.println("data directory is: " + dataDirectory);

		engine = new C3REngine();
		engine.setDataPath(dataDirectory);
		engine.setEngineData(engineData);
		engine.setEngineRule(engineRule);

	}

	@Ignore("Not Ready to Run")
	@Test
	public void multiplication() {
		String queryResult = "";
		assertEquals(queryResult, engine.getQuery());
	}

	@Test
	public void setSimpleQueryAndRun() {
		String query = "select ?palierrepos display xml where { ?palierrepos rdf:type ontoCC:PalierRepos OPTIONAL { ?palierrepos ontoCC:overallWidth ?longueur FILTER ( xsd:integer(?longueur) > 140) } FILTER (! bound( ?longueur ) ) } ";
		engine.setQuery(query);
		IResults results = engine.runQuery();
		assertTrue("yes" == "yes");

	}

	@After
	public void runAfterEveryTest() {
		engine = null;
	}

	@AfterClass
	public static void runAfterClass() {
		// run for one time after all test cases
	}
}
