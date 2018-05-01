package de.fhr.inf.vv.serialize.sp;


/**
 * Nachbau des Serializer Patterns gem. Riehle et al.
 * PLOP aus 1996
 * @author beneken
 *
 */
public interface MySerializable {
	public void readFrom(Reader r);
	public void writeTo(Writer w);
}
