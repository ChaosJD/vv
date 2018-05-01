package de.fhr.inf.vv.streams.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Demonstration, wie Zeichenweise geschrieben und gelesen wird.
 * Hier werden Daten von einer Datei in eine andere kopiert
 * @author be
 *
 */
public class CopyBinaryFile {
	private static final String LOG_FILE = "pom.xml";
	private static final String DESTINATION = "pommes.xml";
	private static final int EOF = -1; // End Of File
	
	// Package private wg. Unit Test 
	void copyFiles(String source, String destination) {
		
		File sourceFile = new File(source);
		File destinationFile = new File(destination);

		try (InputStream in = new FileInputStream(sourceFile);
			 OutputStream out = new FileOutputStream(destinationFile)) {
			copyStreams(in, out);
		} 

		// Fehlerbehandlung
		catch(FileNotFoundException notfound) {
			// Wenn File Not Found gesondert zu behandeln
			System.err.println("[Error] " + notfound.getMessage());
		}
		catch(IOException ioe) {
			// Behandlung der restlichen Probleme
			System.err.println("[Error] " + ioe.getMessage());
		}
	}
	
	// Package private
	void copyStreams(InputStream source, OutputStream destination) throws IOException {
		int zeichen = 0;
		while ((zeichen = source.read()) != EOF) {
		   destination.write(zeichen);
		}		
	}
	
	/** 
	 * Achtung Programm ist kein Produktiver Code
	 * Es wird Zeichenweise gelesen und geschrieben
	 * Normalerweise wuerde man gepuffert lesen und schreiben
	 * 
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
		String sourceFileName = LOG_FILE;
		String destinationFileName = DESTINATION;
		
		// Zwei Kommandozeilen-Parameter
		// Source und Destination
		if (args.length == 2) {
			sourceFileName = args[0];
			destinationFileName = args[1];
		}

		CopyBinaryFile copy = new CopyBinaryFile();
		System.out.println("copy: " + sourceFileName + " to " + destinationFileName);
		copy.copyFiles(sourceFileName, destinationFileName);
	}
}
