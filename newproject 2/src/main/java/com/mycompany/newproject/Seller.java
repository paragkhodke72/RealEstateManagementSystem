package com.mycompany.newproject;

import java.util.List;

public class Seller extends User{


    private List<Inquiry> inquiry;
    private List<Property> propertyOnSale;


    public Seller() {
	super();
	// TODO Auto-generated constructor stub
    }


    public Seller(List<Inquiry> inquiry, List<Property> propertyOnSale) {
	super();
	this.inquiry = inquiry;
	this.propertyOnSale = propertyOnSale;
    }


    public List<Inquiry> getInquiry() {
        return inquiry;
    }


    public void setInquiry(List<Inquiry> inquiry) {
        this.inquiry = inquiry;
    }


    public List<Property> getPropertyOnSale() {
        return propertyOnSale;
    }


    public void setPropertyOnSale(List<Property> propertyOnSale) {
        this.propertyOnSale = propertyOnSale;
    }



}
