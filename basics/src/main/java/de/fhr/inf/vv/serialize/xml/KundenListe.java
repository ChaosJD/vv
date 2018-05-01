package de.fhr.inf.vv.serialize.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Liste")
@XmlAccessorType(XmlAccessType.FIELD)
public class KundenListe {
	private List<Kunde> kundenListe = null;
	
	public KundenListe(List<Kunde> kundenListe) {
		this.kundenListe = kundenListe;
	}
	
	public KundenListe() {
		this.kundenListe = new ArrayList<>();
	}
	
	public List<Kunde> getKundenListe() {
		return kundenListe;
	}
}
