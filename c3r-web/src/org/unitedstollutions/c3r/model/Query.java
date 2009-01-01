/**
 * 
 */
package org.unitedstollutions.c3r.model;

/**
 * @author ruben.stoll
 *
 */
public class Query {

	private String name;
	private String type;
	private String description;
	private String sparql;
	
	public Query() {
		
	}
	
	public Query(String sparql) {
		this.sparql = sparql;
	}
	
	/**
	 * @return the queryName
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param queryName the queryName to set
	 */
	public void setName(String queryName) {
		this.name = queryName;
	}
	/**
	 * @return the queryType
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param queryType the queryType to set
	 */
	public void setType(String queryType) {
		this.type = queryType;
	}
	/**
	 * @return the queryDescription
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param queryDescription the queryDescription to set
	 */
	public void setDescription(String queryDescription) {
		this.description = queryDescription;
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
