package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

//import application.Main;
//import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class SellerController implements Initializable{


    @FXML
    private Label owners_label;

    @FXML
    private Label buyers_label;

    @FXML
    private Label total_ListingsLabel;

    @FXML
    private Label property_soledLabel;

    @FXML
    private Button switchToBuyingBtn;

    @FXML
    private Button soledPropertiesBtn;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button myPropertiesButton;

    @FXML
    private Button profileBtn;

    @FXML
    private Button sellPropertyBtn;

    @FXML
    void dashboard(ActionEvent event) throws IOException {

	App.setRoot("SellerDashboard");
    }

    @FXML
    void myProperty(ActionEvent event) throws IOException {
	App.setRoot("SellerListings");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
	App.setRoot("SellerEditProfile");
    }

    @FXML
    void sellProperty(ActionEvent event) throws IOException {

	App.setRoot("AddProperty");
    }

    @FXML
    void soldProperties(ActionEvent event) throws IOException {
	App.setRoot("SoldProperties");
    }

    @FXML
    void switchToBuying(ActionEvent event) throws IOException {
	App.setRoot("UserDashboard");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	setPropertyListingCount();
	setSoldPropertyCount();
	setTotalPropertiesCount();
	setTotalBuyersCount();
	setTotalOwnersCount();
    }

    public void setPropertyListingCount(){

	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM properties where owner_id=? and status!=?";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, id);
		    preparedStmt.setString(2, "sold");
		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		    total_ListingsLabel.setTextAlignment(TextAlignment.CENTER);
		    total_ListingsLabel.setText(row.get(0).toString());

		    connection.close();

		}catch (Exception e) {
		    e.printStackTrace();
		}
    }


    public void setSoldPropertyCount(){
	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "SELECT COUNT(*) FROM properties where owner_id=? and status=?";
	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, id);
		    preparedStmt.setString(2, "sold");
		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		    property_soledLabel.setTextAlignment(TextAlignment.CENTER);
		    property_soledLabel.setText(row.get(0).toString());

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

		    total_ListingsLabel.setTextAlignment(TextAlignment.CENTER);
		    total_ListingsLabel.setText(row.get(0).toString());

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
