package model.domain.artikel;

import java.util.Comparator;
import java.util.Objects;

public class Artikel implements Comparable<Artikel> {

    private String code, omschrijving, artikelgroep;
    private double verkoopprijs;
    private int actueleVoorraad;

    public Artikel(String code, String omschrijving, String artikelgroep, double verkoopprijs, int actueleVoorraad) {
        setCode(code);
        setOmschrijving(omschrijving);
        setArtikelgroep(artikelgroep);
        setVerkoopprijs(verkoopprijs);
        setActueleVoorraad(actueleVoorraad);
    }

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    private void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public String getArtikelgroep() {
        return artikelgroep;
    }

    private void setArtikelgroep(String artikelgroep) {
        this.artikelgroep = artikelgroep;
    }

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    private void setVerkoopprijs(double verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
    }

    public int getActueleVoorraad() {
        return actueleVoorraad;
    }

    private void setActueleVoorraad(int actueleVoorraad) {
        this.actueleVoorraad = actueleVoorraad;
    }

    @Override
    public String toString() {
        return this.getOmschrijving() + " (" + this.getCode() + ")";
    }

    @Override
    public int compareTo(Artikel artikel) {
        return (this.omschrijving).compareTo(artikel.omschrijving);
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        Artikel artikel = (Artikel) o;
        // field comparison
        return code.equals(artikel.getCode());
    }
}
