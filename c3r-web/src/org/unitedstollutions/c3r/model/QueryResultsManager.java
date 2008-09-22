/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.Enumeration;
import java.util.HashMap;

import fr.inria.acacia.corese.api.IResult;
import fr.inria.acacia.corese.api.IResultValue;
import fr.inria.acacia.corese.api.IResults;

/**
 * @author ruben.stoll
 * 
 */
public class QueryResultsManager {

	private HashMap<String, Query> queries;

	public QueryResultsManager() {
		if (this.queries == null) {
			this.queries = new HashMap<String, Query>();
		}
	}

	/**
	 * @return the queries
	 */
	public HashMap<String, Query> getQueries() {
		return queries;
	}

	/**
	 * @param queries
	 *            the queries to set
	 */
	public void setQueries(HashMap<String, Query> queries) {
		this.queries = queries;
	}

	public void setResults(IResults res) {

		HashMap<String, Query> selectedQueries = new HashMap<String, Query>();
		
		// go through all results
		for (Enumeration<IResult> en = res.getResults(); en.hasMoreElements();) {
			// get a result
			IResult r = en.nextElement();
			Query q = new Query();
			String queryName = "";
			String queryDescription = "";
			String querySparql = "";
			
			if (r.isBound("?queryName")) {
				System.out.println("+++ " + r.getStringValue("?queryName"));
				queryName = r.getStringValue("?queryName");
				q.setQueryName(queryName);
			}
			if (r.isBound("?queryDescription")) {
				System.out.println("+++ "
						+ r.getStringValue("?queryDescription"));
				queryDescription = r.getStringValue("?queryDescription");
				q.setQueryDescription(queryDescription);
			}
			if (r.isBound("?sparqlContent")) {
				System.out.println("+++ " + r.getStringValue("?sparqlContent"));
				querySparql = r.getStringValue("?sparqlContent");
				q.setSparql(querySparql);
			}
			
			selectedQueries.put(queryName, q);
			
		}
		
		setQueries(selectedQueries);
	}

}; // end of class