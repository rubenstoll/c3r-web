/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Enumeration;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import fr.inria.acacia.corese.api.IResult;
import fr.inria.acacia.corese.api.IResultValue;
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

		String engineData = dataDirectory + File.separator + "annotations"
				+ File.separator + "defaultIfc.rdf";
		String engineRule = dataDirectory + File.separator + "definition_rules";
		String engineSchema = dataDirectory + File.separator + "schemas"
				+ File.separator + "ontoCC.owl";

		engine = new C3REngine();
		engine.setDataPath(dataDirectory);
		engine.setEngineData(engineData);
		engine.setEngineRule(engineRule);
		engine.setEngineSchema(engineSchema);

		System.out.println("data directory is: " + dataDirectory);
		System.out.println("schema being used is: " + engine.getEngineSchema());

	}

	@Ignore("Not Ready to Run")
	@Test
	public void multiplication() {
		String queryResult = "";
		assertEquals(queryResult, engine.getQuery());
	}

	@Test
	public void setSimpleQueryAndRun() {
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String query = prefixOnto
				+ "select ?x display xml where { ?x rdf:type rdfs:Class } ";

		IResults res = setQueryAndRunEngine(query);
		showResults(res);
		// assertTrue("yes" == "yes");
	}

	
	@Test
	public void ifcProjectQueryTest() {
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String query = prefixOnto + "select ?x display xml where { ?x  rdf:type   ontoCC:IfcProject }";

		IResults res = setQueryAndRunEngine(query);
		showResults(res);
		
		// assertTrue("yes" == "yes");
	}

	private IResults setQueryAndRunEngine(String query) {
		
		engine.setEngineRun(true);
		engine.setQuery(query);
		
		return engine.runQuery();

	}
	
	private void showResults(IResults results) {
		
		String[] variables = results.getVariables();
		// go through all results
		for (Enumeration<IResult> en = results.getResults(); en.hasMoreElements();) {
			// get a result
			IResult r = en.nextElement();
			// go through this result
			for (String var : variables) {
				if (r.isBound(var)) {
					// get result values for each selected variable
					IResultValue[] values = r.getResultValues(var);
					for (int j = 0; j < values.length; j++)
						System.out.println(var + " = "
								+ values[j].getStringValue());
				} else {
					System.out.println(var + " = Not bound");
				}
			}
		}
	}
	
	
	@After
	public void runAfterEveryTest() {
		engine = null;
		System.out.println("leaving test ...");
	}

	@AfterClass
	public static void runAfterClass() {
		// run for one time after all test cases
	}
}
