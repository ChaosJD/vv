package de.fhr.inf.vv.serialize.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import com.google.gson.Gson;

public class Serializer {
	public void jsonSerialize(List<Kunde> kunden, OutputStream out) {
		// Hier wird ein Writer verwendet, da ein Text geschrieben wird
		try (OutputStreamWriter outwriter = new OutputStreamWriter(out)) {
			Gson parser = new Gson();
			String json = parser.toJson(kunden);
			outwriter.write(json);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Kunde> jsonDeSerzalize(InputStream in) {
		// Hier wird direkt ein BufferedReader verwendet
		// Reader ist in Java fuer das Lesen von UTF8/16 Texten gedacht
		try (BufferedReader inreader = 
				new BufferedReader(new InputStreamReader(in))) {
			Gson parser = new Gson();
			
			// Leider speichert JSON den Typ der Liste und ihrer Objekte nicht mit
			// daher muss man hier den Typ explizit mit angeben
			Type listType = new TypeToken<List<Kunde>>() {}.getType();
			List<Kunde> result = parser.fromJson(inreader, listType);
			return result;
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
