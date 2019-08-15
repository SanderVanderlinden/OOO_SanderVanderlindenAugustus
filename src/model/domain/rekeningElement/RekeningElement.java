package model.domain.rekeningElement;

import model.domain.artikel.Artikel;

public class RekeningElement {

    private String code, omschrijving;
    private double verkoopprijs, totaleVerkoopPrijs;
    private int aantal;

    public RekeningElement(String code, String omschrijving, double verkoopprijs, int aantal) {
        setCode(code);
        setOmschrijving(omschrijving);
        setVerkoopprijs(verkoopprijs);
        setAantal(aantal);
    }

    public RekeningElement(Artikel gescandArtikel) {
        this(gescandArtikel.getCode(), gescandArtikel.getOmschrijving(), gescandArtikel.getVerkoopprijs(), 1);
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

    public double getVerkoopprijs() {
        return verkoopprijs;
    }

    private void setVerkoopprijs(double verkoopprijs) {
        this.verkoopprijs = verkoopprijs;
        setTotaleVerkoopPrijs();
    }

    public int getAantal() {
        return aantal;
    }

    private void setAantal(int aantal) {
        this.aantal = aantal;
        setTotaleVerkoopPrijs();
        //System.out.println(aantal);

    }

    public double getTotaleVerkoopPrijs() {
        return totaleVerkoopPrijs;
    }

    private void setTotaleVerkoopPrijs() {
        this.totaleVerkoopPrijs = verkoopprijs * aantal;
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
        RekeningElement rekeningElement = (RekeningElement) o;
        // field comparison
        return code.equals(rekeningElement.getCode());
    }

    @Override
    public String toString(){
        return this.getOmschrijving() + " (" + this.getCode() + "): " + this.getAantal();
    }

    public void verhoogAantal() {
        setAantal(aantal + 1);
        setTotaleVerkoopPrijs();
    }

    public void verlaagAantal() {
        setAantal(aantal - 1);
        setTotaleVerkoopPrijs();
        //System.out.println(aantal);
    }}

