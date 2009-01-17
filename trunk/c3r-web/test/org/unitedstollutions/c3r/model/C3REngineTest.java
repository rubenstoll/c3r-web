/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

		if (log.isDebugEnabled()) {
			log.info("logging debug mode is on");
		} else {
			log.info("logging debug mode is OFF");
		}
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

		engine = new C3REngine();

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


	@Test
	public void ifcProjectQueryTest() {

		log.info("+ running porte principale query test");

		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		engine.loadDirectory(engineRule);
		engine.runRuleEngine();

		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		// String query = prefixOnto
		// +
		// "select ?x display xml where { ?x  rdf:type   ontoCC:PortePrincipale }";
		String query = prefixOnto
				+ "select ?x display xml where { ?x  rdf:type   ontoCC:Sas  }";

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);

		printResults(results);

	}

	@Test
	public void runSingleMappedQueriesQueryTest() {

		log.info("+ running one query of multiple mapped queries");

		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		engine.loadDirectory(engineRule);
		engine.runRuleEngine();

		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String sparql = " select ?loc display xml where { ?loc rdf:type ontoCC:LocauxVideOrdures  } ";
		String query = prefixOnto + sparql;

		ArrayList<String> results = engine.runQuery(query);

		assertNotNull(results);
		assertEquals(results.isEmpty(), false);

		printResults(results);

	}

	@Test
	public void mappedQueriesTest() {

		log.info("+ running mapped query test");

		engine.loadFile(engineData);
		engine.loadFile(engineSchema);
		engine.loadDirectory(engineRule);
		engine.runRuleEngine();

		HashMap<String, Query> mappedQueries = new HashMap<String, Query>();

		mappedQueries = new HashMap<String, Query>();
		String queryCode = "r00081007";
		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		//String sparql = " select ?bati ?hallentree ?loc display xml where { ?loc rdf:type ontoCC:LocauxVideOrdures OPTIONAL { ?hallentree rdf:type ?t FILTER ( ?t = ontoCC:LocauxVideOrduresAccessible) } FILTER ( ! bound( ?t ) ) } ";
		String sparql = "select distinct ?porte ?rfe ?ro ?locaux display xml where {{?porte 	rdf:type 		ontoCC:IfcDoor ?rfe	rdf:type		ontoCC:IfcRelFillsElement ?rfe	ontoCC:relatingOpeningElement	?ro ?ro	rdf:type		ontoCC:IfcOpeningElement ?rfe	ontoCC:relatedBuildingElement	?porte  ?rsb	rdf:type		ontoCC:IfcRelSpaceBoundary ?rsb	ontoCC:relatingSpace	?locaux ?rsb	ontoCC:relatedBuildingElement	?ro ?locaux	rdf:type		ontoCC:CabineDouche }UNION { ?porte 	rdf:type 		ontoCC:IfcDoor } OPTIONAL { ?porte ontoCC:overallWidth 	?width FILTER ( xsd:integer(?width) >= 80) } FILTER (! bound( ?width) ) }";
		Query query = new Query(prefixOnto + sparql);
		mappedQueries.put(queryCode, query);

		// queryCode = "r00010190";
		// sparql = "select ?porte ?def ?pstyle ?panel ?pwidth " +
		// "display xml where { ?porte rdf:type ontoCC:IfcDoor ?def rdf:type " +
		// "ontoCC:IfcRelDefinesByType ?def ontoCC:relatingType ?pstyle ?def " +
		// "ontoCC:relatedObjects ?porte ?pstyle rdf:type ontoCC:IfcDoorStyle "
		// +
		// "?pstyle ontoCC:operationType ?t FILTER (?t ~ 'double_door') OPTIONAL "
		// +
		// "{ ?pstyle ontoCC:hasPropertySets ?panel ?panel rdf:type ontoCC:IfcDoorPanelProperties ?panel "
		// +
		// "ontoCC:panelWidth ?pwidth FILTER ( xsd:integer(?pwidth) >= 80) } FILTER (! bound( ?pwidth) ) } ";
		// query = new Query(prefixOnto + sparql);
		// mappedQueries.put(queryCode, query);

		HashMap<String, ArrayList<String>> results = engine
				.runMappedQueries(mappedQueries);

		assertNotNull(results);

		printResults(results);

	}

	private void printResults(ArrayList<String> results) {

		log.info("+++ QUERY RESULTS:");
		for (String result : results) {
			log.info(result);
		}

	}

	private void printResults(HashMap<String, ArrayList<String>> results) {

		log.info("+++ MAPPED QUERY RESULTS:");
		Iterator<String> iterator = results.keySet().iterator();
		while (iterator.hasNext()) {

			String queryRefNumber = iterator.next();
			log.info("Query refrence number: " + queryRefNumber);

			ArrayList<String> queryResults = results.get(queryRefNumber);
			if (queryResults.isEmpty()) {
				log.info("NO Results");
			} else {
				log.info("not emty ... running through results");
				for (String result : queryResults) {
					log.info(result);
				}
			}

		}

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
