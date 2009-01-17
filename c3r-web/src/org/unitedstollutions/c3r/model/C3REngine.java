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

	private String query;
	private String propertyFile;
	private String dataPath;
	private String engineSchema;
	private String engineData;
	private String engineRule;
	private String log4jProperties;
	private Boolean engineRun;

	Logger logger = Logger.getLogger(C3REngine.class);

	public C3REngine() {
		this.ef = new EngineFactory();
	}

	public void createIEngineInstance() {
		
		if(this.engine == null) {
			this.engine = this.ef.newInstance();
		}

	}
	
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the propertyFile
	 */
	public String getPropertyFile() {
		return propertyFile;
	}

	/**
	 * @param propertyFile
	 *            the propertyFile to set
	 */
	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
		ef.setProperty(EngineFactory.PROPERTY_FILE, propertyFile);
	}

	/**
	 * @return the dataPath
	 */
	public String getDataPath() {
		return dataPath;
	}

	/**
	 * @param dataPath
	 *            the dataPath to set
	 */
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
		ef.setProperty(EngineFactory.DATAPATH, dataPath);
	}

	/**
	 * @return the engineSchema
	 */
	public String getEngineSchema() {
		return engineSchema;
	}

	/**
	 * @param engineSchema
	 *            the engineSchema to set
	 */
	public void setEngineSchema(String engineSchema) {
		this.engineSchema = engineSchema;
		// ef.setProperty(EngineFactory.ENGINE_SCHEMA,
		// "data/humans.rdfs ontology/owlOntology.owl onto");
		ef.setProperty(EngineFactory.ENGINE_SCHEMA, engineSchema);
	}

	/**
	 * @return the engineData
	 */
	public String getEngineData() {
		return engineData;
	}

	/**
	 * @param engineData
	 *            the engineData to set
	 */
	public void setEngineData(String engineData) {
		this.engineData = engineData;
		// ef.setProperty(EngineFactory.ENGINE_DATA, "data/humans.rdf");
		ef.setProperty(EngineFactory.ENGINE_DATA, engineData);
	}

	/**
	 * @return the engineRule
	 */
	public String getEngineRule() {
		return engineRule;
	}

	/**
	 * @param engineRule
	 *            the engineRule to set
	 */
	public void setEngineRule(String engineRule) {
		this.engineRule = engineRule;
		// ef.setProperty(EngineFactory.ENGINE_RULE, "data/humans.rul");
		ef.setProperty(EngineFactory.ENGINE_RULE, engineRule);
	}

	/**
	 * @return the log4jProperties
	 */
	public String getLog4jProperties() {
		return log4jProperties;
	}

	/**
	 * @param log4jProperties
	 *            the log4jProperties to set
	 */
	public void setLog4jProperties(String log4jProperties) {
		this.log4jProperties = log4jProperties;
		ef.setProperty(EngineFactory.ENGINE_LOG4J, log4jProperties);
	}

	/**
	 * @return the engineRun
	 */
	public Boolean getEngineRun() {
		return engineRun;
	}

	/**
	 * @param engineRun
	 *            the engineRun to set
	 */
	public void setEngineRun(Boolean engineRun) {
		this.engineRun = engineRun;
		// TODO check if toString on a boolean returns True and False
		// respectively.
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, engineRun.toString());
	}

	/**
	 * Load a single file to the engine. Try to create an Engine Factory and an
	 * IEngine first if possible and if you don't one will be created
	 * automatically
	 */
	public void loadFile(String data) throws EngineException {

		if(engine == null) {
			createIEngineInstance();
		}
		engine.load(data);
	}

	/**
	 * Load a directory containing data. Try to create an Engine Factory and an
	 * IEngine first if possible and if you don't one will be created
	 * automatically
	 */
	public void loadDirectory(String directory) throws EngineException {

		if(engine == null) {
			createIEngineInstance();
		}
		engine.load(directory);

	}

	/**
	 * Applies all the data loaded to the Corese engine. Wrapper method to the
	 * original corese runRuleEngine() method.
	 * 
	 */
	public void runRuleEngine() {

		logger.debug("+++ running rule engine");

		if(engine == null) {
			createIEngineInstance();
		}
		engine.runRuleEngine();

	}

	/**
	 * @return
	 */
	public ArrayList<String> runQuery(String query) throws EngineException {

		IResults results = null;
		ArrayList<String> parsedResults = null;

		if(engine == null) {
			createIEngineInstance();
		}
		results = engine.SPARQLQuery(query);
		parsedResults = parseIResults(results);

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
							logger.debug(var + " !!INFO = "
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
			HashMap<String, Query> mappedQueries) throws EngineException {

		HashMap<String, ArrayList<String>> mappedresults = null;
		Iterator<String> iterator = mappedQueries.keySet().iterator();

		// run the queries
		mappedresults = new HashMap<String, ArrayList<String>>();
		while (iterator.hasNext()) {

			String queryRefNumber = iterator.next();
			Query currentQuery = mappedQueries.get(queryRefNumber);

			logger.debug("QUERY REFERENCE NUMBER: " + queryRefNumber);
			logger.debug("QUERY SPARQL: " + currentQuery.getSparql());

			ArrayList<String> currResults = runQuery(currentQuery.getSparql());

			mappedresults.put(queryRefNumber, currResults);
		}

		return mappedresults;

	}

}
