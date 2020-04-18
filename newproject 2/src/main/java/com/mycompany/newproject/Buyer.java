package com.mycompany.newproject;


import java.util.List;

public class Buyer extends User {

    private List<Property> propertyBought;
    private List<Inquiry> inquiries;

    public Buyer() {
	super();
	// TODO Auto-generated constructor stub
    }


    public Buyer(String firstName, String lastName, String email, int age, String role, String password,List<Property> propertyBought, List<Inquiry> inquiries) {
	super(firstName, lastName, email, age, role, password);

	this.propertyBought = propertyBought;
	this.inquiries = inquiries;

    }


    public List<Property> getPropertyBought() {
        return propertyBought;
    }


    public void setPropertyBought(List<Property> propertyBought) {
        this.propertyBought = propertyBought;
    }


    public List<Inquiry> getInquiries() {
        return inquiries;
    }


    public void setInquiries(List<Inquiry> inquiries) {
        this.inquiries = inquiries;
    }







}
