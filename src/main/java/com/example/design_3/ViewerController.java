package com.example.design_3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static javafx.scene.paint.Color.GREEN;

public class ViewerController {

    @FXML
    public ToggleButton homeBt;
    @FXML
    public ToggleButton bookingBt;
    @FXML
    public ToggleButton buyBt;
    @FXML
    public HBox parentBox;
    private Pane curContent;
    @FXML
    private Label lblBookAlert;

    // Integration UseCases
    @FXML
    private TableView artBuyTable;
    public TableColumn artTitleCol;
    public TableColumn artistIDColB;
    //Confirmation page
    public Label confirmText;

    ObservableList<Artist> artists = FXCollections.observableArrayList();
    ObservableList <Art> artworks = FXCollections.observableArrayList();
    ObservableList <Manager> managers = FXCollections.observableArrayList();
    ObservableList <Viewer> viewers = FXCollections.observableArrayList();
    ObservableList <Client> clients = FXCollections.observableArrayList();
    ObservableList <Payment> payments = FXCollections.observableArrayList();
    ObservableList <Purchase> purchases = FXCollections.observableArrayList();
    ObservableList <Request> requests = FXCollections.observableArrayList();
    ObservableList <Viewing> viewings = FXCollections.observableArrayList();

    @FXML
    protected void onHomeBtClick(ActionEvent event) throws IOException {
        switchContent("Artist/HomePage.fxml","homeContent");
    }
    @FXML
    protected void onBookingBtClick(ActionEvent event) throws IOException {
        switchContent("Viewer/BookingLanding.fxml","bookingContent");
    }

    @FXML
    void onConfirmBooking(ActionEvent event) {
        lblBookAlert.setTextFill(GREEN);
    }

    //Switches the content on the display section of the window
    private void switchContent(String targetFxmlPath, String targetContentID) throws IOException {
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource(targetFxmlPath));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#" + targetContentID);
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }

    @FXML
    void onBuyBtClick(ActionEvent event) throws IOException {
        switchContent("Viewer/BuyLanding.fxml", "buyContent");

        updateTable("Select * From Art WHERE Display_Status = Yes;", "Art");
        artBuyTable = (TableView) curContent.lookup("#artBuyTable");
        artistIDColB = new TableColumn<>("Artist ID");
        artTitleCol = new TableColumn<>("Art Title");
        artistIDColB.setCellValueFactory(new PropertyValueFactory<Art,String>("artistID"));
        artTitleCol.setCellValueFactory(new PropertyValueFactory<Art,String>("artTitle"));
        artBuyTable.getColumns().add(artistIDColB);
        artBuyTable.getColumns().add(artTitleCol);
        artBuyTable.setItems(artworks);
    }

    @FXML
    void onConfirmBuyArt(ActionEvent event) throws IOException {
        parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
        FXMLLoader paymentPage = new FXMLLoader(HelloApplication.class.getResource("Viewer/MakePayment.fxml"));
        Scene testScene = new Scene(paymentPage.load());
        curContent = (Pane)testScene.lookup("#makePaymentPn");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
        Request currentArt = (Request) artBuyTable.getSelectionModel().selectedItemProperty().getValue();
        if(currentArt != null){

        }
    }

    private void switchInnerConfirmation(ActionEvent event, String message) throws IOException{
        // Handles the switching of content window caused outside of nave Pane, for notification window

        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Confirmation.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#confirmationPage");
        confirmText = (Label) curContent.lookup("#confirmText");
        confirmText.setText(message);
        parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }

    private void updateTable(String sqlString, String tableName)
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
                        File imageFile = new File(allNodes.getString("Image"));
                        BufferedImage artImage = ImageIO.read(imageFile);
                        Art newArt = new Art(artID, artTitle,artDate,artType,artStyle,
                                artInterpretation,artDisplayStatus,artSaleStatus,artPrice,artistID,purchaseID,artImage);
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
                        String artistPseudonym = allNodes.getString("Pseudonym");
                        int artistDYear = allNodes.getInt("Death_Year");
                        Artist newArtist = new Artist(artistID,artistFName,artistLName,artistEmail,artistCountry,artistBYear,
                                artistPassword,artistPseudonym,artistDYear);
                        artists.add(newArtist);
                        System.out.println(newArtist);//Debugging
                    }
                    break;
                case "Client":
                    clients.clear();
                    updateTable("Select * From Viewer", "Viewer");
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
                            Client newClient = new Client(curViewer.getViewerID(),curViewer.getViewerType(),curViewer.getViewerPassword(),
                                    curViewer.getViewerFName(),curViewer.getViewerLName(),curViewer.getViewerEmail(),curViewer.getViewerPhone());
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
                        String managerOfficeNum = allNodes.getString("Office_Phone");
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
                        String requestResponse = allNodes.getString("Response");
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
                        Viewing newViewing = new Viewing(viewingID,viewingTimeslot,viewingCapacity);
                        viewings.add(newViewing);
                        System.out.println(newViewing);
                    }
                    break;
            }
            newConnection.close();
        }
        catch (Exception e){
            System.out.println("Connection failed.");
            System.out.println(e.getMessage());
        }
    }

}
