/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import fr.inria.acacia.corese.api.EngineFactory;
import fr.inria.acacia.corese.api.IEngine;
import fr.inria.acacia.corese.api.IResult;
import fr.inria.acacia.corese.api.IResultValue;
import fr.inria.acacia.corese.api.IResults;
import fr.inria.acacia.corese.exceptions.EngineException;

/**
 * @author ruben.stoll
 * 
 */
public class C3REngine {

	private EngineFactory ef;
	private IEngine engine;

	Logger logger = Logger.getLogger(C3REngine.class);

	// first alternative to create a singleton
	private static C3REngine c3rEngineSingleton;

	// TODO remove singleton creation.
	// It may be useful to be able to create different engines and thus many different instances useful.
	protected C3REngine() {
		// Exists only to defeat instantiation.
	}

	/**
	 * @return a single C3REngine instance
	 */
	public static C3REngine getInstance() {
		if (c3rEngineSingleton == null) {
			synchronized (C3REngine.class) {
				c3rEngineSingleton = new C3REngine();
				c3rEngineSingleton.ef = new EngineFactory();
				c3rEngineSingleton.engine = c3rEngineSingleton.ef.newInstance();

			}
		}
		return c3rEngineSingleton;
	}

	public void doAllInOne(String schema, String data, String rule) {

		try {
			engine.load(schema);
			engine.load(data);
			engine.load(rule);
			engine.runRuleEngine();
		} catch (EngineException e) {
			e.printStackTrace();
		}

		String prefixOnto = "PREFIX ontoCC: <http://www.owl-ontologies.com/Ontology1205837312.owl#>";
		String queryString = prefixOnto
				+ " select ?x display xml where { ?x  rdf:type   ontoCC:Sas  }";

		try {
			IResults res = engine.SPARQLQuery(queryString);
			logger.debug(res);
		} catch (EngineException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Load a single file to the engine. Try to create an Engine Factory and an
	 * IEngine first if possible and if you don't one will be created
	 * automatically
	 */
	public void loadFile(String data) {

		try {
			engine.load(data);
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Load a directory containing data. Try to create an Engine Factory and an
	 * IEngine first if possible and if you don't one will be created
	 * automatically
	 */
	public void loadDirectory(String directory) {
		try {
			engine.load(directory);
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Applies all the data loaded to the Corese engine. Wrapper method to the
	 * original corese runRuleEngine() method.
	 * 
	 */
	public void runRuleEngine() {

		logger.debug("+++ running rule engine");

		try {
			engine.runRuleEngine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @return
	 */
	public ArrayList<String> runQuery(String query) {
		IResults results = null;
		ArrayList<String> parsedResults = null;
		// TODO add null query string check here
		// TODO add application path must be defined
		try {
			results = engine.SPARQLQuery(query);
			parsedResults = parseIResults(results);
		} catch (EngineException e) {
			logger.debug("Exception thrown when running query: " + query, e);
		}

		return parsedResults;

	}

	/**
	 * @param results
	 * @return
	 */
	private ArrayList<String> parseIResults(IResults results) {

		ArrayList<String> parsedResults = null;

		String[] variables = results.getVariables();

		if (variables.length != 0) {

			parsedResults = new ArrayList<String>();
			// go through all results
			for (Enumeration<IResult> en = results.getResults(); en
					.hasMoreElements();) {
				// get a result
				IResult r = en.nextElement();
				// go through this result
				for (String var : variables) {
					if (r.isBound(var)) {
						// get result values for each selected variable
						IResultValue[] values = r.getResultValues(var);
						for (int j = 0; j < values.length; j++) {
							logger.info(var + " !!INFO = "
									+ values[j].getStringValue());
							parsedResults.add(values[j].getStringValue());
						}

					} else {
						logger.debug(var + " = Not bound");
					}
				}
			}
		}
		return parsedResults;
	}

	/**
	 * @return
	 */
	public HashMap<String, ArrayList<String>> runMappedQueries(
			HashMap<String, Query> mappedQueries) {

		HashMap<String, ArrayList<String>> mappedresults = null;
		Iterator<String> iterator = mappedQueries.keySet().iterator();

		// run the queries
		mappedresults = new HashMap<String, ArrayList<String>>();
		while (iterator.hasNext()) {

			String queryRefNumber = iterator.next();
			Query currentQuery = mappedQueries.get(queryRefNumber);
			
			// TODO initialize logging correctly because debug is not active
			logger.info("query reference number: " + queryRefNumber);
			logger.info("query sparql: " + currentQuery.getSparql());

			ArrayList<String> currResults = runQuery(currentQuery.getSparql());

			mappedresults.put(queryRefNumber, currResults);
		}

		return mappedresults;

	}

}
