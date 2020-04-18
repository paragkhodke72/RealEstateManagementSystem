package com.mycompany.newproject;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class AdminProfileController implements Initializable{

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
    private Button saveProfileBtn;

    @FXML
    private PasswordField passwordfeild;

    @FXML
    private TextField lastNameFeild;

    @FXML
    private TextField ageFeild;

    @FXML
    private TextField emailFeild;

    @FXML
    private TextField firstNameFeild;

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
    void saveProfile(ActionEvent event) throws SQLException {



	String firstName = firstNameFeild.getText().toString();
	String lastName = lastNameFeild.getText().toString();
	int age = Integer.parseInt(ageFeild.getText().toString());
	String email = emailFeild.getText().toString();
	String password = passwordfeild.getText().toString();

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();



	String query = "update users set first_name=?, last_name=?, email=?, age=?, password=? where role=?";

	String query1 = "update user_details set email=?, password=? where role=?";

	PreparedStatement preparedStmt = connection.prepareStatement(query);

	PreparedStatement preparedStmt1 = connection.prepareStatement(query1);


	preparedStmt1.setString(1, email);
	preparedStmt1.setString(2, password);
	preparedStmt1.setString(3, "admin");


	preparedStmt.setString(1, firstName);
	preparedStmt.setString(2, lastName);
	preparedStmt.setString(3, email);
	preparedStmt.setInt(4, age);
	preparedStmt.setString(5, password);
	preparedStmt.setString(6, "admin");

	preparedStmt.executeUpdate();
	preparedStmt1.executeUpdate();
	connection.close();


	Alert a = new Alert(AlertType.NONE);
	 a.setAlertType(AlertType.INFORMATION);

         // set content text
         a.setContentText("Profile Edited Successful!\n Your UserName is "+firstName+"_"+lastName+"\n Password is "+password);

         // show the dialog
         a.show();
    }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

	LoginController controller = new LoginController();
	User user = controller.getUser();

	firstNameFeild.setText(user.getFirstName());
	lastNameFeild.setText(user.getLastName());
	ageFeild.setText(Integer.toString(user.getAge()));
	emailFeild.setText(user.getEmail());

	passwordfeild.setText(user.getPassword());

    }

}
