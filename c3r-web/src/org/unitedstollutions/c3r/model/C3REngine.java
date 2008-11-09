/**
 * 
 */
package org.unitedstollutions.c3r.model;

import fr.inria.acacia.corese.api.EngineFactory;
import fr.inria.acacia.corese.api.IEngine;
import fr.inria.acacia.corese.api.IResults;
import fr.inria.acacia.corese.exceptions.EngineException;

/**
 * @author ruben.stoll
 * 
 */
public class C3REngine {

	private String query;
	private EngineFactory ef;
	private IEngine engine;
	private String propertyFile;
	private String dataPath;
	private String engineSchema;
	private String engineData;
	private String engineRule;
	private String log4jProperties;
	private Boolean engineRun;
	
	
	public C3REngine() {
		ef = new EngineFactory();
		engine = ef.newInstance();
	}

	public IResults runQuery() {
		IResults results = null;

		try {
			results = engine.SPARQLQuery(query);
		} catch (EngineException e) {
			e.printStackTrace();
		}
		
		return results;

	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @param query the query to set
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
	 * @param propertyFile the propertyFile to set
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
	 * @param dataPath the dataPath to set
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
	 * @param engineSchema the engineSchema to set
	 */
	public void setEngineSchema(String engineSchema) {
		this.engineSchema = engineSchema;
//		ef.setProperty(EngineFactory.ENGINE_SCHEMA,
//		"data/humans.rdfs ontology/owlOntology.owl onto");
		ef.setProperty(EngineFactory.ENGINE_SCHEMA,engineSchema);
	}

	/**
	 * @return the engineData
	 */
	public String getEngineData() {
		return engineData;
	}

	/**
	 * @param engineData the engineData to set
	 */
	public void setEngineData(String engineData) {
		this.engineData = engineData;
//		ef.setProperty(EngineFactory.ENGINE_DATA, "data/humans.rdf");
		ef.setProperty(EngineFactory.ENGINE_DATA, engineData);
	}

	/**
	 * @return the engineRule
	 */
	public String getEngineRule() {
		return engineRule;
	}

	/**
	 * @param engineRule the engineRule to set
	 */
	public void setEngineRule(String engineRule) {
		this.engineRule = engineRule;
//		ef.setProperty(EngineFactory.ENGINE_RULE, "data/humans.rul");
		ef.setProperty(EngineFactory.ENGINE_RULE, engineRule);
	}

	/**
	 * @return the log4jProperties
	 */
	public String getLog4jProperties() {
		return log4jProperties;
	}

	/**
	 * @param log4jProperties the log4jProperties to set
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
	 * @param engineRun the engineRun to set
	 */
	public void setEngineRun(Boolean engineRun) {
		this.engineRun = engineRun;
		// TODO check if toString on a boolean returns True and False respectively.
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, engineRun.toString());
	}

}
