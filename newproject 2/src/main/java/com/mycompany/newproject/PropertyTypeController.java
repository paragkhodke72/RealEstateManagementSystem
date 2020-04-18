package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class PropertyTypeController implements Initializable{

    @FXML
    private Button addPropertyTypeBtn;

    @FXML
    private Button editPropertyTypeBtn;

    @FXML
    private Button deletePropertyType;

    @FXML
    private TableView<PropertyType> propertyTypeTable;

    @FXML
    private TableColumn<PropertyType, String> propertyTypeCol;

    @FXML
    private Pane addPropertyTypePane;

    @FXML
    private TextField eet_propertyType;

    @FXML
    private Button submitBtn;

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
    private Button reviewsButton;

    @FXML
    private Button countryButton;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button propertyButton;

    @FXML
    private Button propertyTypeButton;

    @FXML
    void addPropertyType(ActionEvent event) throws SQLException {

	addPropertyTypePane.setVisible(true);


	submitBtn.setOnAction(e->{

	    try {

		String name = eet_propertyType.getText().toString();
		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();



		String query = "insert into property_types (name)"
	        + " values (?)";

		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString(1, name);
		preparedStmt.execute();
		connection.close();

		Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("Property Type Added Successfully!");

	        // show the dialog
	        a.show();

	        addPropertyTypePane.setVisible(false);
	        populateTable();
	        eet_propertyType.setText("");

	    } catch (Exception e2) {
		e2.printStackTrace();
	    }

	});


    }

    @FXML
    void deletePropertyType(ActionEvent event) throws SQLException {


	PropertyType propType = new PropertyType();
	propType = propertyTypeTable.getSelectionModel().getSelectedItem();
	String propertyType = propType.getType();
	String query = "DELETE FROM property_types WHERE name = ?";

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, propertyType);
		    preparedStmt.execute();
		}catch (Exception e) {
		    e.printStackTrace();
		}

		 Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("Property Type Deleted Successfully!");

	        // show the dialog
	        a.show();

		populateTable();
    }

    @FXML
    void editPropertyType(ActionEvent event) {

	addPropertyTypePane.setVisible(true);
	PropertyType propertyType = propertyTypeTable.getSelectionModel().getSelectedItem();
	eet_propertyType.setText(propertyType.getType());

	submitBtn.setOnAction(e->{
	    try {


		ConnectionClass connectionClass = new ConnectionClass();
		Connection connection = connectionClass.getConnection();



		String query = "update property_types set name = ? where name = ?";
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, eet_propertyType.getText().toString());
		      preparedStmt.setString(2, propertyType.getType());

		      // execute the java preparedstatement
		      preparedStmt.executeUpdate();

		      connection.close();

		Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("Property Type Updated Successfully!");

	        // show the dialog
	        a.show();

	        addPropertyTypePane.setVisible(false);
	        populateTable();
	        eet_propertyType.setText("");

	    } catch (Exception e2) {
		e2.printStackTrace();
	    }

	});
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

	addPropertyTypePane.setVisible(false);

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
   	 String query1 = "SELECT name from property_types";
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



   		 List<PropertyType> types = new ArrayList<PropertyType>();

		    for(int i =0 ; i<row.size();i++){
			PropertyType type = new PropertyType();

			type.setType(row.get(i).toString());
			types.add(type);

		    }

		    ObservableList<PropertyType> propertyTypeObservable =FXCollections.observableArrayList(types);


		    	propertyTypeCol.setCellValueFactory(new PropertyValueFactory<PropertyType, String>("type"));


   		        propertyTypeTable.setItems(propertyTypeObservable);
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
       }
}
