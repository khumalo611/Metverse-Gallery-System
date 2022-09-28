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
import java.sql.*;

public class ArtistController {


    @FXML
    public ToggleButton homeBt;
    @FXML
    public ToggleButton artStatusBt;
    @FXML
    public ToggleButton requestsBt;
    @FXML
    public Pane contentPn;
    @FXML
    public HBox parentBox;
    //Confirmation & Notification elements
    public Label confirmText;
    public Button confirmationBackBt;
    public Button confirmationConfirmBt;
    public Pane notificationPage;

    //Request meeting use case elements
    public Pane requestMeetingPn;
    public Label requestMErrorLb;
    public DatePicker requestDateDp;
    public TextField requestMessageTf;
    public TableView requestMeetingTb;
    public Label selectedManagerLb;

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

    Artist curArtist = new Artist(6,"Lwando","Macakati","LwandosFakemail@gmail.com","South Africa",
            1990, "Passy","fakePseudo", 2085);
    private Pane curContent;

    @FXML
    protected void onHomeBtClick(ActionEvent event) throws IOException {
        switchContent("Artist/HomePage.fxml","homeContent");
    }
    @FXML
    protected void onArtStatusBtClick(ActionEvent event) throws IOException {
        switchContent("Artist/ArtStatusLanding.fxml","artStatusLanding");
    }
    @FXML
    protected void onRequestsBtClick(ActionEvent event) throws IOException {
        switchContent("Artist/RequestLanding.fxml","requestLanding");

    }
    @FXML
    protected void onRequestMeeting(ActionEvent event){
        try {
            switchInner("Artist/RequestMeeting.fxml", "requestMeetingPn", event);
            updateTable("Select * From Manager","Manager");
            TableColumn managerName = new TableColumn<>("Name");
            TableColumn managerSName = new TableColumn("Surname");
            managerName.setCellValueFactory(new PropertyValueFactory<Manager,String>("managerFName"));
            managerSName.setCellValueFactory(new PropertyValueFactory<Manager, String>("managerLName"));
            requestMeetingTb = (TableView) parentBox.getChildren().get(1).lookup("#requestMeetingTb");
            requestMeetingTb.getColumns().addAll(managerName,managerSName);
            requestMeetingTb.setItems(managers);
            requestMeetingTb.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) ->{
                Manager curManager = (Manager)requestMeetingTb.getSelectionModel().getSelectedItem();
                selectedManagerLb = (Label) parentBox.getChildren().get(1).lookup("#selectedManagerLb");
                selectedManagerLb.setText(curManager.getManagerFName() + " " + curManager.getManagerLName());
            } ));

        }
        catch(Exception e){
            System.out.println("Couldn't switch to request meeting");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    protected void onRequestMeetingConfirm(ActionEvent event){
        if(checkContent(requestMessageTf,requestDateDp,selectedManagerLb)){
            requestMErrorLb.setText("");
            String message = requestMessageTf.getText();
            String date = requestDateDp.getValue().toString();
            Manager curManager = (Manager) requestMeetingTb.getSelectionModel().getSelectedItem();
            String managerID = curManager.getManagerID() + "";
            String artistID = curArtist.getArtistID()+ "";
            ConnectToDB curConnection = new ConnectToDB();
            String sqlString = String.format("Insert Into Request ([Date], Message, Manager_ID, Artist_ID) Values (#%s#,'%s',%s,%s);",
                    date,message,managerID, artistID);
            curConnection.addToDB(sqlString);
            try {
                switchInner("Notifications.fxml", "notificationPage", event);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
        else {
            requestMErrorLb.setText("All fields are required.");
        }
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
            System.out.println(e.getMessage());
        }
    }
    //Switches the content on the display section of the window
    private void switchContent(String targetFxmlPath, String targetContentID) throws IOException{
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
    private boolean checkContent(Control... inputControls){
        // returns true if all controls have elements
        boolean hasContent = true;
        for(Control curControl:inputControls){
            String controlType = curControl.getTypeSelector();
            switch(controlType){
                case "TextField":
                    if(((TextField)curControl).getText().isEmpty())
                        hasContent = false;
                    break;
                case "DatePicker":
                    if (((DatePicker)curControl).getValue() == null)
                        hasContent = false;
                    break;
                case "ChoiceBox":
                    if (((ChoiceBox)curControl).getValue() == null)
                        hasContent = false;
                    break;
                case "Label":
                    if(((Label)curControl).getText().isEmpty())
                    {
                        System.out.println(((Label) curControl).getText());
                        hasContent = false;
                    }
                    break;
            }
            if(!hasContent)
                break;
        }
        return hasContent;
    }
}

