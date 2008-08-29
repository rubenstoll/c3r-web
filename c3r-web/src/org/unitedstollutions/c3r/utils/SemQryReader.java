/**
 * 
 */
package org.unitedstollutions.c3r.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URI;
import java.util.List;

import org.unitedstollutions.c3r.model.AnnotationRegle;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author ruben.stoll
 * 
 */
public class SemQryReader {

	private XMLReader parser;
	private SemQryHandler handler;
	private List<AnnotationRegle> semQryFiles; // list of annotations
	private File semQryFilesPath;
	private URI semQryFilesPathURL;
	
	/**
	 * Create a parser object, depending on which parser is available.
	 * 
	 */
	private void createReader() {

		try {
			parser = XMLReaderFactory
					.createXMLReader("org.apache.xerces.parsers.SAXParser");
		} catch (SAXException e1) {
			try {
				parser = XMLReaderFactory.createXMLReader();
			} catch (SAXException e2) {
				throw new NoClassDefFoundError("No SAX parser is available");
				// or whatever exception the method is declared to throw
			}
		}

	}

	/*
	 * Creates an XML parser and reads instance's specified ifc file
	 */
	private void parse(List<String> semQryFiles) {

		// create a parser instance
		createReader();

		// Since this just writes onto the console, it's best
		// to use the system default encoding, which is what
		// we get by not specifying an explicit encoding here.
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));

		// register a content handler
		registerContentHandler(out);

		// register a error handler
		// registerErrorHandler(out);

		parser.setContentHandler(handler);

		for (String semQryFile : semQryFiles) {

			String filePath = this.semQryFilesPath
					.toString()
					+ File.separator + semQryFile;

			// DEBUG
			// System.out.println("parsing: " + currAnnoRegleFileAndPath);
			try {

				// needs this strange long system depent path - this is not
				// needed in UNIX - crap windows!
				parser.parse(new File(filePath).toURI()
						.toString());

				out.flush();

			} catch (IOException e) {
				System.out.println("Parsing IO error occurred");
				e.printStackTrace();
			} catch (SAXException e) {
				System.out.println("SAX exception");
				System.out.println(filePath
						+ " is not well-formed.");
			}

		}

		this.semQryFiles = handler.getCollectedRules();

	}
	/**
	 * Creates a content handler
	 * 
	 * @param out
	 */
	private void registerContentHandler(Writer out) {

		handler = new SemQryHandler();

	}
	/*
	 * 
	 * @param fileToParse
	 */
	private void parse(String fileToParse) {

		createReader();
		Writer out = new BufferedWriter(new OutputStreamWriter(System.out));
		registerContentHandler(out);
		parser.setContentHandler(handler);
		try {
			parser.parse(fileToParse);
			out.flush();
			this.semQryFiles = handler.getCollectedRules();
		} catch (IOException e) {
			System.out.println("Parsing IO error occurred");
			e.printStackTrace();

		} catch (SAXException e) {
			System.out.println("SAX exception");
			System.out.println(fileToParse + " is not well-formed.");
		}
	}

}
