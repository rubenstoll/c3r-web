/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

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

	// private HashMap<String, Query> mappedQueries;
//	private HashMap<String, ArrayList<String>> mappedresults;
	private EngineFactory ef;
	private IEngine engine;
	private String propertyFile;
	private String dataPath;
	private String engineSchema;
	private String engineData;
	private String engineRule;
	private String log4jProperties;
	private Boolean engineRun;

	// first alternative to create a singleton
	private static C3REngine c3rEngineSingleton;

	// second alternative to create a simple, fast and thread safe singleton
	// public final static C3REngine c3rEngSingleton = new C3REngine();

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
			}
		}
		return c3rEngineSingleton;
	}

	/**
	 * Creates an engine factory as described in the Corese API
	 */
	public void createEngineFactory() {

		ef = new EngineFactory();
	}

	/**
	 * Creates an IEngine as described in the Corese API
	 */
	public void createIEngine() {

		engine = ef.newInstance();

	}

	/**
	 * method to create an engine factory and an IEngine instance in one shot.
	 * Alternatively each method can be called separately.
	 */
	private void create() {
		createEngineFactory();
		createIEngine();
	}

	/**
	 * Load a single file to the engine. Try to create an Engine Factory and an
	 * IEngine first if possible and if you don't one will be created
	 * automatically
	 */
	public void loadFile(String data) {
		if (!engineExists()) {
			create();
		}
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
		if (!engineExists()) {
			create();
		}
		try {
			engine.load(directory);
		} catch (EngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return
	 */
	private Boolean engineExists() {

		if (engine == null) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Applies all the data loaded to the Corese engine. Wrapper method to the
	 * original corese runRuleEngine() method.
	 * 
	 */
	public void runRuleEngine() {

		if (!engineExists()) {
			// TODO throw an exception or add an assert
			return;
		}
		System.out.println("+++ running rule engine");

		engine.runRuleEngine();

	}

	/**
	 * @return
	 */
	public ArrayList<String> runQuery(String query) {
		IResults results = null;
		// TODO add null query string check here
		// TODO add application path must be defined	
		try {
			results = engine.SPARQLQuery(query);
		} catch (EngineException e) {
			e.printStackTrace();
		}

		return parseIResults(results);

	}
	
	
	/**
	 * @param results
	 * @return
	 */
	private ArrayList<String> parseIResults(IResults results) {
		
		ArrayList<String> parsedResults = new ArrayList<String>();
		
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
					for (int j = 0; j < values.length; j++) {
						System.out.println(var + " = "
								+ values[j].getStringValue());
						parsedResults.add(values[j].getStringValue());
					}
					
				} else {
					System.out.println(var + " = Not bound");
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

		HashMap<String, ArrayList<String>> mappedresults = new HashMap<String, ArrayList<String>>();
		Iterator<String> iterator = mappedQueries.keySet().iterator();

		while (iterator.hasNext()) {

			String queryRefNumber = iterator.next();
			System.out.println(queryRefNumber);
			Query currentQuery = mappedQueries.get(queryRefNumber);
			ArrayList<String> currResults = runQuery(currentQuery.getSparql());
			
			mappedresults.put(queryRefNumber, currResults);

		}
		
		return mappedresults;

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
		// ef.setProperty(EngineFactory.PROPERTY_FILE, "corese.properties");
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
		// ef.setProperty(EngineFactory.DATAPATH, "D:/toto/corese/data");
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
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, engineRun.toString());
	}

}