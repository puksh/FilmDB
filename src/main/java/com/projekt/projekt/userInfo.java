package com.projekt.projekt;

public class userInfo {
    String id,username,firstname,lastname,addressA,addressB,phone,postcode,city,email,theme,language,admin;

    public userInfo(String id, String username, String firstname, String lastname,
                    String addressA, String addressB, String phone, String postcode,
                    String city, String email, String theme, String language, String admin) {
        this.id = id;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.addressA = addressA;
        this.addressB = addressB;
        this.phone = phone;
        this.postcode = postcode;
        this.city = city;
        this.email = email;
        this.theme = theme;
        this.language = language;
        this.admin = admin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddressA() {
        return addressA;
    }

    public void setAddressA(String addressA) {
        this.addressA = addressA;
    }

    public String getAddressB() {
        return addressB;
    }

    public void setAddressB(String addressB) {
        this.addressB = addressB;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
