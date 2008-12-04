/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author ruben.stoll
 * 
 */
public class C3REngineTest {

	private static final Log log = LogFactory.getLog(C3REngineTest.class);
	private C3REngine engine;
	private String dataDirectory;

	@BeforeClass
	public static void runBeforeClass() {
	}

	@Before
	public void runBeforeEveryTest() {

		// run for one time before all test cases
		File currDir = new File(System.getProperty("user.dir"));
		log.info("current directory is:" + currDir);

		dataDirectory = currDir.toString() + File.separator + "war"
				+ File.separator + "data";

		String engineData = dataDirectory + File.separator + "annotations"
				+ File.separator + "defaultIfc.rdf";
		String engineRule = dataDirectory + File.separator + "definition_rules";
		String engineSchema = dataDirectory + File.separator + "schemas"
				+ File.separator + "ontoCC.owl";

		log.info("data directory is: " + dataDirectory);
		log.info("schema being used is: " + engineSchema);

		engine = C3REngine.getInstance();
		engine.createEngineFactory();
		// engine.setDataPath(dataDirectory);
		// engine.setEngineData(engineData);
		// engine.setEngineRule(engineRule);
		// engine.setEngineSchema(engineSchema);

		engine.createIEngine();

		engine.loadDirectory(engineRule);
		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		engine.runRuleEngine();

	}

	@Ignore("Not Ready to Run")
	@Test
	public void multiplication() {
		String queryResult = "";
		assertEquals(queryResult, engine.getEngineSchema());
	}

	@Test
	public void setSimpleQueryAndRun() {
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String query = prefixOnto
				+ "select ?x display xml where { ?x rdf:type rdfs:Class } ";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);

		printResults(results);

	}

	@Test
	public void ifcProjectQueryTest() {
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		// String query = prefixOnto +
		// "select ?x display xml where { ?x  rdf:type   ontoCC:IfcDoor }";
		String query = prefixOnto
				+ "select ?x display xml where { ?x  rdf:type   ontoCC:PortePrincipale  }";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);

		printResults(results);

	}

	@Ignore("Not finished yet")
	@Test
	public void mappedQueriesTest() {

		HashMap<String, Query> mappedQueries = new HashMap<String, Query>();

		// TODO create hash map reference numbers (r0008) and Query objects as
		// test data

		HashMap<String, ArrayList<String>> results = engine
				.runMappedQueries(mappedQueries);

		assertNotNull(results);

		printResults(results);

	}

	private void printResults(ArrayList<String> results) {

		log.info("+++ QUERY RESULTS:\n\n");
		for (String result : results) {
			log.info(result);
		}

	}

	private void printResults(HashMap<String, ArrayList<String>> results) {

		log.info("+++ MAPPED QUERY RESULTS:");
		// TODO implement results collection iterator to iterate through hash
		// map

	}

	@After
	public void runAfterEveryTest() {
		engine = null;
		log.info("++++ leaving test ...");
	}

	@AfterClass
	public static void runAfterClass() {
		// run for one time after all test cases
	}
}
