/**
 * 
 */
package org.unitedstollutions.c3r.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ruben
 *
 */
public class FileRWUtils {

	
	
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
	public String readFile(File file) {

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
	public void writeFile(File file, String dataString) {
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
