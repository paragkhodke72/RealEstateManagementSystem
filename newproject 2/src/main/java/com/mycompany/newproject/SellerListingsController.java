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


import javafx.beans.property.SetProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class SellerListingsController implements Initializable {

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
    private Button editPropertyBtn;

    @FXML
    private Button viewPropertyBtn;

    @FXML
    private Button deletePropertyBtn;

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

    static Property propertyView = new Property();

    public void setProperty(Property prop){
	this.propertyView = prop;
    }

    public Property getProperty(){
	return this.propertyView;
    }


    @FXML
    void deleteProperty(ActionEvent event) {
	Property prop = new PropertyType();
	prop = property_table.getSelectionModel().getSelectedItem();
	String property = prop.getPropertyName();
	String query = "DELETE FROM properties WHERE name = ?";

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, property);
		    preparedStmt.execute();
		    Alert a = new Alert(AlertType.NONE);
			 a.setAlertType(AlertType.INFORMATION);

		        // set content text
		        a.setContentText("Property Deleted Successfully!");

		        // show the dialog
		        a.show();
		}catch (Exception e) {
		    e.printStackTrace();
		}



		property_table.refresh();
    }

    @FXML
    void editProperty(ActionEvent event) throws IOException {
	Property property = property_table.getSelectionModel().getSelectedItem();
	this.setProperty(property);
	App.setRoot("EditProperty");
    }

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


    @FXML
    void viewProperty(ActionEvent event) throws IOException {
	Property p = property_table.getSelectionModel().getSelectedItem();
	this.setProperty(p);
	App.setRoot("ViewSellerProperty");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT city,state,country,address,name,type,status,price,sq_feet,no_of_beds from properties where owner_id=? and status!=?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, id);
   		    preparedStmt1.setString(2, "sold");
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
