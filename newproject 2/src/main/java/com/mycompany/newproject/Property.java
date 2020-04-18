package com.mycompany.newproject;


import java.security.acl.Owner;
import java.util.List;

public class Property {

    private String propertyName;
    private String location;
    private String owner;
    private String status;
    private String description;
    private String noOfBeds;
    private String type;
    private String squareFeetArea;
    private String price;
    private List<String> amenities;

    public Property() {
	super();
	// TODO Auto-generated constructor stub
    }



    public Property(String propertyName, String location, String owner, String status, String description,
	    String noOfBeds, String type, String squareFeetArea, String price, List<String> amenities) {
	super();
	this.propertyName = propertyName;
	this.location = location;
	this.owner=owner;
	this.status = status;
	this.description = description;
	this.noOfBeds = noOfBeds;
	this.type = type;
	this.squareFeetArea = squareFeetArea;
	this.price = price;
	this.amenities = amenities;
    }






    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(String noOfBeds) {
        this.noOfBeds = noOfBeds;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSquareFeetArea() {
        return squareFeetArea;
    }

    public void setSquareFeetArea(String squareFeetArea) {
        this.squareFeetArea = squareFeetArea;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }


}
