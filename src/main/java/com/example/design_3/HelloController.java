package com.example.design_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.LocalDateStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.NumberStringConverter;
import org.controlsfx.control.action.Action;
import org.w3c.dom.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Locale;

public class HelloController {

    @FXML
    public Pane artworkLanding;
    @FXML
    public Pane contentPn;
    @FXML
    public Pane homeContent;
    @FXML
    public HBox parentBox;
    @FXML
    public ToggleButton homeBt;
    @FXML
    public ToggleButton artworkBt;
    @FXML
    public ToggleButton artistBt;
    @FXML
    public ToggleButton clientBt;
    @FXML
    public ToggleButton requestBt;
    //Add Artwork Use Case elements elements
    public TextField addArtTypeTf;
    public TextField addArtStyleTf;
    public TextField addArtInspirationTf;
    public TextField addArtPriceTf;
    public CheckBox addArtOnDisplayCb;
    public Button addArtCancelBt;
    public Button addArtConfirmBt;
    public ChoiceBox addArtStatusCb;
    public DatePicker addArtDate;
    public TextField addArtTitleTf;
    //
    private Pane curContent;
    private TextField artSearchTf;
    private Button addArtBt;
    //View Artist Use Case
    @FXML
    public Label viewArtistFName;
    @FXML
    public Label viewArtistLName;
    @FXML
    public Label viewArtistEmail;
    @FXML
    public Label viewArtistCountry;
    @FXML
    public Label viewArtistBYear;
    @FXML
    public Label viewArtistPseudonym;
    @FXML
    public Label viewArtistDYear;
    //Tables to store data in
    ObservableList <Artist> artists = FXCollections.observableArrayList();
    ObservableList <Art> artworks = FXCollections.observableArrayList();
    ObservableList <Manager> managers = FXCollections.observableArrayList();
    ObservableList <Viewer> viewers = FXCollections.observableArrayList();
    ObservableList <Client> clients = FXCollections.observableArrayList();
    ObservableList <Payment> payments = FXCollections.observableArrayList();
    ObservableList <Purchase> purchases = FXCollections.observableArrayList();
    ObservableList <Request> requests = FXCollections.observableArrayList();
    ObservableList <Viewing> viewings = FXCollections.observableArrayList();
    //private Artist curArtist;

    public HelloController(){
        //connectToDB();
        updateTable("Select * From Artist;", "Artist");
    }

    // Manager Page Methods

    @FXML
    protected void onHomeBtClick(ActionEvent event) throws IOException {
        FXMLLoader homePage = new FXMLLoader(HelloApplication.class.getResource("Manager/HomePage.fxml"));
        Scene testScene = new Scene(homePage.load());
        curContent = (Pane)testScene.lookup("#homeContent");
//        parentBox = (HBox) testScene.lookup("#parentBox");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);

//        contentPn.getChildren().clear();
//        contentPn.getChildren().setAll(curContent);
    }
    @FXML
    protected void onArtBtClick(ActionEvent event) throws IOException{
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Manager/ArtworkLanding.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#artworkLanding");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    protected void onArtistBtClick(ActionEvent event) throws IOException{
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Manager/ArtistLanding.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#artistLanding");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    protected void onClientBtCLick(ActionEvent event) throws IOException{
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Manager/ClientLanding.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#clientLanding");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    protected void onRequestsBtClick(ActionEvent event) throws IOException
    {
        //This line will replace the commented out paragraph, both do the same thing, but this is obviously cleaner.
        switchContent("Manager/RequestLanding.fxml", "requestLanding");
    }
    protected void connectToUI (Pane nextPane){
        // Was just using this to test something, will become the method to connect to all buttons and stuff we need.
        artSearchTf = (TextField) parentBox.lookup("#artSearchTf");
        artSearchTf.setText("Yasho!");
    }
    @FXML
    //Still working on this one
    protected void onAddArtClickBt(ActionEvent event) throws IOException{
        // addArtBt = (Button) parentBox.lookup("addArtBt");
//        try {
            switchInner("Manager/AddArtwork.fxml", "addArtContent",event);
            addArtStatusCb.getItems().add("Sold");
            addArtStatusCb.getItems().add("Test");
//        }
//        catch (Exception e){
//            System.out.println("Error loading the page");
//            System.out.println(e.getMessage());
//        }
    }

    private void switchContent(String targetFxmlPath, String targetContentID) throws IOException{
        // Handles the switching of content window when navigation pane button is pressed
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource(targetFxmlPath));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#" + targetContentID);
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    private void switchInner(String targetFxmlPath, String targetContentID, ActionEvent event ) throws IOException{
        // Handles the switching of content window caused outside of navigation pane

        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource(targetFxmlPath));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#" + targetContentID);
        parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }

