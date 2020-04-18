package com.mycompany.newproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ViewPropertyController implements Initializable{


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

    @FXML
    private TextField propertyNameField;

    @FXML
    private TextField propertyAddressField;

    @FXML
    private TextField propertyPriceField;

    @FXML
    private TextField PropertyBedroomField;

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
	App.setRoot("UserPropertyList");


    }

    @FXML
    void propertyType(ActionEvent event) throws IOException {
	App.setRoot("UserPropertyTypes");

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


	UserPropertyListController userController = new UserPropertyListController();


	viewProperty(userController.getViewProperty());
    }


    public void viewProperty(Property property){

	this.propertyNameField.setText(property.getPropertyName());
    }
}
