package de.fhr.inf.vv.serialize.sp;


/**
 * Nachbau Serializer Pattern (nach Riele)
 * @author be
 *
 */
public interface Reader {
    public MySerializable readObject();
    public String readString();
}