    private void updateTable(String sqlString, String tableName )
    // Method to update the table based on sql query
    {
        try {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            //Almost same code as the one from first semester
            Connection newConnection = DriverManager.getConnection(dataBaseURL);
            System.out.println("Connected to MS Access database");
            Statement sqlStatement = newConnection.createStatement();
            ResultSet allNodes = sqlStatement.executeQuery(sqlString);
            //This is...
        if(allNodes != null){
            System.out.println("Successfully selected all data.");
        }
        else{
            System.out.println("Data recovery failed, table may be empty");
        }
        switch (tableName){
            case "Art":
                artworks.clear();
                while(allNodes.next()){
                    int artID = allNodes.getInt("Art_ID");
                    String artTitle = allNodes.getString("Art_Title");
                    String artDate = allNodes.getString("Art_Date");
                    String artType = allNodes.getString("Art_Type");
                    String artStyle = allNodes.getString("Art_Style");
                    String artInterpretation = allNodes.getString("Interpretation");
                    boolean artDisplayStatus = allNodes.getBoolean("Display_Status");
                    String artSaleStatus = allNodes.getString("Sale_Status");
                    Double artPrice = allNodes.getDouble("Price");
                    int artistID = allNodes.getInt("Artist_ID");
                    int purchaseID = allNodes.getInt("Purchase_ID");
                    Art newArt = new Art(artID, artTitle,artDate,artType,artStyle,
                            artInterpretation,artDisplayStatus,artSaleStatus,artPrice,artistID,purchaseID);
                    artworks.add(newArt);
                    System.out.println(newArt);//Debugging
                }
                break;
            case "Artist":
                artists.clear();
                while(allNodes.next()){
                    int artistID = allNodes.getInt("ArtistID");
                    String artistFName = allNodes.getString("First_Name");
                    String artistLName = allNodes.getString("Last_Name");
                    String artistEmail = allNodes.getString("Email");
                    String artistCountry = allNodes.getString("Country");
                    int artistBYear = allNodes.getInt("Birth_Year");
                    String artistPassword = allNodes.getString("Password");
                    String artistPseudonym = allNodes.getString("Psuedonym");
                    int artistDYear = allNodes.getInt("Death_Year");
                    Artist newArtist = new Artist(artistID,artistFName,artistLName,artistEmail,artistCountry,artistBYear,
                            artistPassword,artistPseudonym,artistDYear);
                    artists.add(newArtist);
                    System.out.println(newArtist);//Debugging
                }
                break;
            case "Client":
                clients.clear();
                if(viewers.isEmpty()){
                    System.out.println("No viewers in system, i.e, no clients either");
                }
                else {
                    while (allNodes.next()) {
                        int viewerID = allNodes.getInt("Viewer_ID");
                        String clientAddress = allNodes.getString("Client_Address");
                        Viewer curViewer = null;
                        for (int i = 0; i < viewers.size(); i++) {
                            curViewer = viewers.get(i);
                            int curViewerID = curViewer.getViewerID();
                            if (viewerID == curViewerID)
                                break;
                        }
                        Client newClient = (Client) curViewer;
                        newClient.setClientAddress(clientAddress);
                        clients.add(newClient);
                        System.out.println(newClient);
                    }
                }
                break;
            case "Manager":
                managers.clear();
                while(allNodes.next()) {
                    int managerID = allNodes.getInt("Manager_ID");
                    String managerFName = allNodes.getString("First_Name");
                    String managerLName = allNodes.getString("Last_Name");
                    String managerEmail = allNodes.getString("Email");
                    String managerPassword = allNodes.getString("Password");
                    String managerOfficeNum = allNodes.getString("Office_Number");
                    Manager newManager = new Manager(managerID, managerFName, managerLName,
                            managerEmail, managerPassword, managerOfficeNum);
                    managers.add(newManager);
                    System.out.println(newManager);
                }

                break;
            case "Viewer":
                viewers.clear();
                while(allNodes.next()){
                    int viewerID = allNodes.getInt("Viewer_ID");
                    String viewerType = allNodes.getString("Type_Of_Viewer");
                    String viewerPassword = allNodes.getString("Password");
                    String viewerFName = allNodes.getString("First_Name");
                    String viewerLName = allNodes.getString("Last_Name");
                    String viewerEmail = allNodes.getString("Email");
                    String viewerPhone = allNodes.getString("Phone");
                    Viewer newViewer = new Viewer(viewerID,viewerType,viewerPassword,viewerFName,
                            viewerLName,viewerEmail,viewerPhone);
                    viewers.add(newViewer);
                    System.out.println(newViewer.toString());
                }
                break;
            case "Payment":
                payments.clear();
                while(allNodes.next()){
                    int purchaseID = allNodes.getInt("PurchaseID");
                    String cardOwner = allNodes.getString("Card_Owner");
                    Payment newPayment = new Payment(purchaseID,cardOwner);
                    payments.add(newPayment);
                    System.out.println(newPayment);
                }
                break;
            case "Purchase":
                purchases.clear();
                while(allNodes.next()){
                    int purchaseID = allNodes.getInt("Purchase_ID");
                    Double purchaseAmount = allNodes.getDouble("Purchase_Amount");
                    int viewerID = allNodes.getInt("Viewer_ID");
                    Purchase newPurchase = new Purchase(purchaseID,purchaseAmount,viewerID);
                    purchases.add(newPurchase);
                    System.out.println(newPurchase);
                }
                break;
            case "Request":
                requests.clear();
                while (allNodes.next()){
                    int requestID = allNodes.getInt("Request_ID");
                    String requestDate = allNodes.getString("Date");
                    String requestMessage = allNodes.getString("Message");
                    Boolean requestResponse = allNodes.getBoolean("Response");
                    int managerID = allNodes.getInt("Manager_ID");
                    int artistID = allNodes.getInt("Artist_ID");
                    Request newRequest = new Request(requestID,requestDate,requestMessage,
                            requestResponse,managerID,artistID);
                    requests.add(newRequest);
                }
                break;
            case "Viewing":
                viewings.clear();
                while(allNodes.next()){
                    int viewingID = allNodes.getInt("Viewing_ID");
                    String viewingTimeslot = allNodes.getString("Timeslot");
                    int viewingCapacity = allNodes.getInt("Gallery_Capacity");
                    int viewerID = allNodes.getInt("Viewer_ID");
                    int artID = allNodes.getInt("Art_ID");
                    Viewing newViewing = new Viewing(viewingID,viewingTimeslot,viewingCapacity,
                            viewerID,artID);
                    viewings.add(newViewing);
                    System.out.println(newViewing);
                }
                break;
        }
        newConnection.close();
        }
        catch (Exception e){
            System.out.println("Connection failed.");
        }
    }

