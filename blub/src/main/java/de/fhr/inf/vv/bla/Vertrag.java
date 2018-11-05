package de.fhr.inf.vv.bla;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by be on 29.05.2018.
 */
@Entity
public class Vertrag {
    @Id
    @GeneratedValue
    private Long versicherungsnr;

    @ManyToOne(optional = false)
    @JsonIgnore
    private Kunde kunde;
    private String titel;

    public Vertrag(Kunde kunde, String titel) {
        this.kunde = kunde;
        this.titel = titel;
    }

    public Vertrag() {
    }

    public Long getVersicherungsnr() {
        return versicherungsnr;
    }

    public void setVersicherungsnr(Long versicherungsnr) {
        this.versicherungsnr = versicherungsnr;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}
