package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import java.security.acl.LastOwnerException;
import java.security.acl.NotOwnerException;
import java.security.acl.Owner;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class UserController implements Initializable{

    @FXML
    private TextField et_search;

    @FXML
    private ComboBox<String> menu_city;

    @FXML
    private ComboBox<String> menu_state;

    @FXML
    private ComboBox<String> menu_country;

    @FXML
    private Button btn_search;

    @FXML
    private TableView<Property> property_table;

    @FXML
    private TableColumn<Property, String> propertyName;

    @FXML
    private TableColumn<Property, String> propertyType;

    @FXML
    private TableColumn<Property, String> propertyAddress;

    @FXML
    private TableColumn<Property, String> noOfBedsCol;

    @FXML
    private TableColumn<Property, String> sqFtCol;

    @FXML
    private TableColumn<Property, String> ownerCol;

    @FXML
    private TableColumn<Property, String> statusCol;

    @FXML
    private TableColumn<Property, String> priceCol;

    @FXML
    private MenuButton menu_type;


    @FXML
    private Button switchToSelling;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button propertyButton;

    @FXML
    private Button propertyTypeButton;

    @FXML
    private Button myPropertiesButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button ownerButton;

    @FXML
    private Button inquiryButton;

    static List<Property> propertyList = new ArrayList<Property>();


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

    @FXML
    void searchProperty(ActionEvent event) {

	String name = et_search.getText().toString();
	String city = menu_city.getSelectionModel().getSelectedItem().toString();
	String state = menu_state.getSelectionModel().getSelectedItem().toString();
	String country = menu_country.getSelectionModel().getSelectedItem().toString();


	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT city,state,country,address,name,type,status,price,sq_feet,no_of_beds from properties where name=? or city=?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, name);
   		    preparedStmt1.setString(2, city);
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
   			String city1 =row.get(i).toString();
   			i++;
   			String state1=row.get(i).toString();
   			i++;
   			String country1=row.get(i).toString();
   			i++;
   			property.setLocation(row.get(i).toString()+","+city1+","+state1+","+country1);
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

   		    	propertyName.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyName"));
		    	propertyAddress.setCellValueFactory(new PropertyValueFactory<Property, String>("location"));
		    	propertyType.setCellValueFactory(new PropertyValueFactory<Property, String>("type"));
		    	sqFtCol.setCellValueFactory(new PropertyValueFactory<Property, String>("squareFeetArea"));
		    	statusCol.setCellValueFactory(new PropertyValueFactory<Property, String>("status"));
		    	priceCol.setCellValueFactory(new PropertyValueFactory<Property, String>("price"));
		    	noOfBedsCol.setCellValueFactory(new PropertyValueFactory<Property, String>("noOfBeds"));


   		    property_table.setItems(stateObservable);
   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	try {
	    setCountryBox();
	    setCityBox();
	    setStateBox();

	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }


    public void setCountryBox() throws SQLException{
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT name from countries";
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


   		 this.menu_country.setItems(row);


   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
    }

    public void setStateBox() throws SQLException{
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT name from states";
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


   		 this.menu_state.setItems(row);


   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
    }

    public void setCityBox() throws SQLException{

	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT name from cities";
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


   		 this.menu_city.setItems(row);


   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
    }


}