    private void connectToDB()
    //Connects to the database, this one was just testing by printing all the names of the art pieces in the database
    {
        String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";

        try {
            //Almost same code as the one from first semester
            Connection newConnection = DriverManager.getConnection(dataBaseURL);
            System.out.println("Connected to MS Access database");
            Statement sqlStatement = newConnection.createStatement();
            String sql = "Select * From Artist";
            ResultSet allArt = sqlStatement.executeQuery(sql);
            if(allArt != null){
                System.out.println("Successfully selected all data.");
            }
            // Gets whatever we want from the database
            while(allArt.next()){
                int artistID = allArt.getInt("ArtistID");
                String artistFName = allArt.getString("First_Name");
                String artistLName = allArt.getString("Last_Name");
                String artistEmail = allArt.getString("Email");
                String artistCountry = allArt.getString("Country");
                int artistBYear = allArt.getInt("Birth_Year");
                String artistPassword = allArt.getString("Password");
                String artistPseudonym = allArt.getString("Psuedonym");
                int artistDYear = allArt.getInt("Death_Year");
                Artist newArtist = new Artist(artistID,artistFName,artistLName,artistEmail,artistCountry,artistBYear,
                        artistPassword,artistPseudonym,artistDYear);
                artists.add(newArtist);
                System.out.println(newArtist);
            }
            newConnection.close();
        }
        catch(Exception e){
            System.out.println("Try Again Buddy");
        }
    }


    // Artist Page Methods

    @FXML
    protected void onViewArtistClick(ActionEvent event){
        //Will still enter the code to connect table selection to selected/ curArtist below


        //Testing to view Artist from observable list in the window using arbitrary artist.
        Artist curArtist = artists.get(2);
        viewArtistFName.setText(curArtist.getArtistFName().getValue());
        viewArtistLName.setText(curArtist.getArtistLName().getValue());
        viewArtistPseudonym.setText(curArtist.getArtistPseudonym().getValue());
        viewArtistEmail.setText(curArtist.getArtistEmail().getValue());
        viewArtistCountry.setText(curArtist.artistCountry.getValue());
        viewArtistBYear.setText(String.valueOf(curArtist.getArtistBYear().getValue()));
        viewArtistDYear.setText(String.valueOf(curArtist.getArtistDYear().getValue()));

    }
    @FXML
    protected void onAddArtConfirm(ActionEvent event){
        if(checkContentTf() && addArtDate.getValue() != null){
            int artID = artworks.size();
            String title = addArtTitleTf.getText();
            LocalDate date = addArtDate.getValue();
            String type = addArtTypeTf.getText();
            String style = addArtStyleTf.getText();
            String interpretation = addArtInspirationTf.getText();
            Double price = Double.parseDouble(addArtPriceTf.getText());
            Boolean displayStatus = addArtOnDisplayCb.isSelected();
            String artStatus = addArtOnDisplayCb.getText();
            int artistID = artists.size();
            int purchaseID = purchases.size();
            Art newArt = new Art(artID,title,date.toString(),type,style,interpretation,
                    displayStatus,artStatus,price,artistID,purchaseID);
            //Create a method to add stuff to database and take it form there

        }
    }
    private boolean checkContentTf(TextField... textFields){
        boolean hasValue = true;
        for(TextField curTf:textFields){
            if(curTf.getText() == null || curTf.getText()==""){
                hasValue = false;
                break;
            }
        }
        return hasValue;
    }
}