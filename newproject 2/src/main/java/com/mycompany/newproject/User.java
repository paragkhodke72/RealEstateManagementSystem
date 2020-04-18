package com.mycompany.newproject;


public class User {


    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String role;
    private String password;


    public User() {
	super();
    }




    public User(String firstName, String lastName, String email, int age, String role, String password) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.age = age;
	this.role = role;
	this.password = password;
    }



    public String getPassword() {
        return password;
    }




    public void setPassword(String password) {
        this.password = password;
    }




    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }


}
