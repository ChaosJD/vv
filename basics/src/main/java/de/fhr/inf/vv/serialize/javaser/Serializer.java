package de.fhr.inf.vv.serialize.javaser;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;

public class Serializer {
	public void javaSerialize(List<Kunde> kunden, OutputStream out) {
		try (ObjectOutputStream oout = new ObjectOutputStream(out)) {
			oout.writeObject(kunden);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public List<Kunde> javaDeSerzalize(InputStream in) {
		try (ObjectInputStream oin = new ObjectInputStream(in)) {
			List<Kunde> result = (List<Kunde>) oin.readObject();
			return result;
		}
		catch(IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
