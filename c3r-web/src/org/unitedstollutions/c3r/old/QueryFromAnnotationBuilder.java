/**
 * 
 */
package org.unitedstollutions.c3r.old;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author yurchyshyna
 *
 */
public class QueryFromAnnotationBuilder {
	
	private ArrayList<String> queriesFull = new ArrayList<String>();
	private ArrayList<String> queriesShort = new ArrayList<String>();
	private ArrayList<String> queriesToCorese = new ArrayList<String>();
	private ArrayList<String> queriesTexts = new ArrayList<String>();
	private ArrayList<String> queriesShortWithText = new ArrayList<String>();
	private ArrayList<QueryInfo> queryInfoList = new ArrayList<QueryInfo>();
	
	//build from a list of annotations files names
	// tested => works fine
	public QueryFromAnnotationBuilder(ArrayList<String> listOfAnnotations){
		ArrayList<String> listSelectedQueriesFull = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShort = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesTexts = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShortWithTexts = new ArrayList<String>();
		//ArrayList<QueryInfo> listQueryInfo = new ArrayList<QueryInfo>();
		
		for (String annCurr : listOfAnnotations) {
			//String fullCurrName = namesFile + annCurr;
			//System.out.println("current rdf is " +fullCurrName);
			//condition is Selecting ALL queries "//AnnotationRegle/contenuRegle"
			TestDOMXPath tpFull = new TestDOMXPath(annCurr);
			String curQuery = tpFull.getQuery(); //taking full query name
			//reading a query text 
			TestDOMXPath tpText = new TestDOMXPath(annCurr, "//AnnotationRegle/textRegle");
			String curQText = tpText.getQuery();
			//System.out.println(curQuery);
			// putting text and contenu as variables of QueryInfo
			QueryInfo qi = new QueryInfo(curQuery, curQText);
			if (!(curQuery.equals(""))) {
				listSelectedQueriesFull.add(curQuery);
				listSelectedQueriesShort.add(curQuery.substring(48)); //taking queryN.txt
				listSelectedQueriesTexts.add(curQText);
				listSelectedQueriesShortWithTexts.add(curQuery.substring(48) + " : " + curQText);
				queryInfoList.add(qi);
				
			}
		}
		queriesFull = listSelectedQueriesFull;
		queriesShort = listSelectedQueriesShort;
		queriesTexts = listSelectedQueriesTexts;
		queriesShortWithText = listSelectedQueriesShortWithTexts;
	}
	
	public QueryFromAnnotationBuilder(ArrayList<String> listOfAnnotations, String conditionExpression){
		ArrayList<String> listSelectedQueriesFull = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShort = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShortWithTexts = new ArrayList<String>();
		for (String annCurr : listOfAnnotations) {
			TestDOMXPath tpC = new TestDOMXPath(annCurr, conditionExpression);
			String curQuery = tpC.getQuery(); //taking full query name
			//System.out.println(curQuery);
//			//reading a query text 
			TestDOMXPath tpText = new TestDOMXPath(annCurr, "//AnnotationRegle/textRegle");
			String curQText = tpText.getQuery();
			if (!(curQuery.equals(""))) {
				listSelectedQueriesFull.add(curQuery);
				listSelectedQueriesShort.add(curQuery.substring(48)); //taking queryN.txt
				listSelectedQueriesShortWithTexts.add(curQuery.substring(48)+ " : " +curQText);
			}
		}
		queriesFull = listSelectedQueriesFull;
		queriesShort = listSelectedQueriesShort;
		queriesShortWithText = listSelectedQueriesShortWithTexts;
	}
	
	public QueryFromAnnotationBuilder(String namesFile) {
		ArrayList<String> listAnnotationNames = getFilesFromDir(namesFile);
		ArrayList<String> listSelectedQueries = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesNames = new ArrayList<String>();
		ArrayList<String> listSelectedQueriesShortWithTexts = new ArrayList<String>();
		for (String annCurr : listAnnotationNames) {
			String fullCurrName = namesFile + annCurr;
			//System.out.println("current rdf is " +fullCurrName);
			TestDOMXPath tpFull = new TestDOMXPath(fullCurrName, "//AnnotationRegle/contenuRegle");
			String curQuery = tpFull.getQuery();
			//System.out.println(curQuery);
//			reading a query text 
			TestDOMXPath tpText = new TestDOMXPath(annCurr, "//AnnotationRegle/textRegle");
			String curQText = tpText.getQuery();
			if (!(curQuery.equals(""))) {
				listSelectedQueries.add(curQuery);
				listSelectedQueriesNames.add(curQuery.substring(48));
				listSelectedQueriesShortWithTexts.add(curQuery.substring(48)+ " : " +curQText);
			}
		}
		//System.out.println("queries of the list : ");
		for (String q : listSelectedQueries){
			//System.out.println("q : " + q);
			//System.out.println("q : " + q.substring(48));
		}
		queriesFull = listSelectedQueries;
		queriesShort = listSelectedQueriesNames;
		queriesShortWithText = listSelectedQueriesShortWithTexts;
	}
	
	public ArrayList<String> getQueriesFull(){
		return queriesFull;
	}
	
	public ArrayList<String> getQueriesShort(){
		return queriesShort;
	}
	
	public ArrayList<String> getQueriesShortWithTexts(){
		return queriesShortWithText;
	}
	
	public ArrayList<String> getFilesFromDir(String pathToDir){
		ArrayList<String> dirFiles = new ArrayList<String>();
		File f = new File(pathToDir); 

		if (f.exists()) {
			String[] directoryListing = f.list();
			for (String currFile : directoryListing) {
				if (currFile.endsWith("rdf")) {
					dirFiles.add(currFile);
				}
			}
		}
		// DEBUG - remove later
		System.out.println("INFO : Currently, the regulation base has " + dirFiles.size()+ " regulations");
		//System.out.println("\n\n");
		return dirFiles;
	}

	
	public ArrayList<String> getQueriesToCorese() {
/*		QueryDialog queryListDialog = new QueryDialog(null, this.queriesFull);
		queriesToCorese = queryListDialog.getSelectedQueries();
		return queriesToCorese;*/
		return null;
	}
	

	
	
}