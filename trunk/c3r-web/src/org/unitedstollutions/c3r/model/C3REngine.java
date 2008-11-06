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

	IResults results;
	String query;
	EngineFactory ef;
	IEngine engine;

	public C3REngine() {
		ef = new EngineFactory();
		engine = ef.newInstance();
	}

	public void runQuery() {
		try {
			results = engine.SPARQLQuery(query);
		} catch (EngineException e) {
			e.printStackTrace();
		}

	}

	public void setProperties() {
		ef.setProperty(EngineFactory.PROPERTY_FILE, "corese.properties");
		ef.setProperty(EngineFactory.DATAPATH, "D:/toto/corese/data");
		ef.setProperty(EngineFactory.ENGINE_LOG4J, "prop/log4j.properties");
		ef.setProperty(EngineFactory.ENGINE_SCHEMA,
				"data/humans.rdfs ontology/owlOntology.owl onto");
		ef.setProperty(EngineFactory.ENGINE_DATA, "data/humans.rdf");
		ef.setProperty(EngineFactory.ENGINE_RULE, "data/humans.rul");
		ef.setProperty(EngineFactory.ENGINE_RULE_RUN, "true");

	}

}
