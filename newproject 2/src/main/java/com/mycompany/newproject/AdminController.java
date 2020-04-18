package com.mycompany.newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    @FXML
    private Button dashboardButton;

    @FXML
    private Button propertyButton;

    @FXML
    private Button propertyTypeButton;

    @FXML
    private Button clientsButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button ownerButton;

    @FXML
    private Button cityButton;

    @FXML
    private Button stateButton;

    @FXML
    private Button profileBtn;

    @FXML
    private Button countryButton;

    @FXML
    private Label property_label;

    @FXML
    private Label country_label;

    @FXML
    private Label state_label;

    @FXML
    private Label city_label;

    @FXML
    private Label owners_label;

    @FXML
    private Label buyers_label;


    private static Stage secondaryStage;

    public void setStage(Stage secondaryStage) {
        this.secondaryStage = secondaryStage;
    }

    @FXML
    void city(ActionEvent event) throws IOException {
	App.setRoot("City");


    }

    @FXML
    void clientList(ActionEvent event) throws IOException {

	App.setRoot("ClientList");

    }

    @FXML
    void country(ActionEvent event) throws IOException {

	App.setRoot("Country");


    }

    @FXML
    void dashboard(ActionEvent event) throws IOException {

	App.setRoot("AdminDashboard");


    }

    @FXML
    void listProperties(ActionEvent event) throws IOException {

	App.setRoot("ListProperty");


    }

    @FXML
    void logout(ActionEvent event) throws IOException {

	App.setRoot("Login");


    }

    @FXML
    void ownerList(ActionEvent event) throws IOException {
	App.setRoot("OwnersList");
    }

    @FXML
    void propertyType(ActionEvent event) throws IOException {
	App.setRoot("PropertyTypes");
    }
    
    @FXML
    void profile(ActionEvent event) throws IOException {
	App.setRoot("AdminEditProfile");
    }

    @FXML
    void state(ActionEvent event) throws IOException {
	App.setRoot("State");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	setCityCount();
	setCountryCount();
	setStateCount();
        setTotalOwnersCount();
        setTotalPropertiesCount();
        setTotalBuyersCount();
    }
    
    public void setCountryCount(){
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM countries;";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStmt.executeQuery();
		    ObservableList row = FXCollections.observableArrayList();
		    while (resultSet.next()) {
	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }
                    }
		    country_label.setTextAlignment(TextAlignment.CENTER);
		    country_label.setText(row.get(0).toString());
		    connection.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
    }

    public void setCityCount(){
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM cities;";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStmt.executeQuery();
		    ObservableList row = FXCollections.observableArrayList();
		    while (resultSet.next()) {
	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }
                    }
		    city_label.setTextAlignment(TextAlignment.CENTER);
		    city_label.setText(row.get(0).toString());
		    connection.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
    }

    public void setStateCount(){
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM states;";
	PreparedStatement preparedStmt;
		try {
		    preparedStmt = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStmt.executeQuery();
		    ObservableList row = FXCollections.observableArrayList();
		    while (resultSet.next()) {
	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }
                    }
		    state_label.setTextAlignment(TextAlignment.CENTER);
		    state_label.setText(row.get(0).toString());
		    connection.close();
		}catch (Exception e) {
		    e.printStackTrace();
		}
    }
    
    
    public void setTotalPropertiesCount(){
	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM properties";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);

		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		    property_label.setTextAlignment(TextAlignment.CENTER);
		    property_label.setText(row.get(0).toString());

		    connection.close();

		}catch (Exception e) {
		    e.printStackTrace();
		}
    }

    public void setTotalBuyersCount(){
	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM users";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);

		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		   buyers_label.setTextAlignment(TextAlignment.CENTER);
		   buyers_label.setText(row.get(0).toString());

		    connection.close();

		}catch (Exception e) {
		    e.printStackTrace();
		}
    }

    public void setTotalOwnersCount(){
   	LoginController controller = new LoginController();
   	String id = controller.getId();
   	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	String query = "SELECT COUNT(*) FROM users";
   	      PreparedStatement preparedStmt;

   		try {
   		    preparedStmt = connection.prepareStatement(query);

   		    ResultSet resultSet = preparedStmt.executeQuery();

   		    ObservableList row = FXCollections.observableArrayList();

   		    while (resultSet.next()) {


   	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
   	                    row.add(resultSet.getString(i).toString());
   	                    System.out.println(row);
   	                }


   	             //   data.add(row);

   	            }

   		   owners_label.setTextAlignment(TextAlignment.CENTER);
   		   owners_label.setText(row.get(0).toString());

   		    connection.close();

   		}catch (Exception e) {
   		    e.printStackTrace();
   		}
       }
}
