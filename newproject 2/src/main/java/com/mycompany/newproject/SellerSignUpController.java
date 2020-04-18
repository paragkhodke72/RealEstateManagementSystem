package com.mycompany.newproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SellerSignUpController implements Initializable {

    @FXML
    private ImageView slideShow;
    @FXML
    private PasswordField passwordfeild;

    @FXML
    private TextField lastNameFeild;

    @FXML
    private TextField ageFeild;

    @FXML
    private TextField emailFeild;

    @FXML
    private Button signupBtn;

    @FXML
    private Button backBtn;

    @FXML
    private TextField firstNameFeild;

    public int count = 0;
    @FXML
    void goBack(ActionEvent event) throws IOException {

	App.setRoot("OwnerSignin");
    }

    @FXML
    void signUp(ActionEvent event) throws SQLException {
	String firstName = firstNameFeild.getText().toString();
	String lastName = lastNameFeild.getText().toString();
	String age = ageFeild.getText().toString();
	String email = emailFeild.getText().toString();
	String password = passwordfeild.getText().toString();

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();



	String query = "insert into users (first_name, last_name, email, age, role,password)"
        + " values (?, ?, ?, ?, ?,?)";

	String query1 = "insert into user_details (email, password, role)"
	        + " values (?, ?, ?)";

	PreparedStatement preparedStmt = connection.prepareStatement(query);

	PreparedStatement preparedStmt1 = connection.prepareStatement(query1);


	preparedStmt1.setString(1, email);
	preparedStmt1.setString(2, password);
	preparedStmt1.setString(3, "seller");


	preparedStmt.setString(1, firstName);
	preparedStmt.setString(2, lastName);
	preparedStmt.setString(3, email);
	preparedStmt.setString(4, age);
	preparedStmt.setString(5, "seller");
	preparedStmt.setString(6, password);

	preparedStmt.execute();
	preparedStmt1.execute();
	connection.close();


	Alert a = new Alert(AlertType.NONE);
	 a.setAlertType(AlertType.INFORMATION);

         // set content text
         a.setContentText("SignUp Successful!\n Your UserName is "+firstName+"_"+lastName);

         // show the dialog
         a.show();

         try
         {
             Thread.sleep(2000);
         }
         catch(InterruptedException ex)
         {
             Thread.currentThread().interrupt();
         }


         a.close();
         try {
	    App.setRoot("OwnerSignin");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	firstNameFeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color:  #0078D7");
	lastNameFeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color:  #0078D7");
	emailFeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color:  #0078D7");
	ageFeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color:  #0078D7");
	passwordfeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color:  #0078D7");


	   Image image = new Image("com/mycompany/newproject/images/background9.jpg");
	   Image image1 = new Image("com/mycompany/newproject/images/background12.jpg");
	   Image image2= new Image("com/mycompany/newproject/images/background23.jpg");
	   Image image3 = new Image("com/mycompany/newproject/images/background24.jpg");
	   Image image4 = new Image("com/mycompany/newproject/images/background25.jpg");

	List<Image> imageArrayList = new ArrayList<Image>();
	imageArrayList.add(image1);
	imageArrayList.add(image);
	imageArrayList.add(image2);
	imageArrayList.add(image3);
	imageArrayList.add(image4);





	// then in your method

	long delay = 2000; //update once per 2 seconds.
	new Timer().schedule(new TimerTask() {

	    @Override
	    public void run() {
		slideShow.setImage(imageArrayList.get(count++));
	        if (count >= imageArrayList.size()) {
	            count = 0;
	        }
	    }
	}, 0, delay);

    }

}
