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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PropertyController  implements Initializable{

    @FXML
    private TableView<Property> property_table;

    @FXML
    private TableColumn<Property, String> nameCol;

    @FXML
    private TableColumn<Property, String> typeCol;

    @FXML
    private TableColumn<Property, String> addressCol;

    @FXML
    private TableColumn<Property, String> bedsCol;

    @FXML
    private TableColumn<Property, String> sq_feetCol;

    @FXML
    private TableColumn<Property, String> status;

    @FXML
    private TableColumn<Property, String> price;

    @FXML
    private Button viewPropertyBtn;

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

    static Property propertyView = new Property();

    public void setProperty(Property prop){
	this.propertyView = prop;
    }

    public Property getProperty(){
	return this.propertyView;
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

    @FXML
    void viewProperty(ActionEvent event) throws IOException {
	Property p = property_table.getSelectionModel().getSelectedItem();
	this.setProperty(p);
	App.setRoot("AdminViewProperty");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT city,state,country,address,name,type,status,price,sq_feet,no_of_beds from properties ";
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


   		    List<Property> properties = new ArrayList<Property>();

   		    for(int i =0 ; i<row.size();i++){
   			Property property = new Property();
   			String city =row.get(i).toString();
   			i++;
   			String state=row.get(i).toString();
   			i++;
   			String country=row.get(i).toString();
   			i++;
   			property.setLocation(row.get(i).toString()+","+city+","+state+","+country);
   			i++;
   			property.setPropertyName(row.get(i).toString());
   			i++;
   			property.setType(row.get(i).toString());
   			i++;

   			property.setStatus(row.get(i).toString());
   			i++;
   			property.setPrice(row.get(i).toString());
   			i++;
   			property.setSquareFeetArea(row.get(i).toString());
   			i++;
   			property.setNoOfBeds(row.get(i).toString());

   			properties.add(property);

   		    }

   		    ObservableList<Property> stateObservable =FXCollections.observableArrayList(properties);

		    	nameCol.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyName"));
		    	addressCol.setCellValueFactory(new PropertyValueFactory<Property, String>("location"));
		    	typeCol.setCellValueFactory(new PropertyValueFactory<Property, String>("type"));
		    	sq_feetCol.setCellValueFactory(new PropertyValueFactory<Property, String>("squareFeetArea"));
		    	status.setCellValueFactory(new PropertyValueFactory<Property, String>("status"));
		    	price.setCellValueFactory(new PropertyValueFactory<Property, String>("price"));
		    	bedsCol.setCellValueFactory(new PropertyValueFactory<Property, String>("noOfBeds"));


   		    property_table.setItems(stateObservable);
   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}


    }

}
