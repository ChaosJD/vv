package de.fhr.inf.vv.serialize.xml;

 
public class Adresse  {

	private String strasse;
    private String plz;
    private String ort;
    
    
    public Adresse() {	
    }
    
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
	
}
