package com.mycompany.newproject;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


public class EditPropertyController implements Initializable{

    @FXML
    private Button switchToBuyingBtn;

    @FXML
    private Button soldPropertyBtn;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button myPropertiesButton;

    @FXML
    private Button profileBtn;

    @FXML
    private Button sellPropertyBtn;

    @FXML
    private Button save_property_btn;

    @FXML
    private TextField property_address;

    @FXML
    private TextField no_of_beds;

    @FXML
    private TextField property_name;

    @FXML
    private ComboBox<String> status_combobox;

    @FXML
    private TextField price_rent;

    @FXML
    private TextField sq_feet_area;

    @FXML
    private DatePicker year_built;

    @FXML
    private TextField description;

    @FXML
    private CheckBox amenity_one;

    @FXML
    private CheckBox amenity_two;

    @FXML
    private CheckBox amenity_three;

    @FXML
    private CheckBox amenity_four;

    @FXML
    private CheckBox amenity_five;

    @FXML
    private CheckBox amenity_six;

    @FXML
    private CheckBox amenity_seven;

    @FXML
    private CheckBox amenity_eight;

    @FXML
    private CheckBox amenity_nine;

    @FXML
    private ImageView pictureView;

    @FXML
    private Button uploadPictureBtn;

    @FXML
    private ComboBox<String> propetyType_box;

    @FXML
    private ComboBox<String> country;

    @FXML
    private ComboBox<String> state;

    @FXML
    private ComboBox<String> city;

    File file;
    Image image;

    @FXML
    void dashboard(ActionEvent event) throws IOException {

	App.setRoot("SellerDashboard");
    }

    @FXML
    void myProperty(ActionEvent event) throws IOException {
	App.setRoot("SellerListings");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
	App.setRoot("SellerEditProfile");
    }

    @FXML
    void sellProperty(ActionEvent event) throws IOException {

	App.setRoot("AddProperty");
    }

    @FXML
    void soldProperties(ActionEvent event) {

    }


    @FXML
    void switchToBuying(ActionEvent event) throws IOException {
	App.setRoot("UserDashboard");
    }

    @FXML
    void saveProperty(ActionEvent event) throws SQLException, FileNotFoundException {



	LoginController controller = new LoginController();
	String owner_id = controller.getId();


	String propertyName = property_name.getText().toString();
	String propertyAddress = property_address.getText().toString();
	LocalDate buildDate = year_built.getValue();
	String noOfBeds = no_of_beds.getText().toString();
	String descriptionProperty = description.getText().toString();
	String price = price_rent.getText().toString();
	String sq_feet = sq_feet_area.getText().toString();
	String status = status_combobox.getSelectionModel().getSelectedItem().toString();
	String type = propetyType_box.getSelectionModel().getSelectedItem().toString();
	String c = city.getSelectionModel().getSelectedItem().toString();
	String s = state.getSelectionModel().getSelectedItem().toString();
	String cc = country.getSelectionModel().getSelectedItem().toString();

	List<String> amenitiesList = new ArrayList<String>();

	if(amenity_one.isSelected()){
	    amenitiesList.add(amenity_one.getText().toString());
	}

	if(amenity_two.isSelected()){
	    amenitiesList.add(amenity_two.getText().toString());
	}

	if(amenity_three.isSelected()){
	    amenitiesList.add(amenity_three.getText().toString());
	}

	if(amenity_four.isSelected()){
	    amenitiesList.add(amenity_four.getText().toString());
	}

	if(amenity_five.isSelected()){
	    amenitiesList.add(amenity_five.getText().toString());
	}

	if(amenity_six.isSelected()){
	    amenitiesList.add(amenity_six.getText().toString());
	}

	if(amenity_seven.isSelected()){
	    amenitiesList.add(amenity_seven.getText().toString());
	}

	if(amenity_eight.isSelected()){
	    amenitiesList.add(amenity_eight.getText().toString());
	}

	ConnectionClass connectionClass = new ConnectionClass();
	Connection connection = connectionClass.getConnection();


	FileInputStream fis=new FileInputStream(file);





	String query = "update properties set name=?,address=?,description=?,no_of_beds=?,sq_feet=?,picture=?,price=?,amenities=?,owner_id=?,type=?,city=?,status=?,state=?,country=?,year_built=?,buyer_id=? WHERE id=?";

	PreparedStatement preparedStmt = connection.prepareStatement(query);
	preparedStmt.setString(1, propertyName);
	preparedStmt.setString(2, propertyAddress);
	preparedStmt.setString(3, descriptionProperty);
	preparedStmt.setString(4, noOfBeds);
	preparedStmt.setString(5, sq_feet);


	preparedStmt.setBinaryStream(6, fis,(int)file.length());
	preparedStmt.setString(7, price);
	preparedStmt.setString(8, amenitiesList.get(0));
	preparedStmt.setString(9, owner_id);
	preparedStmt.setString(10, type);
	preparedStmt.setString(11, c);
	preparedStmt.setString(12, status);
	preparedStmt.setString(13, s);
	preparedStmt.setString(14, cc);
	preparedStmt.setString(15, buildDate.toString());
	preparedStmt.setString(16, "0");
	preparedStmt.setInt(17,4);

	Statement statement;
	try {
	    statement = connection.createStatement();
	    preparedStmt.executeUpdate();
	    Alert a = new Alert(AlertType.NONE);
		 a.setAlertType(AlertType.INFORMATION);

	        // set content text
	        a.setContentText("Property Edited Successfully!");

	        // show the dialog
	        a.show();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	connection.close();


    }


    @FXML
    void uploadPicture(ActionEvent event) {
	FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
           image = SwingFXUtils.toFXImage(bufferedImage, null);
            pictureView.setImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


	try {
		this.setCityBox();
	    this.setCountryBox();
	    this.setPropertyTypeBox();
		this.setStateBox();
		this.setStatus();
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}



	SellerListingsController controller = new SellerListingsController();
	Property propertyx = controller.getProperty();
	  int id = 0;
	  String buildDate = null;
	  Property property = new Property();
	  String city = null,state= null,country= null;


	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT id,city,state,country,address,name,type,status,price,sq_feet,no_of_beds,year_built,description from properties where name = ?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, propertyx.getPropertyName());
   		    ResultSet resultSet = preparedStmt1.executeQuery();

   		    ObservableList row = FXCollections.observableArrayList();

   		    while (resultSet.next()) {


   	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
   	                    row.add(resultSet.getString(i).toString());
   	                    System.out.println(row);
   	                }


   	             //   data.add(row);

   	            }






