package de.fhr.inf.vv.serialize.xml;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class Serializer {
	public void xmlSerialize(List<Kunde> kunden, OutputStream out) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(KundenListe.class);
			Marshaller m = ctx.createMarshaller();			
			m.marshal(new KundenListe(kunden), out);
		}
		catch(JAXBException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Kunde> xmlDeSerzalize(InputStream in) {
		try {
			JAXBContext ctx = JAXBContext.newInstance(KundenListe.class);
			Unmarshaller u = ctx.createUnmarshaller();
			JAXBElement<KundenListe> kElement = u.unmarshal(new StreamSource(in), KundenListe.class);
			KundenListe liste = kElement.getValue();
			return liste.getKundenListe();
		}
		catch(JAXBException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
