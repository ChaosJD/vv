package de.fhr.inf.vv.serialize.sp;


/**
 * Nachbau Serializer Pattern (nach Riele)
 * @author be
 */
public interface Writer {
    public void writeObject(MySerializable object);
    public void writeString(String s);
}
