package com.example.design_3;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.FXPermission;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDate;
import java.util.Base64;

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
    // View client Use case
    public TableView clientSearchTable;
    public Button viewClientBt;
    public TableView artistSearchTable;
    public Button updateArtistBt;
    public Button viewArtistBt;
    public Button removeArtistBt;
    public Button addArtistBt;


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
    private void imageToBytes(String filePath, String fileName) throws IOException
    // take image from 'filePath' and converts it into a byte array stored in 'fileName', in this case, on my desktop
    // Used for debugging
    {
        BufferedImage curImage = ImageIO.read(curFile);
        WritableRaster imageRaster = curImage.getRaster();
        DataBufferByte imageBuffByte = (DataBufferByte) imageRaster.getDataBuffer();
        byte[] imageData = imageBuffByte.getData();

        System.out.println(Base64.getEncoder().encodeToString(imageData));
        File newFile = new File("C:\\Users\\Lwando Macakati\\Desktop\\" + fileName + ".txt");
        if(newFile.createNewFile()){
            PrintWriter newWriter = new PrintWriter("C:\\Users\\Lwando Macakati\\Desktop\\" + fileName + ".txt");
            newWriter.write(Base64.getEncoder().encodeToString(imageData));
            newWriter.close();
            System.out.println("Written to text file");
        }
        else{
            System.out.println("Couldn't work right");
        }
    }
    @FXML
    protected void onChooseImage(ActionEvent event) throws IOException {
        FileChooser fileCh = new FileChooser();
        Stage fileExplorer = new Stage();
        fileExplorer.initOwner(((Button)event.getSource()).getScene().getWindow());
        fileExplorer.initModality(Modality.APPLICATION_MODAL);
        curFile = fileCh.showOpenDialog(fileExplorer);
        addArtSelectedLb.setText(curFile.getName());
        //imageToBytes(curFile.getName(),"Image1");
    }
    @FXML
    protected void fillElements(Event event){
        boolean accessed = false;
        if(addArtStatusCb.getItems().size() == 0 ) {
            addArtStatusCb.getItems().add("Sold");
            addArtStatusCb.getItems().add("Test");
        }

    }
    @FXML
    protected void onViewClient(ActionEvent event){
        Client curClient = (Client) clientSearchTable.getSelectionModel().selectedItemProperty().getValue();
        if(curClient!=null){
            //TBC
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
    }
    @FXML
    protected void onAddArtConfirm(ActionEvent event) throws IOException, SQLException
    //Caters for the addition of new artwork in the database when confirm button is clicked on the add Artwork page
    {
        if(checkContentTf() && addArtDate.getValue() != null){
            String title = addArtTitleTf.getText();
            String date = (addArtDate.getValue()).toString();
            String type = addArtTypeTf.getText() ;
            String style = addArtStyleTf.getText();
            String interpretation = addArtInspirationTf.getText();
            Double price = Double.parseDouble(addArtPriceTf.getText());
            Boolean displayStatus = addArtOnDisplayCb.isSelected();
            String artStatus = addArtOnDisplayCb.getText();
            String artistID = "4";//Need to add functionality to search for artist
            String purchaseID = "1";
            String imagePath = curFile.getPath();
            String sqlString =String.format("Insert into Art (Art_Title, Art_Date, Art_type, Art_Style, Interpretation, Display_Status, Sale_Status, Price, Artist_ID, Purchase_ID, Image)\n" +
                    "Values ('%s', #%s#, '%s', '%s', '%s', '%s', '%s', %s, %s, %s, '%s');",title,date,type,style,interpretation,displayStatus,
                    artStatus, price, artistID,purchaseID,imagePath);
            ConnectToDB addArt = new ConnectToDB();
            addArt.addToDB(sqlString);
            addArt.close();
        }
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


    private void updateTableView(String sqlString, String tableName, String tableFxId, String tableColumnName, String attributeName){
        updateTable(sqlString, tableName);
        curContent = (Pane)parentBox.getChildren().get(1);
        artistSearchTable = (TableView) curContent.lookup("#" + tableFxId);
        artNamesTb = new TableColumn<>(tableColumnName);
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist,String>(attributeName));
        artistSearchTable.getColumns().add(artNamesTb);
        artistSearchTable.setItems(artists);
    }
}