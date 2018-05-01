package de.fhr.inf.vv.serialize.json;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerialisierungTest {
	private List<Kunde> kunden = null;

	@Before
	public void setUp() throws Exception {
		Adresse adr = new Adresse("Schuetteweg 3", "35678", "Clausthal-Zellerfeld");
		kunden = new ArrayList<Kunde>();
		kunden.add(new Kunde("4711", "Hugo Habicht", adr));
		kunden.add(new Kunde("0815", "Willi Winzig", adr));
		kunden.add(new Kunde("1234", "Harry Hurtig", new Adresse("Hauptstr. 30", "02497", "Westostwestfahlen")));

		kunden.get(0).addHobby("Fussball");
		kunden.get(0).addHobby("Handball");
		kunden.get(0).addHobby("Kopfball");
		kunden.get(1).addHobby("Kochen");
		kunden.get(1).addHobby("Backen");
		kunden.get(2).addHobby("Schach");

	}

	@Test
	public void testSerialisierung() {
		Serializer s = new Serializer();

		try (OutputStream out = new FileOutputStream("kunden.json")) {
			s.jsonSerialize(kunden, out);
		} catch (IOException ex) {
			ex.printStackTrace();
			fail();
		}

		try (InputStream in = new FileInputStream("kunden.json")) {
			List<Kunde> gelesen = s.jsonDeSerzalize(in);
			assertThat(gelesen, equalTo(kunden));
		} catch (IOException ex) {
			ex.printStackTrace();
			fail();
		}
	}
}
