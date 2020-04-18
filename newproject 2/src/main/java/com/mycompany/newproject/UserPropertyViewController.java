package com.mycompany.newproject;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class UserPropertyViewController implements Initializable{

    @FXML
    private Label nameLabel;

    @FXML
    private Label addresslabel;

    @FXML
    private Label typelabel;

    @FXML
    private Label bedslabel;

    @FXML
    private Label sqfeetlabel;

    @FXML
    private Label statuslabel;

    @FXML
    private Label yearbuiltlabel;

    @FXML
    private Label pricelabel;

    @FXML
    private Label descriptionlabel;

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
    private CheckBox amenity_seven;

    @FXML
    private CheckBox amenity_eight;

    @FXML
    private CheckBox amenity_six;

    @FXML
    private ImageView propertyImage;

    @FXML
    private Label ownerfirstName;

    @FXML
    private Label owner_lastName;

    @FXML
    private Label ownerAge;

    @FXML
    private Label ownerEmail;

    @FXML
    private Button logoutButton1;

    @FXML
    private Button inquiryButton1;

    @FXML
    private Button ownerButton1;

    @FXML
    private Button myPropertiesButton1;

    @FXML
    private Button propertyTypeButton1;

    @FXML
    private Button propertyButton1;

    @FXML
    private Button dashboardButton1;

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
	UserPropertyListController controller = new UserPropertyListController();
	Property propertyx = controller.getViewProperty();
	  String id = null ;
	  String buildDate = null;
	  Property property = new Property();

	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT id,city,state,country,address,name,type,status,price,sq_feet,no_of_beds,year_built,description,owner_id from properties where name = ?";
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


   		    	id= row.get(0).toString();
			String city =row.get(1).toString();
			String state=row.get(2).toString();
			String country=row.get(3).toString();

   		    	property.setLocation(row.get(4).toString()+","+city+","+state+","+country);

   			property.setPropertyName(row.get(5).toString());

   			property.setType(row.get(6).toString());


   			property.setStatus(row.get(7).toString());

   			property.setPrice(row.get(8).toString());

   			property.setSquareFeetArea(row.get(9).toString());

   			property.setNoOfBeds(row.get(10).toString());

   		        buildDate = row.get(11).toString();

   			property.setDescription(row.get(12).toString());
   			property.setOwner(row.get(13).toString());

   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

	nameLabel.setText(property.getPropertyName());
	bedslabel.setText(property.getNoOfBeds());
	typelabel.setText(property.getType());
	statuslabel.setText(property.getStatus());
	sqfeetlabel.setText(property.getSquareFeetArea());
	pricelabel.setText(property.getPrice());
	descriptionlabel.setText(property.getDescription());
	addresslabel.setText(property.getLocation());
	yearbuiltlabel.setText(buildDate);
	try{
	    ConnectionClass connectionClass1 = new ConnectionClass();
		Connection connection1 = connectionClass1.getConnection();

		File file=new File("Users\\paragkhodke\\Downloads\\src\\main\\resources\\com\\mycompany\\newproject\\images\\newimage.jpg");
		FileOutputStream fos=new FileOutputStream(file);
		byte b[];
		Blob blob;

		PreparedStatement ps=connection1.prepareStatement("select picture from properties where id=?");
		ps.setString(1, id);
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
	    propertyImage.setImage(image);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}





	ConnectionClass connectionClassX = new ConnectionClass();
   	Connection connectionX = connectionClassX.getConnection();
   	 String queryX = "SELECT * from users where id=?";
   	      PreparedStatement preparedStmtX;

   		try {
   		 preparedStmtX = connectionX.prepareStatement(queryX);
   		preparedStmtX.setString(1, property.getOwner());
   		    ResultSet resultSetX = preparedStmtX.executeQuery();

   		    ObservableList rowX = FXCollections.observableArrayList();

   		    while (resultSetX.next()) {


   	                for (int i = 1; i <= resultSetX.getMetaData().getColumnCount(); i++) {
   	                    rowX.add(resultSetX.getString(i).toString());
   	                    System.out.println(rowX);
   	                }


   	             //   data.add(row);

   	            }


   		    User user = new User();

   		 for(int i =0 ; i<rowX.size();i++){


			i++;
			user.setFirstName(rowX.get(i).toString());
			i++;
			user.setLastName(rowX.get(i).toString());
			i++;
			user.setEmail(rowX.get(i).toString());
			i++;
			user.setAge(Integer.parseInt(rowX.get(i).toString()));
			i++;
			user.setRole(rowX.get(i).toString());
			i++;
			user.setPassword(rowX.get(i).toString());
		    }


   		 ownerfirstName.setText(user.getFirstName());
   		 owner_lastName.setText(user.getLastName());
   		 ownerAge.setText(Integer.toString(user.getAge()));
   		 ownerEmail.setText(user.getEmail());
   		 connectionX.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}

    }

}
