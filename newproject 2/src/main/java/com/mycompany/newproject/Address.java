package com.mycompany.newproject;

public class Address {

    private String country;
    private String state;
    private String city;

    public Address() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Address(String country, String state, String city) {
	super();
	this.country = country;
	this.state = state;
	this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }





}
