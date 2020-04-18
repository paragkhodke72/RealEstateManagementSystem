package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
import com.mysql.cj.protocol.Resultset;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;



public class StateController implements Initializable {

    @FXML
    private TableColumn<Address, String> countryCol;

    @FXML
    private TableColumn<Address, String> stateCol;
    @FXML
    private Button deleteStateBtn;

    @FXML
    private TableView<Address> stateTable;

    @FXML
    private Button addStateBtn;

    @FXML
    private TextField et_addState;

    @FXML
    private ComboBox<String> country_box;

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
    private Button dashboardButton;

    @FXML
    private Button propertyButton;

    @FXML
    private Button propertyTypeButton;

    private ObservableList data;

    @FXML
    void addState(ActionEvent event) throws SQLException {


	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	String name = country_box.getSelectionModel().getSelectedItem().toString();
	 PreparedStatement preparedStmt = null;
	 String query = "select id from countries where name = ?";

	String country_id;


		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1,name);
		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		    country_id = row.get(0).toString();

		    String state = et_addState.getText().toString();

		    String query1 = "insert into states (country_id, name)"
			        + " values (?, ?)";

		    PreparedStatement preparedStatement1;
		    preparedStatement1 = connection.prepareStatement(query1);
		    preparedStatement1.setString(1,country_id);
		    preparedStatement1.setString(2, state);
		    preparedStatement1.execute();

		   connection.close();

		   Alert a = new Alert(AlertType.NONE);
			 a.setAlertType(AlertType.INFORMATION);

		        // set content text
		        a.setContentText("State Added Successfully!");

		        // show the dialog
		        a.show();

		        et_addState.setText("");
		        country_box.setAccessibleText("Select Country");

		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		populateTable();

    }
    @FXML
    void deleteState(ActionEvent event) throws SQLException {

	Address state = new Address();
	state = stateTable.getSelectionModel().getSelectedItem();
	String stateName = state.getState();
	String query = "DELETE FROM states WHERE name = ?";

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, stateName);
		    preparedStmt.execute();
		}catch (Exception e) {
		    e.printStackTrace();
		}

		 Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("State Deleted Successfully!");

	        // show the dialog
	        a.show();

		populateTable();
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
    public void initialize(URL arg0, ResourceBundle arg1) {
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	 String query = "select name from countries";
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
		    country_box.setItems(row);
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		try {
		    connection.close();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}


		try {
		    populateTable();
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
    }



    public void populateTable() throws SQLException{

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	 String query1 = "SELECT states.name,countries.name FROM states INNER JOIN countries ON states.country_id = countries.id;";
	      PreparedStatement preparedStmt1;

		try {
		    preparedStmt1 = connection.prepareStatement(query1);
		    ResultSet resultSet = preparedStmt1.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }


		    List<Address> states = new ArrayList<Address>();

		    for(int i =0 ; i<row.size();i++){
			Address state = new Address();
			state.setState(row.get(i).toString());
			i++;
			state.setCountry(row.get(i).toString());
			states.add(state);

		    }

		    ObservableList<Address> stateObservable =FXCollections.observableArrayList(states);;

		    	countryCol.setCellValueFactory(new PropertyValueFactory<Address, String>("country"));
			stateCol.setCellValueFactory(new PropertyValueFactory<Address, String>("state"));

		    stateTable.setItems(stateObservable);
		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		connection.close();
    }

}
