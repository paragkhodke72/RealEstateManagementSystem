package com.mycompany.newproject;

import java.util.List;

public class PropertyType extends Property {

    private String type;

    public PropertyType() {
	super();
	// TODO Auto-generated constructor stub
    }

    public PropertyType(String propertyName, String location, String owner, String status, String description,
	    String noOfBeds, String type, String squareFeetArea, String price, List<String> amenities) {
	super(propertyName, location, owner, status, description, noOfBeds, type, squareFeetArea, price, amenities);
	// TODO Auto-generated constructor stub
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
