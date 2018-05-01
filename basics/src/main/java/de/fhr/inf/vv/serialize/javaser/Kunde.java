package de.fhr.inf.vv.serialize.javaser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
/**
 * Beispieldaten zum Serialisieren einer abhaengingen Klasse
 * @author Gerd Beneken
 *
 */
public class Kunde implements Serializable {

	/**
	 * Versionsnummer fuer die Serialisierung. 
	 * Serialisierte Objekte mit derselben Versionsnummer
	 * gelten als kompatibel.
	 */
	private static final long serialVersionUID = 1L;

	
	private String nummer;
    private String name;
    private Adresse firmenAdresse;
	private List<String> hobbies;

    public Kunde() {
    	this.hobbies = new ArrayList<String>();
    }
    
    public Kunde(String nummer, String name, Adresse firmenAdresse)
    {
    	this.nummer = nummer;
    	this.name = name;
    	this.firmenAdresse = firmenAdresse;
    	this.hobbies = new ArrayList<String>();
    }
 
    public Adresse getFirmenAdresse() {
		return firmenAdresse;
	}


	public void setFirmenAdresse(Adresse firmenAdresse) {
		this.firmenAdresse = firmenAdresse;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getNummer() {
		return nummer;
	}


	public void setNummer(String nummer) {
		this.nummer = nummer;
	}

    public List<String> getHobbies() {
    	return hobbies;
    }

    public void addHobby(String hobby) {
    	hobbies.add(hobby);
    }

	public String toString()
    {
    	StringBuffer buf = new StringBuffer();
    	buf.append("Kunde ");
    	buf.append(nummer);
    	buf.append(" :");
    	buf.append(name);
    	buf.append(",");
    	buf.append(firmenAdresse);
    	return buf.toString();
    }

	@Override
	public boolean equals(Object arg) {
		if (null == arg) return false;
		if (this == arg) return true;
		if (! (arg instanceof Kunde)) return false;
		
		Kunde k = (Kunde)arg;
		return k.nummer.equals(nummer);
	}

	@Override
	public int hashCode() {
		return nummer.hashCode();
	}	
}
