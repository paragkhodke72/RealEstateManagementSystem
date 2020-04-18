package com.mycompany.newproject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class ForgotPasswordController {

    @FXML
    private ImageView slideshow;

    @FXML
    private Pane login_pane;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernamefeild;

    @FXML
    private PasswordField passwordfeild;

    @FXML
    private Button signupBtn;

    @FXML
    void login(ActionEvent event) throws SQLException {


	String email = usernamefeild.getText().toString();
	String password = passwordfeild.getText().toString();

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();



	String query = "update users set password=? where email=? ";
	String query1 = "update  user_details set password=? where email=?";
        
	PreparedStatement preparedStmt = connection.prepareStatement(query);
	PreparedStatement preparedStmt1 = connection.prepareStatement(query1);

	preparedStmt1.setString(1, password);
	preparedStmt1.setString(2, email);

	preparedStmt.setString(1, password);
	preparedStmt.setString(2, email);


	preparedStmt.executeUpdate();
	preparedStmt1.executeUpdate();
	connection.close();


	Alert a = new Alert(AlertType.NONE);
	 a.setAlertType(AlertType.INFORMATION);
         a.setContentText("Password Changed Successful!");
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
	    App.setRoot("Login");
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {

	App.setRoot("Login");
    }

}
