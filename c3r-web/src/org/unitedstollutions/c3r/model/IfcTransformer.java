/**
 * 
 */
package org.unitedstollutions.c3r.model;

import java.io.File;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.unitedstollutions.c3r.utils.URLUtils;

/**
 * @author yurchyshyna
 * 
 */
public class IfcTransformer {

	Logger logger = Logger.getLogger(IfcTransformer.class);

	// properties
	private File xsltFile;
	private File untransformedFile;
	private File transformedFile;


	/**
	 * @return the xsltFile
	 */
	public File getXsltFile() {
		return xsltFile;
	}

	/**
	 * Sets the xstl sheet which will be used for the transformation
	 * 
	 * @param xsltFile
	 *            the xsltFile to set
	 */
	public void setXsltFile(File xsltFile) {
		this.xsltFile = xsltFile;
	}

	/**
	 * @return the untransformedFile
	 */
	public File getUntransformedFile() {
		return untransformedFile;
	}

	/**
	 * @param untransformedFile
	 *            the untransformedFile to set
	 */
	public void setUntransformedFile(File untransformedFile) {
		this.untransformedFile = untransformedFile;
	}

	/**
	 * @return the transformedFile
	 */
	public File getTransformedFile() {
		return transformedFile;
	}

	/**
	 * @param transformedFile
	 *            the transformedFile to set
	 */
	public void setTransformedFile(File transformedFile) {
		this.transformedFile = transformedFile;
	}

	public void transform() throws TransformerException {

		// JAXP reads data using the Source interface
		Source xmlSource = new StreamSource(this.untransformedFile);
		Source xsltSource = new StreamSource(this.xsltFile);

		logger.debug("Results will go to: "
				+ this.transformedFile.getAbsolutePath());

		// send the result to a file
		Result result = new StreamResult(this.transformedFile);

		// the factory pattern supports different XSLT processors
		TransformerFactory transFact = TransformerFactory.newInstance();
		Transformer trans = transFact.newTransformer(xsltSource);

		// have the transformer factory do the actual transformation
		trans.transform(xmlSource, result);

	}

	/**
	 * Reads a uri or file on a remote server and returns a file object to be
	 * used locally. Note that this method should not be in this class and will
	 * probably not be needed since the xslt file should reside alongside the
	 * application.
	 * 
	 * @param uri
	 * @return xslt file
	 */
	private File getXsltFromUri(String uri) {

		// use url reading utilities to read from a url if from a url
		URLUtils fetcher = URLUtils.URLUTIL_INSTANCE;
		File xsltFile = fetcher.readFetchFile(uri);

		return xsltFile;

	}

}
