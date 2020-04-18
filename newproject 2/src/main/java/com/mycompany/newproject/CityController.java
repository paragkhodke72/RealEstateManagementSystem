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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


public class CityController implements Initializable{

    @FXML
    private Button deleteCityBtn;

    @FXML
    private TableView<Address> cityTable;

    @FXML
    private TableColumn<Address, String> countryCol;

    @FXML
    private TableColumn<Address, String> stateCol;

    @FXML
    private TableColumn<Address, String> cityCol;

    @FXML
    private Button addCityBtn;

    @FXML
    private TextField et_addCity;

    @FXML
    private ComboBox<String> countryBox;

    @FXML
    private ComboBox<String> stateBox;

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
    void addCity(ActionEvent event) {


	String city = et_addCity.getText().toString();
	String state = stateBox.getSelectionModel().getSelectedItem().toString();
	String country = countryBox.getSelectionModel().getSelectedItem().toString();
	String state_id;

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();


	 PreparedStatement preparedStmt = null;
	 String query = "select id from states where name = ?";




		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1,state);
		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                    System.out.println(row);
	                }


	             //   data.add(row);

	            }

		    state_id = row.get(0).toString();



		    String query1 = "insert into cities (state_id, name)"
			        + " values (?, ?)";

		    PreparedStatement preparedStatement1;
		    preparedStatement1 = connection.prepareStatement(query1);
		    preparedStatement1.setString(1,state_id);
		    preparedStatement1.setString(2, city);
		    preparedStatement1.execute();

		   connection.close();

		   Alert a = new Alert(AlertType.NONE);
			 a.setAlertType(AlertType.INFORMATION);

		        // set content text
		        a.setContentText("City Added Successfully!");

		        // show the dialog
		        a.show();

		        populateTable();

		        et_addCity.setText("");
		        countryBox.setAccessibleText("Select Country");
		        stateBox.setAccessibleText("Select State");

		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
    }



    @FXML
    void deleteCity(ActionEvent event) throws SQLException {
	Address state = new Address();
	state = cityTable.getSelectionModel().getSelectedItem();
	String cityName = state.getCity();
	String query = "DELETE FROM cities WHERE name = ?";

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();

	      PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    preparedStmt.setString(1, cityName);
		    preparedStmt.execute();
		}catch (Exception e) {
		    e.printStackTrace();
		}

		 Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("City Deleted Successfully!");

	        // show the dialog
	        a.show();

		populateTable();
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

	 try {
	    populateTable();
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();
	String query = "select name from countries";
	PreparedStatement preparedStmt;

		try {
		    preparedStmt = connection.prepareStatement(query);
		    ResultSet resultSet = preparedStmt.executeQuery();

		    ObservableList row = FXCollections.observableArrayList();

		    while (resultSet.next()) {


	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
	                    row.add(resultSet.getString(i).toString());
	                   // System.out.println(row);
	                }


	             //   data.add(row);

	            }
		    countryBox.setItems(row);

		    connection.close();

		  stateBox.setOnMouseClicked(e->{


                                try {

                                    ConnectionClass connectionClass1 = new ConnectionClass();
                            	    Connection connection1 = connectionClass1.getConnection();
                                    String countryName = countryBox.getSelectionModel().getSelectedItem().toString();

           			 PreparedStatement preparedStmt2;
           			 String query2 = "select id from countries where name = ?";

           			 String country_id;



           				    preparedStmt2 = connection1.prepareStatement(query2);
           				    preparedStmt2.setString(1,countryName);
           				    ResultSet resultSet2 = preparedStmt2.executeQuery();

           				    ObservableList row2 = FXCollections.observableArrayList();

           				    while (resultSet2.next()) {


           			                for (int i = 1; i <= resultSet2.getMetaData().getColumnCount(); i++) {
           			                    row2.add(resultSet2.getString(i).toString());
           			                    System.out.println(row2);
           			                }


           			             //   data.add(row);

           			            }

           				    country_id = row2.get(0).toString();
           				    connection1.close();

           				 ConnectionClass connectionClass2 = new ConnectionClass();
           				Connection connection2 = connectionClass2.getConnection();
           	String query1 = "SELECT name from states where country_id=?";
           	PreparedStatement preparedStatement;
           	preparedStatement = connection2.prepareStatement(query1);
           	preparedStatement.setString(1,country_id);
           	ResultSet rs = preparedStatement.executeQuery();

           	 ObservableList row1 = FXCollections.observableArrayList();

           	    while (rs.next()) {


                        for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                            row1.add(rs.getString(i).toString());
                           // System.out.println(row1);
                        }


                     //   data.add(row);

                    }
           	 connection2.close();

           	   stateBox.setItems(row1);


                                } catch (Exception e2) {
                                    // TODO: handle exception
                                }
                                		  });





		} catch (SQLException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

    }

    public void populateTable() throws SQLException{

   	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT cities.name,states.name,countries.name FROM cities INNER JOIN states ON cities.state_id = states.id INNER JOIN countries ON states.country_id = countries.id;";
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

   			state.setCity(row.get(i).toString());
   			i++;
   			state.setState(row.get(i).toString());
   			i++;
   			state.setCountry(row.get(i).toString());
   			states.add(state);

   		    }

   		    ObservableList<Address> stateObservable =FXCollections.observableArrayList(states);;

		    	countryCol.setCellValueFactory(new PropertyValueFactory<Address, String>("country"));
   		    	cityCol.setCellValueFactory(new PropertyValueFactory<Address, String>("city"));
   			stateCol.setCellValueFactory(new PropertyValueFactory<Address, String>("state"));

   		    cityTable.setItems(stateObservable);
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

   		connection.close();
       }
}
