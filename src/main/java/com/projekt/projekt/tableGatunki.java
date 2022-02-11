package com.projekt.projekt;

public class tableGatunki {
    String id, nazwa;

    public tableGatunki(String id, String nazwa) {
        this.id = id;
        this.nazwa = nazwa;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
}
