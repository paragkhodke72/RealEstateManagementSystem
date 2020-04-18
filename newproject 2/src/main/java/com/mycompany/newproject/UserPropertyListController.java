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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class UserPropertyListController implements Initializable{


    @FXML
    private Button by_rentButton;

    @FXML
    private Button viewButton;

    @FXML
    private Button switchToSelling;

    @FXML
    private TableView<Property> property_table;

    @FXML
    private TableColumn<Property, String> nameCol;

    @FXML
    private TableColumn<Property, String> typeCol;

    @FXML
    private TableColumn<Property, String> addressCol;

    @FXML
    private TableColumn<Property, String> bedsCol;

    @FXML
    private TableColumn<Property, String> sq_feetCol;

    @FXML
    private TableColumn<Property, String> status;

    @FXML
    private TableColumn<Property, String> price;

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

    static List<Property> propertyList = new ArrayList<Property>();
    private static Property property = new Property();
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
    @FXML
    void View(ActionEvent event) throws IOException {
       this.property = this.property_table.getSelectionModel().getSelectedItem();

	System.out.println(property.getPropertyName());
App.setRoot("ViewProperty");
    }


    @FXML
    void switchToSelling(ActionEvent event) throws IOException {

App.setRoot("SellerDashboard");
    }

    @FXML
    void buyRent(ActionEvent event) throws IOException {
        
        
                            
                              
	LoginController controller = new LoginController();
	String id = controller.getId();
	 ImageView imageView = new ImageView();
	String propertyId = null;
	Property property=property_table.getSelectionModel().getSelectedItem();
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT id,city,state,country,address,name,type,status,price,sq_feet,no_of_beds,year_built,description,owner_id from properties where name = ?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, property.getPropertyName());
   		    ResultSet resultSet = preparedStmt1.executeQuery();

   		    ObservableList row = FXCollections.observableArrayList();

   		    while (resultSet.next()) {


   	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
   	                    row.add(resultSet.getString(i).toString());
   	                    System.out.println(row);
   	                }


   	             //   data.add(row);

   	            }


   		    	propertyId= row.get(0).toString();
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



   			property.setDescription(row.get(12).toString());
   			property.setOwner(row.get(13).toString());

   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}
	try{
	    ConnectionClass connectionClass1 = new ConnectionClass();
		Connection connection1 = connectionClass1.getConnection();

		File file=new File("User\\paragkhodke\\NetBeansProject\\newproject2\\src\\main\\resources\\com\\mycompany\\newproject\\images\\newimage.jpg");
		FileOutputStream fos=new FileOutputStream(file);
		byte b[];
		Blob blob;

		PreparedStatement ps=connection1.prepareStatement("select picture from properties where id=?");
		ps.setString(1, propertyId);
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

	File file = new File("User\\paragkhodke\\NetBeansProject\\newproject2\\src\\main\\resources\\com\\mycompany\\newproject\\images\\newimage.jpg");
	BufferedImage bufferedImage;
	try {
	    bufferedImage = ImageIO.read(file);
	    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
	    imageView.setImage(image);

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

                   
	     Text text1 = new Text("Property Name :");
	     Text text2 = new Text("Property Type :");
	     Text text3 = new Text("Property Address   :");
	     Text text4 = new Text("Price         :");
            
             //adding label
             Text text5 = new Text("Property Area");
             
             TextField textField6 = new TextField();
             textField6.setText(property.getSquareFeetArea());
             


	     //creating the text field for the first name
	     TextField textField1 = new TextField();
	      textField1.setText(property.getPropertyName());
	      textField1.setEditable(false);
	     //creating the text field for the last name
	     TextField textField2 = new TextField();
	     textField2.setText(property.getType());
	     textField2.setEditable(false);
	     //creating the text field for the address
	     TextField textField3 = new TextField();
	     textField3.setText(property.getLocation());
	     textField3.setEditable(false);
	     //creating the text field for the salary
	     TextField textField5 = new TextField();
	     textField5.setText(property.getPrice());
	     textField5.setEditable(false);
	     //Creating Button
	     Button button1 = new Button("Buy");
	     Button button2 = new Button("Cancel");


             imageView.setFitHeight(300);
             imageView.setFitWidth(100);

	     //Creating a Grid Pane
	     GridPane gridPane = new GridPane();

	     //Arranging all the nodes in the grid
	     gridPane.add(imageView, 1, 0);
	     gridPane.add(text1, 0, 2);
	     gridPane.add(textField1, 1, 2);
	     gridPane.add(text2, 0, 3);
	     gridPane.add(textField2, 1, 3);
	     gridPane.add(text3, 0, 4);
	     gridPane.add(textField3, 1, 4);
	     gridPane.add(text4, 0, 5);
	     gridPane.add(textField5, 1, 5);
	     
             //add the item to be added in the grid pane
             gridPane.add(text5,0,6);
             gridPane.add(textField6,1,6);
             gridPane.add(button1,2,7);
	     gridPane.add(button2, 3, 7); //first value is column second is row
	     //setting the horizontal and vertical gap between all nodes
	     gridPane.setHgap(10);
	     gridPane.setVgap(10);
             gridPane.setMaxHeight(500);
             gridPane.setMaxWidth(300);
             gridPane.setMaxSize(500,300);
	     //setting the padding
	     gridPane.setPadding(new Insets(10, 10, 10, 10));

	     //setting the gid alignment to center
	     gridPane.setAlignment(Pos.TOP_CENTER);
             
	//making a new scene and passing the gid pane to it
               Scene secondScene = new Scene(gridPane, 800, 600);
               
               // New window (Stage)
               gridPane.setStyle("-fx-background-color: white;");
               Stage newWindow = new Stage();
               newWindow.setTitle("Buy Property");
               newWindow.setScene(secondScene);
               newWindow.setMaxHeight(800);
               newWindow.setMaxWidth(600);
               newWindow.show();


	button1.setOnAction(e->{
	    ConnectionClass connectionClassX = new ConnectionClass();
	   	Connection connectionX = connectionClassX.getConnection();
	   	 String query1X = "update properties set buyer_id=? ,status=? where name=?";
	   	      PreparedStatement preparedStmt1X;

	   		try {
	   		    preparedStmt1X = connectionX.prepareStatement(query1X);
	   		    preparedStmt1X.setString(1, id);
	   		    preparedStmt1X.setString(2, "sold");
	   		    preparedStmt1X.setString(3, property.getPropertyName());
	   		    preparedStmt1X.executeUpdate();
                             Alert a1 = new Alert(null);
	   	   	    a1.setAlertType(AlertType.CONFIRMATION);
                             a1.setContentText("are you sure you want to buy/rent this property");
                             a1.show();
                             newWindow.close();
	   	              
                               //Alert a = new Alert(null);
	   	   	      // a.setAlertType(AlertType.INFORMATION);
                              // a.setContentText("Property Bought/Rented Successfully !");
                            //   a.show();
	   		}catch (Exception e1) {
			  e1.printStackTrace();
			}
	});

	button2.setOnAction(e->{
	    newWindow.close();
	});

    }


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

	LoginController controller = new LoginController();
	String id = controller.getId();
	ConnectionClass connectionClass = new ConnectionClass();
   	Connection connection = connectionClass.getConnection();
   	 String query1 = "SELECT city,state,country,address,name,type,status,price,sq_feet,no_of_beds from properties where owner_id !=? and status!= ?";
   	      PreparedStatement preparedStmt1;

   		try {
   		    preparedStmt1 = connection.prepareStatement(query1);
   		    preparedStmt1.setString(1, id);
   		    preparedStmt1.setString(2, "sold");
   		    ResultSet resultSet = preparedStmt1.executeQuery();

   		    ObservableList row = FXCollections.observableArrayList();

   		    while (resultSet.next()) {


   	                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
   	                    row.add(resultSet.getString(i).toString());
   	                    System.out.println(row);
   	                }


   	             //   data.add(row);

   	            }


   		    List<Property> properties = new ArrayList<Property>();

   		    for(int i =0 ; i<row.size();i++){
   			Property property = new Property();
   			String city =row.get(i).toString();
   			i++;
   			String state=row.get(i).toString();
   			i++;
   			String country=row.get(i).toString();
   			i++;
   			property.setLocation(row.get(i).toString()+","+city+","+state+","+country);
   			i++;
   			property.setPropertyName(row.get(i).toString());
   			i++;
   			property.setType(row.get(i).toString());
   			i++;

   			property.setStatus(row.get(i).toString());
   			i++;
   			property.setPrice(row.get(i).toString());
   			i++;
   			property.setSquareFeetArea(row.get(i).toString());
   			i++;
   			property.setNoOfBeds(row.get(i).toString());

   			properties.add(property);

   		    }

   		    ObservableList<Property> stateObservable =FXCollections.observableArrayList(properties);

		    	nameCol.setCellValueFactory(new PropertyValueFactory<Property, String>("propertyName"));
		    	addressCol.setCellValueFactory(new PropertyValueFactory<Property, String>("location"));
		    	typeCol.setCellValueFactory(new PropertyValueFactory<Property, String>("type"));
		    	sq_feetCol.setCellValueFactory(new PropertyValueFactory<Property, String>("squareFeetArea"));
		    	status.setCellValueFactory(new PropertyValueFactory<Property, String>("status"));
		    	price.setCellValueFactory(new PropertyValueFactory<Property, String>("price"));
		    	bedsCol.setCellValueFactory(new PropertyValueFactory<Property, String>("noOfBeds"));


   		    property_table.setItems(stateObservable);
   		 connection.close();
   		} catch (SQLException e) {
   		    // TODO Auto-generated catch block
   		    e.printStackTrace();
   		}
    }

    public void setProperty(Property property){
	this.property = property;
    }

    public Property getViewProperty(){
	return this.property;
    }
}
