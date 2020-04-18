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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class UserPropertyTypeController implements Initializable{

    @FXML
    private TableView<PropertyType> property_table;

    @FXML
    private TableColumn<PropertyType,String> propertyName;

    @FXML
    private Button switchToSelling;

    @FXML
    private Button logoutButton;

    @FXML
    private Button inquiryButton;

    @FXML
    private Button ownerButton;

    @FXML
    private Button myPropertiesButton;

    @FXML
    private Button propertyTypeButton;

    @FXML
    private Button propertyButton;

    @FXML
    private Button dashboardButton;

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
    public void initialize(URL arg0, ResourceBundle arg1) {
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


		    propertyName.setCellValueFactory(new PropertyValueFactory<PropertyType, String>("type"));


   		        property_table.setItems(propertyTypeObservable);
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

    }

}
