package com.projekt.projekt;

public class tableUsers {
    private int id, admin;
    private String username;
    private String firstname;
    private String lastname;
    private String addressA;
    private String addressB;
    private String phone;
    private String postcode;
    private String city,email;
    private String password;
    private String theme;
    private String language;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public tableUsers(int id, String username, String firstname, String lastname,
                      String addressA, String addressB, String phone, String postcode,
                      String city, String email, String password, String theme, String language, int admin) {
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
        this.password = password;
        this.theme = theme;
        this.language = language;
        this.admin = admin;
    }
    public tableUsers(){
        this.id = 0;
        this.username = " ";
        this.firstname = " ";
        this.lastname = " ";
        this.addressA = " ";
        this.addressB = " ";
        this.phone = " ";
        this.postcode = " ";
        this.city = " ";
        this.email = " ";
        this.password = " ";
        this.theme = " ";
        this.language = " ";
        this.admin = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
}
