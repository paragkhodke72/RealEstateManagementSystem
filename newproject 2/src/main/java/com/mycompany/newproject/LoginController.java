package com.mycompany.newproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class LoginController implements Initializable{

    @FXML
    private Button forgotPasswordBtn;

    @FXML
    private ImageView slideshow;

    @FXML
    private Label success_label;

    @FXML
    private Label error_label;
    @FXML
    private Button signupBtn;


    @FXML
    private Button loginButton;

    @FXML
    private TextField usernamefeild;

    @FXML
    private PasswordField passwordfeild;

    private static User user = new User();
    private static String user_id;
    public int count = 0;

    @FXML
    void login(ActionEvent event) throws IOException, InterruptedException{

	String userName = usernamefeild.getText().toString();
	String password = passwordfeild.getText().toString();
	 String query1 = "select * from users where email = ? and password = ?";

	  ConnectionClass connectionClass1 = new ConnectionClass();
	    Connection connection1 = connectionClass1.getConnection();
	    PreparedStatement preparedStmt1;

	    try {
		preparedStmt1 = connection1.prepareStatement(query1);
		preparedStmt1.setString(1, userName);
	        preparedStmt1.setString(2, password);
		ResultSet resultSet1 = preparedStmt1.executeQuery();

		  ObservableList row = FXCollections.observableArrayList();

		    while (resultSet1.next()) {


	                for (int i = 1; i <= resultSet1.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet1.getString(i).toString());
	                    System.out.println(row);
	                }
		    }

		    for(int i =0 ; i<row.size();i++){

			user_id = row.get(i).toString();
			i++;
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
		    }
	    } catch (Exception e) {
		e.printStackTrace();
	    }



	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	 String query = "select * from user_details where email = ? and password = ?";
	      PreparedStatement preparedStmt;
	    try {
		preparedStmt = connection.prepareStatement(query);
		preparedStmt.setString(1, userName);
	        preparedStmt.setString(2, password);

	       Alert a = new Alert(AlertType.NONE);
		      // execute the preparedstatement
		     ResultSet resultSet = preparedStmt.executeQuery();



		     if(!resultSet.next()){






				//App.setRoot("Login");
				usernamefeild.setText("");
				passwordfeild.setText("");

			  // set alert type
		                a.setAlertType(AlertType.ERROR);

		                //set content text
		               a.setContentText("Wrong UserName or Password!\n Please Try Again");

		                // show the dialog
		              a.show();

		     }else{


			  // set alert type
		                a.setAlertType(AlertType.INFORMATION);

		                //set content text
		               a.setContentText("Login Successful!");

		               a.initModality(Modality.APPLICATION_MODAL);
		                // show the dialog
		              a.show();


		              connection.close();



			  try
			         {
			             Thread.sleep(1000);
			         }
			         catch(InterruptedException ex)
			         {
			             Thread.currentThread().interrupt();
			         }

			 System.out.println("Success");
			 if(user.getRole().equals("admin")){

				    App.setRoot("AdminDashboard");


				}
				else if(user.getRole().equals("user")){

				    App.setRoot("UserDashboard");

				}
		     }
	    } catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }







    }

    @FXML
    void forgotPassword(ActionEvent event) throws IOException {

	App.setRoot("ForgotPassword");
    }


    @FXML
    void signUp(ActionEvent event) throws IOException {
	  App.setRoot("SignUp");
    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

	usernamefeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color: #6A4E66");
	passwordfeild.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-background-color:#000000;-fx-border-color: #6A4E66");


	success_label.setVisible(false);
	error_label.setVisible(false);

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
		slideshow.setImage(imageArrayList.get(count++));
	        if (count >= imageArrayList.size()) {
	            count = 0;
	        }
	    }
	}, 0, delay);



    }

    public User getUser(){
	return this.user;
    }

    public String getId(){
	return this.user_id;
    }
}
