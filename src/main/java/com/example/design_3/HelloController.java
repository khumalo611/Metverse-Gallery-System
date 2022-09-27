package com.example.design_3;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.FXPermission;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Formatter;
import java.util.function.UnaryOperator;

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

    //Add Artwork use case elements
    public Button addArtDialogueBt;
    public Label addArtSelectedLb;
    public CheckBox addArtNewArtist;
    public Label addArtErrorLb;
    // View client Use case
    public TableView clientSearchTable;
    public Button viewClientBt;
    public TableView artistSearchTable;
    public Button updateArtistBt;
    public Button viewArtistBt;
    public Button removeArtistBt;
    public Button addArtistBt;

    // View on display use case
    public Button viewOnDisplayBt;
    public TableView onDisplayTable;
    public Label reqLabel;

    //View Client Use case
    public Pane viewClientPn;
    public TableView clientPurchTable;
    public Label viewClientName;
    public Label viewClientSurname;
    public Label viewClientEmail;
    public Label viewClientPhone;
    public Label viewClientAddress;
    public Pane notificationPage;
    //Confirmation page
    public Label confirmText;

    //Add artist Use case elements

    public TextField addArtistNameTf;
    public TextField addArtistSNameTf;
    public TextField addArtistEmailTf;
    public TextField addArtistCountryTf;
    public TextField addArtistBYearTf;
    public TextField addArtistPseudonymTf;
    public Pane addArtistPn;
    public Label addArtistErrorLb;
    public Label addArtistReqLb;
    public TextField addArtistDYear;
    //Confirmation elements
    public Pane confirmationPage;
    public Button confirmationBackBt;
    public Button confirmationConfirmBt;


    //
    private Pane curContent;
    // Search artwork use case elements
    @FXML
    private TextField artSearchTf;
    public Button artSearchBt;
    public TableView artSearchTable;
    public Button removeArtBt;
    public Button updateArtBt;
    public Button viewArtBt;
    //
    @FXML
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

    public TableColumn artistNames;
    public TableColumn artNamesTb;
    private File curFile;

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
        //updateTable("Select * From Artist;", "Artist");
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
        switchContent("Manager/ArtworkLanding.fxml", "artworkLanding");
        //Setting up the table on the landing page.
        updateTable("Select * From Art", "Art");
        curContent = (Pane) parentBox.getChildren().get(1);
        artSearchTable = (TableView) curContent.lookup("#artSearchTable");
        artNamesTb = new TableColumn<>("Name");
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Art,String>("artTitle"));
        artSearchTable.getColumns().add(artNamesTb);
        artSearchTable.setItems(artworks);
        System.out.println(artworks.size() + " Artworks in table");
    }
    @FXML
    protected void onArtistBtClick(ActionEvent event) throws IOException{
        try {
            //switchContent("Manager/ArtistLanding.fxml","#artistLanding" );
            FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Manager/ArtistLanding.fxml"));
            Scene testScene = new Scene(artPage.load());
            curContent = (Pane) testScene.lookup("#artistLanding");
            parentBox.getChildren().remove(1);
            parentBox.getChildren().add(parentBox.getChildren().size(), curContent);
            //Setting up the table on the landing page.
            updateTable("Select * From Artist", "Artist");
            curContent = (Pane) parentBox.getChildren().get(1);
            artistSearchTable = (TableView) curContent.lookup("#artistSearchTable");
            artNamesTb = new TableColumn<>("Name");
            artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistFName"));
            artistSearchTable.getColumns().add(artNamesTb);
            artistSearchTable.setItems(artists);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onClientBtCLick(ActionEvent event) throws IOException{
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Manager/ClientLanding.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#clientLanding");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
        //Setting up the table on the landing page.
        updateTable("Select * From Client", "Client");
        curContent = (Pane) parentBox.getChildren().get(1);
        clientSearchTable = (TableView) curContent.lookup("#clientSearchTable");
        artNamesTb = new TableColumn<>("Name");
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Client,String>("viewerFName"));
        clientSearchTable.getColumns().add(artNamesTb);
        clientSearchTable.setItems(clients);
    }
    @FXML
    protected void onRequestsBtClick(ActionEvent event) throws IOException
    {
        //This line will replace the commented out paragraph, both do the same thing, but this is obviously cleaner.
        switchContent("Manager/RequestLanding.fxml", "requestLanding");
    }

    @FXML
    //Still working on this one
    protected void onAddArtClickBt(ActionEvent event) throws IOException{
        // addArtBt = (Button) parentBox.lookup("addArtBt");
//        try {
            switchInner("Manager/AddArtwork.fxml", "addArtContent",event);
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
            System.out.println(e.getMessage());
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
    protected void onViewArtistClick(ActionEvent event) throws IOException {
        //Will still enter the code to connect table selection to selected/ curArtist below
        parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
        switchInner("Manager/ViewArtist.fxml", "viewArtistContent", event);
        Artist curArtist = (Artist) artistSearchTable.getSelectionModel().selectedItemProperty().getValue();
        if(curArtist!=null){
            Pane viewArtist = (Pane)parentBox.getChildren().get(1);
            viewArtistFName = (Label) viewArtist.lookup("#viewArtistFName");
            viewArtistLName = (Label) viewArtist.lookup("#viewArtistLName");
            viewArtistPseudonym = (Label) viewArtist.lookup("#viewArtistPseudonym");
            viewArtistEmail = (Label) viewArtist.lookup("#viewArtistEmail");
            viewArtistCountry = (Label) viewArtist.lookup("#viewArtistCountry");
            viewArtistBYear = (Label) viewArtist.lookup("#viewArtistBYear");
            viewArtistDYear = (Label) viewArtist.lookup("#viewArtistDYear");
            //Testing to view Artist from observable list in the window using arbitrary artist.
            viewArtistFName.setText(curArtist.getArtistFName().getValue());
            viewArtistLName.setText(curArtist.getArtistLName().getValue());
            viewArtistPseudonym.setText(curArtist.getArtistPseudonym().getValue());
            viewArtistEmail.setText(curArtist.getArtistEmail().getValue());
            viewArtistCountry.setText(curArtist.artistCountry.getValue());
            viewArtistBYear.setText(String.valueOf(curArtist.getArtistBYear().getValue()));
            viewArtistDYear.setText(String.valueOf(curArtist.getArtistDYear().getValue()));
        }
    }
    private void imageToBytes(File curFile)
    // take image from 'filePath' and converts it into a byte array stored in 'fileName' txt file, in this case, on my desktop
    // Used for debugging
    {
        try {
            BufferedImage curImage = ImageIO.read(curFile);
            WritableRaster imageRaster = curImage.getRaster();
            DataBufferByte imageBuffByte = (DataBufferByte) imageRaster.getDataBuffer();
            byte[] imageData = imageBuffByte.getData();

            System.out.println(Base64.getEncoder().encodeToString(imageData));

            InputStream curStream = new BufferedInputStream(new FileInputStream(curFile));
            File newCopy = new File("src/main/resources/com/example/design_3/Images/" + curFile.getName());
            FileOutputStream streamImage = new FileOutputStream(newCopy.getPath());
            streamImage.write(imageData, 0,curStream.read(imageData));
            this.curFile = newCopy;
            streamImage.close();

            // Code below creates new Text file and writes it out to the text file.
//        File newFile = new File("C:\\Users\\Lwando Macakati\\Desktop\\" + fileName + ".txt");
//        if(newFile.createNewFile()){
//            PrintWriter newWriter = new PrintWriter("C:\\Users\\Lwando Macakati\\Desktop\\" + fileName + ".txt");
//            newWriter.write(Base64.getEncoder().encodeToString(imageData));
//            newWriter.close();
//            System.out.println("Written to text file");
//        }
//        else{
//            System.out.println("Couldn't work right");
//        }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    protected void onChooseImage(ActionEvent event) throws IOException {
        FileChooser fileCh = new FileChooser();
        FileChooser.ExtensionFilter imagesOnly = new FileChooser.ExtensionFilter("Image Files ", "*.png", "*.gif", "*.jpeg", "*.jpg");
        fileCh.getExtensionFilters().addAll(imagesOnly);
        Stage curStage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        fileCh.setTitle("Choose image to upload");
        curFile = fileCh.showOpenDialog(curStage);
        addArtSelectedLb.setText(curFile.getName());
//        imageToBytes();
    }
//    @FXML
//    protected void changeFocus(KeyEvent event){
//        if(event.getCode() == KeyCode.TAB && ((TextField)event.getSource()).getId() == "addArtPriceTf" ){
//        }
//        else if(event.getCode() == KeyCode.TAB && ((TextField)event.getSource()).getId() == "addArtTitleTf" ){
//
//        }
//    }
    @FXML
    protected void fillElements(Event event){
        if(addArtStatusCb.getItems().size() == 0)
        {
            addArtStatusCb.getItems().removeAll();
            addArtStatusCb.getItems().add("Sold");
            addArtStatusCb.getItems().add("In-Stock");
            reqLabel.setTooltip(new Tooltip("All fields marked (*) are required"));
        }
    }

    @FXML
    protected void enableButtons(Event event){
        if(viewClientBt!=null){
            viewClientBt.disableProperty().bind(Bindings.isEmpty(clientSearchTable.getSelectionModel().getSelectedItems()));
        }
        else if(artistSearchTable != null){
            viewArtistBt.disableProperty().bind(Bindings.isEmpty((artistSearchTable.getSelectionModel().getSelectedItems())));
            removeArtistBt.disableProperty().bind(Bindings.isEmpty((artistSearchTable.getSelectionModel().getSelectedItems())));
            updateArtistBt.disableProperty().bind(Bindings.isEmpty((artistSearchTable.getSelectionModel().getSelectedItems())));
        }
        else if(artSearchTable != null){
            viewArtBt.disableProperty().bind(Bindings.isEmpty(artSearchTable.getSelectionModel().getSelectedItems()));
            removeArtBt.disableProperty().bind(Bindings.isEmpty(artSearchTable.getSelectionModel().getSelectedItems()));
            updateArtBt.disableProperty().bind(Bindings.isEmpty(artSearchTable.getSelectionModel().getSelectedItems()));
        }
        else if (addArtPriceTf != null){
            addArtPriceTf.setTextFormatter(setNumFormat());
            addArtTitleTf.setTextFormatter(setTextFormat());
            addArtTypeTf.setTextFormatter(setTextFormat());
            addArtStyleTf.setTextFormatter(setTextFormat());
            addArtInspirationTf.setTextFormatter(setTextFormat());
        }
        else if (addArtistBYearTf !=null){
            addArtistBYearTf.setTextFormatter(setNumFormat());
            addArtistDYear.setTextFormatter(setNumFormat());
            addArtistNameTf.setTextFormatter(setTextFormat());
            addArtistSNameTf.setTextFormatter(setTextFormat());
            addArtistEmailTf.setTextFormatter(setTextFormat());
            addArtistCountryTf.setTextFormatter(setTextFormat());
            addArtistPseudonymTf.setTextFormatter(setTextFormat());
            addArtistReqLb.setTooltip(new Tooltip("All fields marked (*) are required"));
        }
    }
    @FXML
    protected void onAddArtConfirm(ActionEvent event) throws IOException, SQLException
    //Caters for the addition of new artwork in the database when confirm button is clicked on the add Artwork page
    {
        try {
            if (checkContent(addArtTitleTf, addArtDate,addArtTypeTf,addArtStyleTf,addArtStatusCb,addArtSelectedLb)) {
                addArtErrorLb.setText("");
                imageToBytes(curFile);
                String title = addArtTitleTf.getText();
                String date = (addArtDate.getValue()).toString();
                String type = addArtTypeTf.getText();
                String style = addArtStyleTf.getText();
                String interpretation = addArtInspirationTf.getText();
                Double price = Double.parseDouble(addArtPriceTf.getText());
                Boolean displayStatus = addArtOnDisplayCb.isSelected();
                String artStatus = addArtOnDisplayCb.getText();
                Boolean newArtist = addArtNewArtist.isSelected();
                if (!newArtist) {
                    updateTable("Select * From Artist", "Artist");
                    Popup tablePop = new Popup();
                    VBox popupRoot = new VBox();
                    popupRoot.setPadding(new Insets(5));
                    popupRoot.setSpacing(5);
                    popupRoot.setStyle("-fx-background-color: #fafafa;" +
                            "-fx-border-radius: 30px");
                    popupRoot.setAlignment(Pos.CENTER);
                    Label windowName = new Label("Who does this art belong to ?");
                    windowName.setStyle("-fx-background-color: #00968a;" +
                            "-fx-text-fill: #fafafa;" +
                            "-fx-border-width: 80px;");
                    TableView curArtists = new TableView();
                    curArtists.setPrefHeight(200);
                    curArtists.setMinWidth(80);
                    curArtists.getColumns().removeAll();
                    artNamesTb = new TableColumn<>("Name");
                    artNamesTb.setPrefWidth(80);
                    artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistFName"));
                    curArtists.getColumns().add(artNamesTb);
                    curArtists.setItems(artists);
                    Button cancelBt = new Button("Cancel");
                    Button continueBt = new Button("Continue");
                    HBox buttonsHb = new HBox();
                    buttonsHb.setSpacing(10);
                    buttonsHb.setAlignment(Pos.CENTER);
                    buttonsHb.getChildren().addAll(cancelBt,continueBt);
                    continueBt.getStyleClass().add("generalButtons");
                    Label errorMessage = new Label("");
                    errorMessage.setStyle("-fx-text-fill: red;");
                    popupRoot.getChildren().addAll(windowName, curArtists,errorMessage, buttonsHb);
                    tablePop.getContent().add(popupRoot);
                    tablePop.show(((Button)event.getSource()).getScene().getWindow(),250,200);
                    continueBt.setOnAction(event1 -> {
                        Artist curArtist = (Artist) curArtists.getSelectionModel().selectedItemProperty().getValue();
                        if(curArtist !=null){
                            String artistID = "" + curArtist.getArtistID();
                            String purchaseID = null;
                            String imagePath = curFile.getPath();
                            String sqlString = String.format("Insert Into [Art] (Art_Title, Art_Date, Art_type, Art_Style, Interpretation, Display_Status, Sale_Status, Price, Artist_ID, Purchase_ID, Image)\n" +
                                            "Values ('%s', #%s#, '%s', '%s', '%s', '%s', '%s', %s, %s, %s, '%s');", title, date, type, style, interpretation, displayStatus,
                                    artStatus, price, artistID, purchaseID, imagePath);
                            ConnectToDB addArt = new ConnectToDB();
                            addArt.addToDB(sqlString);
                            tablePop.hide();
                            try {
                                switchInner("Notifications.fxml","notificationPage", event);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            errorMessage.setText("Please select an artist before continuing.");
                            curArtists.focusedProperty().addListener((observable, oldValue, newValue) ->{
                                if(newValue)
                                    errorMessage.setText("");
                            });
                        }
                    });
                    cancelBt.setOnAction(event1 -> {
                        tablePop.hide();
                    });
                }
                else{

                }
            }
            else{
                addArtErrorLb.setText("One or more required fields (*) are empty");
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void uploadFile(File curFile){
        //File newFile = curFile.copyFile()
    }
    @FXML
    protected void onSearchArt(Event event)
    // Handles the search for art use-case
    {
        if (artSearchTf.getText() == "") {
            artSearchTf.getStyleClass().add("searchBarError");
            artSearchTf.setPromptText("Please enter name of artwork");
        } else {
            String searchItem = "'*" + artSearchTf.getText() + "*'";
            String sqlString = "Select * From Art Where Art_Title Like " + searchItem;
            updateTable(sqlString, "Art");
            artSearchTable.setItems(artworks);

        }
        artSearchTf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                artSearchTf.getStyleClass().remove(artSearchTf.getStyleClass().size() - 1);
                artSearchTf.setPromptText("Enter artwork name");
            }
        });
    }
    @FXML
    public void onAddArtistClick(ActionEvent event){
        try {
            switchInner("Manager/AddArtist.fxml", "addArtistPn", event);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    private boolean checkContent(Control... inputControls){
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

    @FXML
    protected void onViewOnDisplay(ActionEvent event) throws IOException {
        switchInner("OnDisplay.fxml", "onDisplayContent",event);
        updateTable("Select * From Art Where Display_Status = true", "Art");
        curContent = (Pane) parentBox.getChildren().get(1);
        onDisplayTable = (TableView) curContent.lookup("#onDisplayTable");
        artNamesTb = new TableColumn<>("Name");
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Art,String>("artTitle"));
        onDisplayTable.getColumns().add(artNamesTb);
        onDisplayTable.setItems(artworks);
    }
    private void updateTableView(String sqlString, String tableName, String tableFxId, String tableColumnName, String attributeName){
        updateTable(sqlString, tableName);
        curContent = (Pane)parentBox.getChildren().get(1);
        artistSearchTable = (TableView) curContent.lookup("#" + tableFxId);
        artNamesTb = new TableColumn<>(tableColumnName);
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist,String>(attributeName));
        artistSearchTable.getColumns().add(artNamesTb);
        artistSearchTable.setItems(artists);
    }

    // Methods for retuning back to relavant landing page

    @FXML
    protected void goHome(ActionEvent event){
        try {
            switchInner("Manager/HomePage.fxml", "homeContent", event);
        }
        catch (Exception e){
            System.out.printf("Error switching to home screen");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void goArtist(ActionEvent event){
        try {
            switchInner("Manager/ArtistLanding.fxml", "artistLanding", event);
            updateTable("Select * From Artist", "Artist");
            curContent = (Pane) parentBox.getChildren().get(1);
            artistSearchTable = (TableView) curContent.lookup("#artistSearchTable");
            artNamesTb = new TableColumn<>("Name");
            artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist, String>("artistFName"));
            artistSearchTable.getColumns().add(artNamesTb);
            artistSearchTable.setItems(artists);
        }
        catch (Exception e){
            System.out.printf("Error switching to home screen");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    protected void goArt(ActionEvent event) {
        try {
            switchInner("Manager/ArtworkLanding.fxml", "artworkLanding", event);
            updateTable("Select * From Art", "Art");
            curContent = (Pane) parentBox.getChildren().get(1);
            artSearchTable = (TableView) curContent.lookup("#artSearchTable");
            artNamesTb = new TableColumn<>("Name");
            artNamesTb.setCellValueFactory(new PropertyValueFactory<Art,String>("artTitle"));
            artSearchTable.getColumns().add(artNamesTb);
            artSearchTable.setItems(artworks);
        } catch (Exception e) {
            System.out.printf("Error switching to art screen");
            System.out.println(e.getMessage());
        }
    }

    private int compareItems(Object Comparator, ObservableList objectList ){
        String objectType = Comparator.getClass().getSimpleName();
        int index = -1;
        switch(objectType){
            case "Art":
                Art compArt = (Art) Comparator;
                Art curArt = null;
                for(int i = 0; i<artworks.size();i++){
                    if(compArt.isEqual(curArt))
                        index = i;
                }
                break;
            case "Artist":
                Artist compArtist = (Artist) Comparator;
                Artist curArtist = null;
                for(int i = 0; i<artists.size();i++){
                    if(compArtist.isEqual(curArtist))
                        index = i;
                }
                break;
            case "Client":
                Client compClient = (Client) Comparator;
                Client curClient = null;
                for(int i = 0; i<clients.size();i++){
                    if(compClient.isEqual(curClient))
                        index = i;
                }
                break;
            case "Payment":
                Payment compPayment = (Payment) Comparator;
                Payment curPayment = null;
                for(int i = 0; i<payments.size();i++){
                    if(curPayment.isEqual(compPayment))
                        index = i;
                }
                break;
            case "Purchase":
                Purchase compPurchase = (Purchase) Comparator;
                Purchase curPurchase = null;
                for(int i = 0; i<purchases.size();i++){
                    if(compPurchase.isEqual(curPurchase))
                        index = i;
                }
                break;
            case "Request":
                Request compRequest = (Request) Comparator;
                Request curRequest = null;
                for(int i = 0; i<requests.size();i++){
                    if(compRequest.isEqual(curRequest))
                        index = i;
                }
                break;
            case "Viewer":
                Viewer compViewer = (Viewer) Comparator;
                Viewer curViewer = null;
                for(int i = 0; i<viewers.size();i++){
                    if(compViewer.isEqual(curViewer))
                        index = i;
                }
                break;
            case "Viewing":
                Viewing compViewing = (Viewing) Comparator;
                Viewing curViewing = null;
                for(int i = 0; i<viewings.size();i++){
                    if(compViewing.isEqual(curViewing))
                        index = i;
                }
                break;

        }
        return index;
    }
    @FXML
    protected void onViewClient(ActionEvent event) {
        updateTable("Select * From Client", "Client");
        parentBox = (HBox) ((Button) event.getSource()).getScene().getRoot();
        Client curClient = (Client) clientSearchTable.getSelectionModel().selectedItemProperty().getValue();
        try {
            switchInner("Manager/ViewClient.fxml", "viewClientPn", event);
        } catch (Exception e) {
            System.out.println("Couldn't open Window");
            System.out.println(e.getMessage());
        }
        if (curClient != null) {
            // Get the tings from the Pane
            Pane curPane = (Pane) parentBox.getChildren().get(1);
            viewClientName = (Label) curPane.lookup("#viewClientName");
            viewClientSurname = (Label) curPane.lookup("#viewClientSurname");
            viewClientEmail = (Label) curPane.lookup("#viewClientEmail");
            viewClientPhone = (Label) curPane.lookup("#viewClientPhone");
            viewClientAddress = (Label) curPane.lookup("#viewClientAddress");
            clientPurchTable = (TableView) curPane.lookup("#clientPurchTable");
            // Set the values of those tings
            viewClientName.setText(curClient.getViewerFName());
            viewClientSurname.setText(curClient.getViewerLName());
            viewClientEmail.setText(curClient.getViewerEmail());
            viewClientPhone.setText(curClient.getViewerPhone());
            viewClientAddress.setText(curClient.getClientAddress());
            TableColumn artName = new TableColumn<>("Art");
            TableColumn purchaseDate = new TableColumn("Date Purchased");
            TableColumn purchaseAmount = new TableColumn("Amount");

        }
    }

    @FXML
    protected void onAddArtistConfirm(ActionEvent event){
        updateTable("Select * From Artist", "Artist");
        int curTableSize = artists.size();
        if(checkContent(addArtistNameTf, addArtistSNameTf, addArtistEmailTf,addArtistCountryTf,addArtistBYearTf)){
            addArtistErrorLb.setText("");
            String artistName = addArtistNameTf.getText();
            String artistSName = addArtistSNameTf.getText();
            String artistEmail = addArtistEmailTf.getText();
            String artistCountry = addArtistCountryTf.getText();
            String artistBYear = addArtistBYearTf.getText();
            String artistDYear = addArtistDYear.getText();
            String artistPseudonym = addArtistPseudonymTf.getText();
            ConnectToDB newConnection = new ConnectToDB();
            String sqlString = String.format("Insert Into [Artist] (First_Name, Last_Name, Email, Country, Birth_Year, Death_Year, Pseudonym)\n" +
                            "Values ('%s', '%s', '%s', '%s', %s, %s, '%s');", artistName,artistSName,artistEmail,artistCountry,
                                artistBYear,artistDYear,artistPseudonym);
            newConnection.addToDB(sqlString);
            updateTable("Select * From Artist", "Artist");
            int newTableSize = artists.size();
            if(curTableSize < newTableSize) {
                try {
                    switchInner("Notifications.fxml", "notificationPage", event);
                }
                catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else
                System.out.println("An error occurred, artist not added");
        }
        else{
            addArtistErrorLb.setText("One or more of the required fields (*) is empty");
        }
    }
    @FXML
    protected void onRemoveArt(ActionEvent event){
        String message = "Selected art will be removed.\nContinue?";
        try{
            //parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
            switchInnerConfirmation(event,message);
            System.out.println(parentBox.getChildren().size());
            confirmationConfirmBt = (Button)(parentBox.getChildren().get(1)).lookup("#confirmationConfirmBt");
            confirmationConfirmBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Art curArt = (Art)artSearchTable.getSelectionModel().getSelectedItem();
                    artSearchTable.getItems().remove(curArt);
                    String artName = curArt.getArtTitle();
                    ConnectToDB curConnect = new ConnectToDB();
                    curConnect.removeDb(String.format("Delete From [Art] Where Art_Title = '%s'", artName));
                    try {
                        switchInner("Notifications.fxml", "notificationPage", event);
                    }
                    catch (Exception e){
                        System.out.println("Can't switch to notification page");
                        System.out.println(e.getMessage());
                    }
                }
            });
            confirmationBackBt = (Button)parentBox.getChildren().get(1).lookup("#confirmationBackBt");
            confirmationBackBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    goArt(event);
                }
            });
        }
        catch (Exception e){
            System.out.println("Can't switch to notification Page");
            System.out.println(e.getMessage());
        }
    }

    @FXML
    protected void onRemoveArtist(ActionEvent event){
        String message = "Selected artist will be removed.\nContinue?";
        try{
            //parentBox = (HBox)((Button)event.getSource()).getScene().getRoot();
            switchInnerConfirmation(event,message);
            System.out.println(parentBox.getChildren().size());
            confirmationConfirmBt = (Button)(parentBox.getChildren().get(1)).lookup("#confirmationConfirmBt");
            confirmationConfirmBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Artist curArtist = (Artist)artistSearchTable.getSelectionModel().getSelectedItem();
                    artistSearchTable.getItems().remove(curArtist);
                    String artistName = curArtist.getArtistFName().get();
                    ConnectToDB curConnect = new ConnectToDB();
                    curConnect.removeDb(String.format("Delete From [Artist] Where First_Name = '%s'", artistName));
                    try {
                        switchInner("Notifications.fxml", "notificationPage", event);
                    }
                    catch (Exception e){
                        System.out.println("Can't switch to notification page");
                        System.out.println(e.getMessage());
                    }
                }
            });
            confirmationBackBt = (Button)parentBox.getChildren().get(1).lookup("#confirmationBackBt");
            confirmationBackBt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    goArtist(event);
                }
            });
        }
        catch (Exception e){
            System.out.println("Can't switch to notification Page");
            System.out.println(e.getMessage());
        }
    }
    private TextFormatter setNumFormat(){
        /*
                Code Source: https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
                by Uwe
                on StackOverflow
             */
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if(text.matches("[0-9]*"))
                return change;
            return null;
        };
        TextFormatter numText = new TextFormatter(filter);
        return numText;
    }


    private TextFormatter setTextFormat(){
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if(text.matches(".*'.*"))
                return null;
            return change;
        };
        TextFormatter stringFormat = new TextFormatter(filter);
        return stringFormat;
    }

    private int linkObjects(Object comparator, String compareOn, ObservableList objectList)
    // Compares the object comparator to all objectList objects and returns index of object that matches on compareOn
    {
        int index = -1;
        String objectType = (objectList.get(0)).getClass().getSimpleName();
        Method[] allMethods ;
        Method curMethod = null ;
        switch(objectType){
            case "Art":
                updateTable("Select * From Art", "Art");
                Art compArt = (Art) comparator;
                Art curArt = artworks.get(0);
                allMethods = curArt.getClass().getMethods();
                for (Method method: allMethods){
                    if(method.getName().contains(compareOn));
                    {
                        curMethod = method;
                        break;
                    }
                }
                for(int i = 0; i<artworks.size();i++){
                    try {
                        int curID = (Integer) curMethod.invoke(artworks.get(i));
                        if (curID == (Integer)curMethod.invoke(compArt)) {
                            index = i;
                        }

                    }
                    catch (Exception e){
                        System.out.println("Couldn't invoke the method");
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "Artist":
                updateTable("Select * From Artist", "Artist");
                Artist compArtist = (Artist) comparator;
                Artist curArtist = artists.get(0);
                allMethods = curArtist.getClass().getMethods();
                for (Method method: allMethods){
                    if(method.getName().contains(compareOn));
                    {
                        curMethod = method;
                        break;
                    }
                }
                for(int i = 0; i<artists.size();i++){
                    try {
                        int curID = (Integer) curMethod.invoke(artists.get(i));
                        if (curID == (Integer)curMethod.invoke(compArtist)) {
                            index = i;
                        }

                    }
                    catch (Exception e){
                        System.out.println("Couldn't invoke the method");
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "Client":
                Client compClient = (Client) comparator;
                Client curClient = null;
                for(int i = 0; i<clients.size();i++){
                    if(compClient.isEqual(curClient))
                        index = i;
                }
                break;
            case "Payment":
                Payment compPayment = (Payment) comparator;
                Payment curPayment = null;
                for(int i = 0; i<payments.size();i++){
                    if(curPayment.isEqual(compPayment))
                        index = i;
                }
                break;
            case "Purchase":
                Purchase compPurchase = (Purchase) comparator;
                Purchase curPurchase = null;
                for(int i = 0; i<purchases.size();i++){
                    if(compPurchase.isEqual(curPurchase))
                        index = i;
                }
                break;
            case "Request":
                Request compRequest = (Request) comparator;
                Request curRequest = null;
                for(int i = 0; i<requests.size();i++){
                    if(compRequest.isEqual(curRequest))
                        index = i;
                }
                break;
            case "Viewer":
                Viewer compViewer = (Viewer) comparator;
                Viewer curViewer = null;
                for(int i = 0; i<viewers.size();i++){
                    if(compViewer.isEqual(curViewer))
                        index = i;
                }
                break;
            case "Viewing":
                Viewing compViewing = (Viewing) comparator;
                Viewing curViewing = null;
                for(int i = 0; i<viewings.size();i++){
                    if(compViewing.isEqual(curViewing))
                        index = i;
                }
                break;

        }
        return index;
    }
}