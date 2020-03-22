package com.paolamonteiro.core.agenda.model;

public class Contact {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(Integer id, String name, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\n\nContact: " + id +
                ",\nname: " + name +
                ",\nphoneNumber: " + phoneNumber +
                ",\nemail: " + email;
    }
}
