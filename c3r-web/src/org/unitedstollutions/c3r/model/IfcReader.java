package org.unitedstollutions.c3r.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author yurchyshyna
 * 
 */
/**
 * @author user
 *
 */
/**
 * @author user
 * 
 */
public class IfcReader {

	private String ifcFile;
	private String ifcLocation;
	private String defaultIfcFileName = "defaultIfc.rdf";
	private String customIfcFileName = "customIfc.rdf"; //loaded file

	public IfcReader() {
		this.ifcLocation = "/data/annotations";
		this.ifcFile = this.ifcLocation + "/" + defaultIfcFileName;

	}

	public IfcReader(String ifcFilePath) {

		ifcLocation = ifcFilePath;
		this.ifcFile = this.ifcLocation + "/" + defaultIfcFileName;

	}

	/**
	 * @return the ifcFile
	 */
	public String getIfcFile() {
		return ifcFile;
	}

	/**
	 * @param ifcFile
	 *            the ifcFile to set
	 */
	public void setIfcFile(String ifcFile) {
		this.ifcFile = ifcFile;
	}

	/**
	 * @return the ifcLocation
	 */
	public String getIfcLocation() {
		return ifcLocation;
	}

	/**
	 * @param ifcLocation
	 *            the ifcLocation to set
	 */
	public void setIfcLocation(String ifcLocation) {
		this.ifcLocation = ifcLocation;
	}

	
	/**
	 *            set default file
	 */
	public void setDefaultIfcfile() {
		this.ifcFile = getIfcLocation() + "/" + this.defaultIfcFileName;
	}
	
	/**
	 * Reads in a specified file from a client and writes the contents to a
	 * custom IFC file.
	 * 
	 * @param ifcFile
	 */
	public void createCustomIfc(String ifcFile) {
		this.ifcFile = this.customIfcFileName;
		
		File ifcFile2read = new File(ifcFile);
		String processedIfcFile = readFile(ifcFile2read);
		File customIfcFile = new File(getIfcLocation() + "/"
				+ customIfcFileName);
		writeFile(customIfcFile, processedIfcFile);
	}

	/**
	 * @param uri
	 * @return
	 */
	public void readFromUri(String uri) {

		BufferedReader in = null;
		String inputLine = null;
		File writeFile = null;
		String wFileName = getIfcLocation() + "/" + this.customIfcFileName;

		try {

			URL url = new URL(uri);

			in = new BufferedReader(new InputStreamReader(url.openStream()));
			writeFile = new File(wFileName);
			BufferedWriter fw = new BufferedWriter(new FileWriter(writeFile
					.toString()));

			while ((inputLine = in.readLine()) != null) {
				// System.out.println(inputLine);
				fw.write(inputLine + "\n");
			}

			fw.close();
			in.close();

		} catch (MalformedURLException mue) {

		} catch (IOException ioe) {

		}

	}

	/**
	 * Reads the contents of a specified file and returns the contents in a long
	 * string.
	 * 
	 * NOTE: This is probably not the good way to do this. I creates a big
	 * string object. Might as well read and write to the end file directly or
	 * just return a StringBuffer instead of a big String
	 * 
	 * @param file
	 * @return String returns entire read in file in one string
	 */
	private String readFile(File file) {

		StringBuffer fileBuffer;
		String fileString = null;
		String line;

		try {
			FileReader in = new FileReader(file);
			BufferedReader dis = new BufferedReader(in);
			fileBuffer = new StringBuffer();

			while ((line = dis.readLine()) != null) {
				fileBuffer.append(line + "\n");
			}

			in.close();
			fileString = fileBuffer.toString();
		} catch (IOException e) {
			return null;
		}
		return fileString;
	}

	/**
	 * Writes a file with the contents of a passed in string.
	 * 
	 * @param File
	 *            file name to write to
	 * @param String
	 *            long string of read in file contents
	 */
	private void writeFile(File file, String dataString) {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(
					new FileWriter(file)));
			out.print(dataString);
			out.flush();
			out.close();
		} catch (IOException e) {
		}
	}

}
