/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 *
 */
public class Query {

	private String queryName;
	private String queryType;
	private String queryDescription;
	private String sparql;
	
	/**
	 * @return the queryName
	 */
	public String getQueryName() {
		return queryName;
	}
	/**
	 * @param queryName the queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
	/**
	 * @return the queryType
	 */
	public String getQueryType() {
		return queryType;
	}
	/**
	 * @param queryType the queryType to set
	 */
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	/**
	 * @return the queryDescription
	 */
	public String getQueryDescription() {
		return queryDescription;
	}
	/**
	 * @param queryDescription the queryDescription to set
	 */
	public void setQueryDescription(String queryDescription) {
		this.queryDescription = queryDescription;
	}
	/**
	 * @return the sparql
	 */
	public String getSparql() {
		return sparql;
	}
	/**
	 * @param sparql the sparql to set
	 */
	public void setSparql(String sparql) {
		this.sparql = sparql;
	}
	
	
}
