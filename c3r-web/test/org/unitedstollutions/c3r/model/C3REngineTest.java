/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

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

		System.out.println("data directory is: " + dataDirectory);
		System.out.println("schema being used is: " + engineSchema);

		engine = C3REngine.getInstance();
		engine.createEngineFactory();
//		engine.setDataPath(dataDirectory);
//		engine.setEngineData(engineData);
//		engine.setEngineRule(engineRule);
//		engine.setEngineSchema(engineSchema);
		
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
		String query = prefixOnto + "select ?x display xml where { ?x rdf:type rdfs:Class } ";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);
		
		printResults(results);
		
	}

	
	@Test
	public void ifcProjectQueryTest() {
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		//String query = prefixOnto + "select ?x display xml where { ?x  rdf:type   ontoCC:IfcDoor }";
		String query = prefixOnto + "select ?x display xml where { ?x  rdf:type   ontoCC:PortePrincipale  }";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);
		
		printResults(results);
		
	}
	
	@Ignore("Not finished yet")
	@Test
	public void mappedQueriesTest() {
		
		HashMap<String, Query> mappedQueries = new HashMap<String, Query>();

		// TODO create hash map reference numbers (r0008) and Query objects as test data
		
		HashMap<String, ArrayList<String>> results = engine.runMappedQueries(mappedQueries);

		assertNotNull(results);
		
		printResults(results);
		
	}
	
	private void printResults(ArrayList<String> results) {
		
		System.out.println("+++ QUERY RESULTS:\n\n");
		for(String result : results) {
			System.out.println(result);
		}
		
	}


	private void printResults(HashMap<String,ArrayList<String>> results) {
		
		System.out.println("+++ MAPPED QUERY RESULTS:\n\n");
		// TODO implement results collection iterator to iterate through hash map
		
	}

	
	@After
	public void runAfterEveryTest() {
		engine = null;
		System.out.println("\n\n++++ leaving test ...");
	}

	@AfterClass
	public static void runAfterClass() {
		// run for one time after all test cases
	}
}