   			 id= Integer.parseInt(row.get(0).toString());
   			 city =row.get(1).toString();
   			 state=row.get(2).toString();
   			 country=row.get(3).toString();
   			property.setLocation(row.get(4).toString());

   			property.setPropertyName(row.get(5).toString());

   			property.setType(row.get(6).toString());


   			property.setStatus(row.get(7).toString());

   			property.setPrice(row.get(8).toString());

   			property.setSquareFeetArea(row.get(9).toString());

   			property.setNoOfBeds(row.get(10).toString());

   		        buildDate = row.get(11).toString();

   			property.setDescription(row.get(12).toString());




   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}


	property_name.setText(property.getPropertyName());
	no_of_beds.setText(property.getNoOfBeds());

	//propetyType_box.setPromptText(property.getType());
	propetyType_box.getSelectionModel().select(property.getType());
	//status_combobox.setPromptText(property.getStatus());

	status_combobox.getSelectionModel().select(property.getStatus());

	sq_feet_area.setText(property.getSquareFeetArea());
	price_rent.setText(property.getPrice());
	description.setText(property.getDescription());
	property_address.setText(property.getLocation());
	year_built.setAccessibleText(buildDate);
	this.city.setPromptText(city);

	this.city.getSelectionModel().select(city);
	//this.state.setPromptText(state);
	this.state.getSelectionModel().select(state);
	//this.country.setPromptText(country);

	this.country.getSelectionModel().select(country);
	try{
	    ConnectionClass connectionClass1 = new ConnectionClass();
		Connection connection1 = connectionClass1.getConnection();

		File file=new File("Users\\paragkhodke\\Downloads\\src\\main\\resources\\com\\mycompany\\newproject\\images\\newimage.jpg");
		FileOutputStream fos=new FileOutputStream(file);
		byte b[];
		Blob blob;

		PreparedStatement ps=connection1.prepareStatement("select picture from properties where id=?");
		ps.setString(1, Integer.toString(id));
		ResultSet rs=ps.executeQuery();

		while(rs.next()){
			blob=rs.getBlob("picture");
			b=blob.getBytes(1,(int)blob.length());
			fos.write(b);
		}

		ps.close();
		fos.close();
		connection1.close();
	}catch(Exception e){
		e.printStackTrace();
	}

	File file = new File("Users\\paragkhodke\\Downloads\\src\\main\\resources\\com\\mycompany\\newproject\\images\\newimage.jpg");
	BufferedImage bufferedImage;
	try {
	    bufferedImage = ImageIO.read(file);
	    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	    pictureView.setImage(image);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}



    }

    public void setPropertyTypeBox(){
  	ConnectionClass connectionClass = new ConnectionClass();
  	Connection connection = connectionClass.getConnection();
  	 String query = "select name from property_types";
  	      PreparedStatement preparedStmt;

  		try {
  		    preparedStmt = connection.prepareStatement(query);
  		    ResultSet resultSet = preparedStmt.executeQuery();

  		    ObservableList row = FXCollections.observableArrayList();

  		    while (resultSet.next()) {


  	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
  	                    row.add(resultSet.getString(i).toString());
  	                    System.out.println(row);
  	                }


  	             //   data.add(row);

  	            }
  		    propetyType_box.setItems(row);
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


     		 country.setItems(row);


     		} catch (SQLException e) {
     		    // TODO Auto-generated catch block
     		    e.printStackTrace();
     		}

     		connection.close();
      }

      public void setStateBox(){
  	 state.setOnMouseClicked(e->{


               try {

                   ConnectionClass connectionClass1 = new ConnectionClass();
           	    Connection connection1 = connectionClass1.getConnection();
                   String countryName = country.getSelectionModel().getSelectedItem().toString();

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

                                 state.setItems(row1);


                                           } catch (Exception e2) {
                                               // TODO: handle exception
                                           }
                                           		  });
      }

      public void setCityBox(){

  	city.setOnMouseClicked(e->{


              try {

                  ConnectionClass connectionClass1 = new ConnectionClass();
          	    Connection connection1 = connectionClass1.getConnection();
                  String stateName = state.getSelectionModel().getSelectedItem().toString();

  		 PreparedStatement preparedStmt2;
  		 String query2 = "select id from states where name = ?";

  		 String country_id;



  			    preparedStmt2 = connection1.prepareStatement(query2);
  			    preparedStmt2.setString(1,stateName);
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
                             String query1 = "SELECT name from cities where state_id=?";
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

                                city.setItems(row1);


                                          } catch (Exception e2) {
                                              // TODO: handle exception
                                          }
                                          		  });
      }


      public void setStatus(){

  	ObservableList<String> status = FXCollections.observableArrayList();
  	status.add("For Sale");
  	status.add("For Rent");


  	status_combobox.setItems(status);
      }



}
