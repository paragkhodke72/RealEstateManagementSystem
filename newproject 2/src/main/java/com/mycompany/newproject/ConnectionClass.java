package com.mycompany.newproject;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

    public Connection connection;
    
    public Connection getConnection(){
	
	String dbName = "rems";
	String userName = "root";
	String password ="";
	
	try {
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    connection = DriverManager.getConnection("jdbc:mysql://localhost/rems?serverTimezone=UTC","root","Parag@123");
	    
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return connection;
    }
}
