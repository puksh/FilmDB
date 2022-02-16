package com.projekt.projekt;

public class tableFilmy {
    private String id;
    private String tytul;
    private String rok;
    private String jezyk;
    private String cena;
    private String gatunek;
    private String rezyser;
    private String aktor;

    public tableFilmy(String id, String tytul, String rok, String jezyk, String cena, String gatunek, String rezyser, String aktor) {
        this.id = id;
        this.tytul = tytul;
        this.rok = rok;
        this.jezyk = jezyk;
        this.cena = cena;
        this.gatunek = gatunek;
        this.rezyser = rezyser;
        this.aktor = aktor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getRok() {
        return rok;
    }

    public void setRok(String rok) {
        this.rok = rok;
    }

    public String getJezyk() {
        return jezyk;
    }

    public void setJezyk(String jezyk) {
        this.jezyk = jezyk;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public String getRezyser() {
        return rezyser;
    }

    public void setRezyser(String rezyser) {
        this.rezyser = rezyser;
    }

    public String getAktor() {
        return aktor;
    }

    public void setAktor(String aktor) {
        this.aktor = aktor;
    }
}
