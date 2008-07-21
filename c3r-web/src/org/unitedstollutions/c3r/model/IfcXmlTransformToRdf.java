/**
 * 
 *//*
package org.unitedstollutions.coreace;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.xml.sax.*;
import org.apache.*;
import org.apache.xalan.*;

*//**
 * @author Anastasiya
 *
 *//*
public class IfcXmlTransformToRdf {

	*//**
	 * @param args
	 *//*
	public static void main(String[] args) 
	throws MalformedURLException, SAXException {
        if (args.length != 2) {
            System.err.println("Usage:");
            System.err.println("  java " + IfcXmlTransformToRdf.class.getName(  )
                    + " xmlFileName xsltFileName");
            System.exit(1);
        }
        String xmlFileName = args[0];
        String xsltFileName = args[1];
        
        *//**String xmlFileName = "E:/sept2007/test_full_ifc.xml";
         * String xsltFileName = "E:/sept2007/test.xslt";
         *//*
        
 
        *//**
         * XML file name and XSLT file name are converted 
         * into system identifier values
         *//*
        String xmlSystemId = new File(xmlFileName).toURL().toExternalForm(  );
        String xsltSystemId = new File(xsltFileName).toURL().toExternalForm(  );
 
        *//**
         * instance of the XSLT processor is created
         *//*        
        org.apache.xalan.xslt.XSLTProcessor processor =
                org.apache.xalan.xslt.XSLTProcessorFactory.getProcessor(  );
 
        *//**creation of XSLTInputSource objects
         * 
         *//*
        org.apache.xalan.xslt.XSLTInputSource xmlInputSource =
                new org.apache.xalan.xslt.XSLTInputSource(xmlSystemId);
 
        org.apache.xalan.xslt.XSLTInputSource xsltInputSource =
                new org.apache.xalan.xslt.XSLTInputSource(xsltSystemId);
 
        *//**creation of instance of XSLTResultTarget  
         * it sends the result of the transformation to System.out
         *//*
        org.apache.xalan.xslt.XSLTResultTarget resultTree =
                new org.apache.xalan.xslt.XSLTResultTarget(System.out);
       
        *//** the transformation itself
         * 
         *//*
        processor.process(xmlInputSource, xsltInputSource, resultTree);


	}

}
*/