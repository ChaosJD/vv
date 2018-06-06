package de.fhr.inf.vv.bla;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * Created by be on 28.05.2018.
 */
@Embeddable
public class Adresse {
    private String strasse;
    private String stadt;
    private String plz;

    @Override
    public String toString() {
        return "Adresse{" +
                "strasse='" + strasse + '\'' +
                ", stadt='" + stadt + '\'' +
                ", plz='" + plz + '\'' +
                '}';
    }

    public Adresse(String strasse, String stadt, String plz) {
        this.strasse = strasse;
        this.stadt = stadt;
        this.plz = plz;
    }

    Adresse() {}

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getStadt() {
        return stadt;
    }

    public void setStadt(String stadt) {
        this.stadt = stadt;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }
}
