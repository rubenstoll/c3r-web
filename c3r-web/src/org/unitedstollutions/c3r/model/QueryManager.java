/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.HashMap;

/**
 * @author ruben.stoll
 *
 */
public class QueryManager {

	private HashMap<String, Query> queries;

	public QueryManager() {
		queries = new HashMap<String, Query>();
	}

	/**
	 * @return the queries
	 */
	public HashMap<String, Query> getQueries() {
		return queries;
	}

	/**
	 * @param queries the queries to set
	 */
	public void setQueries(HashMap<String, Query> queries) {
		this.queries = queries;
	}
	
}
