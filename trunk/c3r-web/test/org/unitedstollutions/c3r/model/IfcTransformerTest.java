/**
 * 
 */
package org.unitedstollutions.c3r.model;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author ruben
 *
 */
public class IfcTransformerTest {

	private static final Log log = LogFactory.getLog(IfcTransformerTest.class);

	private String dataDirectory;


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// run for one time before all test cases
		File currDir = new File(System.getProperty("user.dir"));
		log.info("current directory is:" + currDir);

		dataDirectory = currDir.toString() + File.separator + "war"
				+ File.separator + "data";

		log.info("++++ Starting test");

	}
	
	/**
	 * Test method for {@link org.unitedstollutions.c3r.model.IfcTransformer#setXsltFile(java.io.File)}.
	 */
	@Test
	public final void testSetGetXsltFile() {
		
		IfcTransformer it = new IfcTransformer();
		String xslt = dataDirectory + File.separator + "transforms" + File.separator + "attrIntoElem.xslt";
		File xsltFile = new File(xslt);
		it.setXsltFile(xsltFile);
		assertEquals(xslt, it.getXsltFile().toString());
		
	}
	
	/**
	 *
	 */
	@Test
	public final void testSetGetUntransformedFile() {
		
		IfcTransformer it = new IfcTransformer();
		String uxf = "customIfc.ifcxml";
		String u = dataDirectory + File.separator + "annotations" + File.separator + uxf;
		File untrauntransformedFile = new File(u);
		it.setUntransformedFile(untrauntransformedFile);
		assertEquals(untrauntransformedFile, it.getUntransformedFile());
		
	}	

	/**
	 *
	 */
	@Test
	public final void testSetGetTransformedFile() {
		
		IfcTransformer it = new IfcTransformer();

		String txf = "customIfc.ifcxml.xml";
		String tx = dataDirectory + File.separator +  "annotations" + File.separator + txf;
		File transformedFile = new File(tx);
		it.setTransformedFile(transformedFile);
		assertEquals(transformedFile, it.getTransformedFile());
		
	}	
	
	/**
	 *
	 */
	@Test
	public final void testTransformation() {
		
		IfcTransformer it = new IfcTransformer();

		String xslt = dataDirectory + File.separator + "transforms" + File.separator + "attrIntoElem.xslt";
		File xsltFile = new File(xslt);
		it.setXsltFile(xsltFile);

		String uxf = "customIfc.ifcxml";
		String u = dataDirectory + File.separator + "annotations" + File.separator + uxf;
		File untrauntransformedFile = new File(u);
		it.setUntransformedFile(untrauntransformedFile);

		String txf = "customIfc.ifcxml.xml";
		String tx = dataDirectory + File.separator +  "annotations" + File.separator + txf;
		File transformedFile = new File(tx);
		it.setTransformedFile(transformedFile);

		try {
			it.transform();
		} catch(TransformerException te) {
			log.error("++ Transformer Exception thrown",te);
		}
		
		assertTrue(it.getTransformedFile().exists());
	}
}
