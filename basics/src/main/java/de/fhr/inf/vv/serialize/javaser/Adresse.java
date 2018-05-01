package de.fhr.inf.vv.serialize.javaser;

import java.io.Serializable;

/**
 * Beispieldaten zum Serialisieren einer abhaengingen Klasse
 * @author Gerd Beneken
 *
 */
public class Adresse implements Serializable  {
 
	/**
	 * Versionsnummer fuer die Serialisierung. 
	 * Serialisierte Objekte mit derselben Versionsnummer
	 * gelten als kompatibel.
	 */
	private static final long serialVersionUID = 1L;

	private String strasse;
    private String plz;
    private String ort;
    
        
    public Adresse(String strasse, String plz, String ort) {
    	this.strasse = strasse;
    	this.plz = plz;
    	this.ort = ort;
    }

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
    public String toString() {
    	StringBuffer buf = new StringBuffer();
    	buf.append("Adresse=");
    	buf.append(strasse); 
    	buf.append(",");
    	buf.append(plz);
    	buf.append(" ");
    	buf.append(ort);
    	return buf.toString();
    }

	@Override
	public boolean equals(Object arg) {
		if (null == arg) return false;
		if (this == arg) return true;
		if (!(arg instanceof Adresse)) return false;
		
		Adresse a = (Adresse) arg;
		boolean result = 
			a.strasse.equals(strasse) &&
			a.plz.equals(plz) &&
			a.ort.equals(ort);
		
		return result;
	}

	@Override
	public int hashCode() {
		return strasse.hashCode() + plz.hashCode() + ort.hashCode();
	}
	
}
