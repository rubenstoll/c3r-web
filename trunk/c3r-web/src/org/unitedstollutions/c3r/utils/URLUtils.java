package org.unitedstollutions.c3r.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class URLUtils {

	Logger logger = Logger.getLogger(URLUtils.class);

	// simple, fast and thread safe singleton
	public final static URLUtils URLUTIL_INSTANCE = new URLUtils();

	private URLUtils() {
		// This is so it cannot be instantiated nor sub-classed

		// The following is learning material:

		// Once a constructor is private it cannot be called by subclasses thus
		// cannot be sub-classed.

		// If this constructor were protected it could be accessed by
		// sub-classes or classes in
		// the same package because protected constructors can be called by
		// subclasses and by
		// other classes in the same package.
	}

	/**
	 * @param fileUrl
	 * @return
	 */
	public File readFetchFile(String fileUrl) {

		BufferedReader in = null;
		String inputLine = null;
		File urlTail = null;

		try {

			URL url = new URL(fileUrl);

			String localFile = new File(fileUrl).getName();

			in = new BufferedReader(new InputStreamReader(url.openStream()));

			urlTail = new File(localFile);

			BufferedWriter fw = new BufferedWriter(new FileWriter(urlTail
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

		return urlTail;

	}

	/**
	 * @param source
	 * @return
	 */
	public void readFromUri(String source, String destination)
			throws IOException, MalformedURLException {

		BufferedReader in = null;
		BufferedWriter fw = null;
		String inputLine = null;
		File writeFile = null;
		String wFileName = destination;

		writeFile = new File(wFileName);
		fw = new BufferedWriter(new FileWriter(writeFile.toString()));
		URL url = new URL(source);
		in = new BufferedReader(new InputStreamReader(url.openStream()));

		if (in != null) {
			while ((inputLine = in.readLine()) != null) {
				fw.write(inputLine + "\n");
			}
		}

		// close buffers
		if (in != null) {
			in.close();
		}
		if (fw != null) {
			fw.close();
		}

	}

	/**
	 * @param source
	 * @return
	 */
	public void rFromUri(String source, String destination)
			throws MalformedURLException, IOException {

		ArrayList<String> data = readUri(source);
		this.writeFile(data, destination);

	}

	private void writeFile(ArrayList<String> data, String destination)
			throws IOException {

		BufferedWriter fw = null;
		File writeFile = null;
		String wFileName = destination;

		writeFile = new File(wFileName);
		logger.debug("writing to file: " + writeFile.getName());
		fw = new BufferedWriter(new FileWriter(writeFile.toString()));

		for (String line : data) {
			fw.write(line + "\n");
		}

		if (fw != null) {
			fw.close();
		}

		logger.debug("done writing");

	}

	private ArrayList<String> readUri(String uri) throws IOException,
			MalformedURLException {

		BufferedReader in = null;
		String inputLine = null;
		ArrayList<String> uf = new ArrayList<String>();

		logger.debug("reading from: " + uri);

		URL url = new URL(uri);
		in = new BufferedReader(new InputStreamReader(url.openStream()));

		if (in != null) {
			while ((inputLine = in.readLine()) != null) {
				uf.add(inputLine);
			}
		}

		if (in != null) {
			in.close();
		}

		logger.debug("done reading!");

		return uf;
	}

}
