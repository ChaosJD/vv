package de.fhr.inf.vv.serialize.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="Kunde")
@XmlAccessorType(XmlAccessType.FIELD)
public class Kunde {
	
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
    	buf.append(", Hobbies:");
    	buf.append(Arrays.toString(hobbies.toArray()));
    	return buf.toString();
    }
	
	@Override
    public boolean equals(Object obj) {
		if (obj == null) return false;
		if (! (obj instanceof Kunde)) return false;
		if (obj == this) return true;
		
		Kunde k = (Kunde)obj;
		return k.nummer.equals(this.nummer);
    }

	@Override
    public int hashCode() {
	   return nummer.hashCode();
    }
    
}
