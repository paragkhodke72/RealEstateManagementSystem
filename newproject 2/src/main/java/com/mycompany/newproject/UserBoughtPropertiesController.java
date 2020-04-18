package com.mycompany.newproject;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;


public class UserBoughtPropertiesController implements Initializable{

    @FXML
    private Button viewButton;

    @FXML
    private Button switchToSelling;

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
    private Button logoutButton1;

    @FXML
    private Button inquiryButton1;

    @FXML
    private Button ownerButton1;

    @FXML
    private Button myPropertiesButton1;

    @FXML
    private Button propertyTypeButton1;

    @FXML
    private Button propertyButton1;

    @FXML
    private Button dashboardButton1;

    static List<Property> propertyList = new ArrayList<Property>();
    private static Property property = new Property();

    @FXML
    void View(ActionEvent event) throws IOException {
	this.property = this.property_table.getSelectionModel().getSelectedItem();
	UserPropertyListController controller = new UserPropertyListController();
	controller.setProperty(this.property);
	System.out.println(property.getPropertyName());
	App.setRoot("ViewProperty");
    }

    @FXML
    void dashboard(ActionEvent event) throws IOException {
	App.setRoot("UserDashboard");


    }

    @FXML
    void inquiry(ActionEvent event) throws IOException {
	 App.setRoot("UserInquiries");

    }

    @FXML
    void listProperties(ActionEvent event) throws IOException {
	 App.setRoot("UserPropertyList");



    }

    @FXML
    void logout(ActionEvent event) throws IOException {

	App.setRoot("Login");

    }
    @FXML
    void myProperty(ActionEvent event) throws IOException {
	App.setRoot("UserBoughtProperties");


    }

    @FXML
    void ownerList(ActionEvent event) throws IOException {
	App.setRoot("EditProfile");


    }

    @FXML
    void propertyType(ActionEvent event) throws IOException {
	App.setRoot("UserPropertyTypes");

    }




    @FXML
    void switchToSelling(ActionEvent event) throws IOException {


	App.setRoot("SellerDashboard");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT city,state,country,address,name,type,status,price,sq_feet,no_of_beds from properties where buyer_id =?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, id);
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


    public Property getViewProperty(){
	return this.property;
    }

}
