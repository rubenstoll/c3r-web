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
	private String engineSchema;
	private String engineData;
	private String engineRule;

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

		engineData = dataDirectory + File.separator + "annotations"
				+ File.separator + "defaultIfc.rdf";
		engineRule = dataDirectory + File.separator + "ruleTest";
		engineSchema = dataDirectory + File.separator + "schemas"
				+ File.separator + "ontoCC.owl";

		log.info("data directory is: " + dataDirectory);
		log.info("schema being used is: " + engineSchema);

		engine = C3REngine.getInstance();

		log.info("++++ Starting test");

	}

	@Ignore("Not Ready to Run")
	@Test
	public void multiplication() {
		String queryResult = "";
		assertEquals(queryResult, "");
	}

	@Ignore("Too simple of a test. Ucomment for just checking simple queries")
	@Test
	public void setSimpleQueryAndRun() {

		engine.loadDirectory(engineRule);
		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		try {
			engine.runRuleEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String query = prefixOnto
				+ "select ?x display xml where { ?x rdf:type rdfs:Class } ";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);

		printResults(results);

	}
	
	@Ignore
	@Test
	public void doAllTest() {
		
		engine.doAllInOne(engineSchema, engineData, engineRule);
		
	}
	
	
	@Test
	public void ifcProjectQueryTest() {

		log.info("+ running porte principale query test");
		
		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		engine.loadDirectory(engineRule);
		engine.runRuleEngine();
		
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
//		String query = prefixOnto
//				+ "select ?x display xml where { ?x  rdf:type   ontoCC:PortePrincipale }";
		String query = prefixOnto
				+ "select ?x display xml where { ?x  rdf:type   ontoCC:Sas  }";

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
