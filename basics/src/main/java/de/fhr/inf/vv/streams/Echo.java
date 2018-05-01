package de.fhr.inf.vv.streams;

import java.io.IOException;
import java.util.Scanner;

/**
 * Vorlesung: Verteilte Verarbeitung
 * Ziel: Erstes echtes reaktives System  
 * @author Gerd Beneken
 *
 */
public class Echo  {
	private static final String EXIT = "EXIT";
	
	/** 
	 * Achtung Programm ist kein produktiver Code
	 * Es dient zur Demonstration, wie man Streams "zufuss" programmiert
	 * @param args Kommandozeilenparameter
	 */
	public static void main(String[] args) {
				
		System.out.println("Starte Echo");
		
		try {
			// Wir programmieren hier ein reaktives System!
			// Daher hier eine Endlosschleife
			// Endlosschleife kann unterbrochen werden
			Scanner scan = new Scanner(System.in);
			while (true) { 	
				String zeile = scan.nextLine();          // E
				if (zeile.startsWith(EXIT)) break; // V
				// Ausgabe mit OutputStream-Methoden
				System.out.write(zeile.getBytes());
				System.out.write('\n');
			}
			
			System.out.println("Echo korrekt beendet");
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Echo fehlerhaft beendet");
			// TODO: Gescheite Exception-Behandlung
		}
		finally {
			// Hier leer, da die Input / Output Streams der 
			// Konsole nicht freigegeben werden.
		}
	}
}
