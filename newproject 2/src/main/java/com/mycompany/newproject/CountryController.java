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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class CountryController implements Initializable{


    @FXML
    private TableView<Address> countryTable;

    @FXML
    private TableColumn<Address, String> countryCol;
    @FXML
    private Button deleteCountryBtn;

    @FXML
    private TextField et_addCountry;

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
    private Button addCountryBtn;

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
    void deleteCountry(ActionEvent event) throws SQLException  {

	Address state = new Address();
	state = countryTable.getSelectionModel().getSelectedItem();
	String countryName = state.getCountry();
	String query = "DELETE FROM countries WHERE name = ?";

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, countryName);
		    preparedStmt.execute();
		}catch (Exception e) {
		    e.printStackTrace();
		}

		 Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("Country Deleted Successfully!");

	        // show the dialog
	        a.show();

		populateTable();

    }

    @FXML
    void addCountry(ActionEvent event) throws SQLException {



	String name = et_addCountry.getText().toString();
	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();



	String query = "insert into countries (name)"
        + " values (?)";

	PreparedStatement preparedStmt = connection.prepareStatement(query);
	preparedStmt.setString(1, name);
	preparedStmt.execute();
	connection.close();

	Alert a = new Alert(AlertType.NONE);
	 a.setAlertType(AlertType.INFORMATION);

        // set content text
        a.setContentText("Country Added Successfully!");

        // show the dialog
        a.show();

        populateTable();
        et_addCountry.setText("");
    }

   public void populateTable() throws SQLException{

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


   		    List<Address> states = new ArrayList<Address>();

   		    for(int i =0 ; i<row.size();i++){
   			Address state = new Address();

   			state.setCountry(row.get(i).toString());
   			states.add(state);

   		    }

   		    ObservableList<Address> stateObservable =FXCollections.observableArrayList(states);

		    	countryCol.setCellValueFactory(new PropertyValueFactory<Address, String>("country"));


   		    countryTable.setItems(stateObservable);
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
       }

@Override
public void initialize(URL location, ResourceBundle resources) {
   try {
    populateTable();
} catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}

}
}
