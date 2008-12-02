/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import fr.inria.acacia.corese.api.IResult;
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

	/**
	 * Takes a set of results returned by a query run and a and a list of names
	 * matching the names of the results returned by the query. This method sets
	 * the object's queries properties with the queries matching the list of names
	 * which were selected in a checkbox list or through some other way.
	 * 
	 * @param res
	 * @param selectedQueries
	 */
	public void setQueries(IResults res, ArrayList<String> selectedQueries) {

		HashMap<String, Query> finalQueries = new HashMap<String, Query>();

		// go through all results
		for (Enumeration<IResult> en = res.getResults(); en.hasMoreElements();) {
			// get a result
			IResult r = en.nextElement();
			Query q = new Query();
			String queryName = "";
			String queryDescription = "";
			String querySparql = "";

			if (r.isBound("?queryName")) {
				String tmp = r.getStringValue("?queryName");
				// get the part of the string after the pound
//				System.out.println("String is:" + tmp);
				int hashIndex = tmp.indexOf("#") + 1;
//				System.out.println("index is at: " + hashIndex);
				queryName = tmp.substring(hashIndex);
//				System.out.println("+++ " + queryName);
				q.setName(queryName);
			}
			// check to see if the result is one of the selected queries 
			// and if it is get the rest of the information and add it 
			// to the hashmap
			if (selectedQueries.contains(queryName)) {
				if (r.isBound("?queryDescription")) {
					// remove leading and trailing spaces
					queryDescription = r.getStringValue("?queryDescription")
							.trim();
//					System.out.println("+++ " + queryDescription);
					q.setDescription(queryDescription);
				}
				if (r.isBound("?sparqlContent")) {
					// remove leading and trailing spaces
					querySparql = r.getStringValue("?sparqlContent").trim();
//					System.out.println("+++ " + querySparql);
					q.setSparql(querySparql);
				}

				finalQueries.put(queryName, q);
				System.out.println("++++++ added query: " + queryName);
				
			}
		}

		setQueries(finalQueries);
	}

}; // end of class