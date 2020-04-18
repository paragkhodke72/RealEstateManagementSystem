package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class OwnerListController implements Initializable{

    @FXML
    private TableView<User> ownerTable;

    @FXML
    private TableColumn<User, String> firstNamecol;

    @FXML
    private TableColumn<User, String> lastNameCol;

    @FXML
    private TableColumn<User, String> ageCol;

    @FXML
    private TableColumn<User, String> emailCol;

    @FXML
    private Button sendInquiryBtn;

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
    void sendInquiry(ActionEvent event) {

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {



	List<User> userList = new ArrayList<User>();
	String role="user";
	 String query1 = "select * from users where role=?";

	  ConnectionClass connectionClass1 = new ConnectionClass();
	    Connection connection1 = connectionClass1.getConnection();
	    PreparedStatement preparedStmt1;

	    try {
		preparedStmt1 = connection1.prepareStatement(query1);
		preparedStmt1.setString(1, role);

		ResultSet resultSet1 = preparedStmt1.executeQuery();

		  ObservableList row = FXCollections.observableArrayList();

		    while (resultSet1.next()) {


	                for (int i = 1; i <= resultSet1.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet1.getString(i).toString());
	                    System.out.println(row);
	                }
		    }

		    for(int i =1 ; i<=row.size();i++){

			User user = new User();
			user.setFirstName(row.get(i).toString());
			i++;
			user.setLastName(row.get(i).toString());
			i++;
			user.setEmail(row.get(i).toString());
			i++;
			user.setAge(Integer.parseInt(row.get(i).toString()));
			i++;
			user.setRole(row.get(i).toString());
			i++;
			user.setPassword(row.get(i).toString());
			i++;
			userList.add(user);

		    }
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	 firstNamecol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
	 lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
	 ageCol.setCellValueFactory(new PropertyValueFactory<User, String>("age"));
	 emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));



	ObservableList<User> userObservableList = FXCollections.observableArrayList(userList);
	this.ownerTable.setItems(userObservableList);
    }

}
