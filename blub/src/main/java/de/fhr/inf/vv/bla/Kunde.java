package de.fhr.inf.vv.bla;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * Created by be on 16.05.2018.
 */

@Entity
public class Kunde {
    @Id
    @GeneratedValue
    private Long nummer;

    private String vorname;
    @Column(name="name")
    private String nachname;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "kunde",cascade = CascadeType.ALL)
    private List<Vertrag> vertraege;


    public Kunde(String vorname, String nachname) {
        this.vorname = vorname;
        this.nachname = nachname;
    }

    Kunde() {
    }

    @Override
    public String toString() {
        return "Kunde{" +
                "nummer=" + nummer +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                '}';
    }

    public Long getNummer() {
        return nummer;
    }

    public void setNummer(Long nummer) {
        this.nummer = nummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Vertrag> getVertraege() {
        return vertraege;
    }

    public void setVertraege(List<Vertrag> vertraege) {
        this.vertraege = vertraege;
    }
}
