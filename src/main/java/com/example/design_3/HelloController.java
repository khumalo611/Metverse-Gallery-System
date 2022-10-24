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
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.function.UnaryOperator;

import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.text.TextAlignment.CENTER;

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
    public CheckBox addArtStatusCb;
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
    public ScrollPane artScroll;



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


    // Integration variables
    @FXML
    private Button btnLogin, btnRegister;

    @FXML
    private TextField txtEmail, txtPassword;

    @FXML
    private Label txtLoginAlert, txtMRegAlert, txtARegAlert, txtVRegAlert;

    @FXML
    private ToggleButton btnArtistReg, btnManagerReg, btnViewerReg;

    @FXML
    private Button btnMCancel, btnMRegister;

    @FXML
    private TextField txtMCPassword, txtMEmail, txtMFName, txtMLName, txtMNumber, txtMPassword;

    @FXML
    private Pane panelManagerReg;

    @FXML
    private Button btnACancel, btnARegister;

    @FXML
    private TextField txtABirthYear, txtACPassword, txtACountry, txtAEmail, txtAFName, txtALName, txtANumber, txtAPassword;

    @FXML
    private Pane panelArtistReg;

    @FXML
    private Button btnVCancel, btnVRegister;

    @FXML
    private TextField txtCPassword, txtVEmail, txtVFName, txtVLName, txtVNumber, txtVPassword;

    public static int globalArtistID;

    @FXML
    protected void onHomeBtClick(ActionEvent event) throws IOException {
        switchContent("Manager/HomePage.fxml","homeContent");
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

            //System.out.println(Base64.getEncoder().encodeToString(imageData));

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
                Boolean artStatus = addArtStatusCb.isSelected();
                Boolean newArtist = addArtNewArtist.isSelected();
                if (!newArtist) {
                    updateTable("Select * From Artist", "Artist");
                    Stage tablePop = new Stage();
                    VBox popupRoot = new VBox();
                    popupRoot.setPadding(new Insets(5));
                    popupRoot.setSpacing(5);
                    popupRoot.setStyle("-fx-background-color: #fafafa;" +
                            "-fx-border-radius: 30px");
                    popupRoot.setAlignment(Pos.CENTER);
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
                    cancelBt.setStyle("-fx-background-color: #00968a;" +
                            "-fx-text-fill: #fafafa;");
                    cancelBt.prefWidth(80);
                    Button continueBt = new Button("Continue");
                    continueBt.setStyle("-fx-background-color:  #00968a;" +
                            "-fx-text-fill: #fafafa;");
                    continueBt.setPrefWidth(80);
                    HBox buttonsHb = new HBox();
                    buttonsHb.setSpacing(10);
                    buttonsHb.setAlignment(Pos.CENTER);
                    buttonsHb.getChildren().addAll(cancelBt,continueBt);
                    continueBt.getStyleClass().add("generalButtons");
                    Label errorMessage = new Label("");
                    errorMessage.setStyle("-fx-text-fill: red;");
                    popupRoot.getChildren().addAll(curArtists,errorMessage, buttonsHb);
                    tablePop.setScene(new Scene(popupRoot));
                    tablePop.initOwner(((Button)event.getSource()).getScene().getWindow());
                    tablePop.initModality(Modality.WINDOW_MODAL);
                    tablePop.setTitle("Select artist:");
                    tablePop.setWidth(300);
                    tablePop.show();
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
                    Stage artistPop = new Stage();
                    artistPop.initOwner(((Button)event.getSource()).getScene().getWindow());
                    artistPop.initModality(Modality.WINDOW_MODAL);
                    artistPop.setTitle("Who does this artwork belong to?");
                    FXMLLoader curLoader = new FXMLLoader(HelloController.class.getResource("Manager/AddArtist.fxml"));
                    Scene artistScene = new Scene(curLoader.load());
                    artistPop.setScene(artistScene);
                    artistPop.show();
                    Button confirmBt = (Button)artistScene.lookup("#addArtistConfirmBt");
                    confirmBt.setOnAction(event1 -> {
                        updateTable("Select * From Artist", "Artist");
                        int curTableSize = artists.size();
                        addArtistNameTf = (TextField) artistScene.lookup("#addArtistNameTf");
                        addArtistSNameTf = (TextField) artistScene.lookup("#addArtistSNameTf");
                        addArtistEmailTf = (TextField) artistScene.lookup("#addArtistEmailTf");
                        addArtistCountryTf = (TextField) artistScene.lookup("#addArtistCountryTf");
                        addArtistBYearTf = (TextField) artistScene.lookup("#addArtistBYearTf");
                        addArtistDYear = (TextField) artistScene.lookup("#addArtistDYear");
                        addArtistPseudonymTf = (TextField) artistScene.lookup("#addArtistPseudonymTf");
                        addArtistErrorLb = (Label) artistScene.lookup("#addArtistErrorLb");

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
                            Artist curArtist = artists.get(artists.size()-1);
                            int newTableSize = artists.size();
                            if(curTableSize < newTableSize) {
                                try {
                                    if(curArtist !=null){
                                        String artistID = "" + curArtist.getArtistID();
                                        String purchaseID = null;
                                        String imagePath = curFile.getPath();
                                        String sqString = String.format("Insert Into [Art] (Art_Title, Art_Date, Art_type, Art_Style, Interpretation, Display_Status, Sale_Status, Price, Artist_ID, Purchase_ID, Image)\n" +
                                                        "Values ('%s', #%s#, '%s', '%s', '%s', '%s', '%s', %s, %s, %s, '%s');", title, date, type, style, interpretation, displayStatus,
                                                artStatus, price, artistID, purchaseID, imagePath);
                                        ConnectToDB addArt = new ConnectToDB();
                                        addArt.addToDB(sqString);
                                        try {
                                            switchInner("Notifications.fxml","notificationPage", event);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else{
                                        addArtistErrorLb.setText("Please select an artist before continuing.");
                                    }
                                    artistPop.hide();
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
                    });
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
        artScroll = (ScrollPane) curContent.lookup("#artScroll");
        HBox artHolderHb = new HBox();
        artHolderHb.setPrefSize(696,300);
        artHolderHb.setSpacing(30);
        for(Art curArt:artworks){
            updateTable(String.format("Select * from Artist where ArtistID = %d",curArt.getArtistID()),"Artist");
            Artist curArtist = artists.get(0);
            VBox curArtHoldVb = new VBox();
            curArtHoldVb.setPadding(new Insets(10,10,10,10));
            curArtHoldVb.setPrefSize(240,300);
            curArtHoldVb.setSpacing(20);
            curArtHoldVb.setAlignment(Pos.TOP_CENTER);
            Label artName = new Label(curArt.getArtTitle());
            Label artArtist = new Label(String.format("%s %s", curArtist.getArtistFName().get(),curArtist.getArtistLName().get()));
            ImageView artImage = new ImageView();
            artImage.setImage(convertToFxImage(curArt.getArtImage()));
            artImage.setFitHeight(150);
            artImage.setFitWidth(150);
            curArtHoldVb.getChildren().addAll(artImage, artName, artArtist);
            artHolderHb.getChildren().add(curArtHoldVb);
        }
        artScroll.setContent(artHolderHb);
    }

    //region Methods for retuning back to relevant landing page
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
            artworks = getArtSold(curClient);
            TableColumn artName = new TableColumn<>("Art Name");
            TableColumn purchaseAmount = new TableColumn("Amount");
            artName.setCellValueFactory(new PropertyValueFactory<Art,String>("artTitle"));
            purchaseAmount.setCellValueFactory(new PropertyValueFactory<Art,Double>("artPrice"));
            clientPurchTable.getColumns().addAll(artName,purchaseAmount);
            clientPurchTable.setItems(artworks);
        }
    }
    private ObservableList getArtSold(Client curClient){
        ObservableList<Art> curArtworks = FXCollections.observableArrayList();
        updateTable(String.format("Select * from Purchase Where Viewer_ID = %d",curClient.getViewerID()),"Purchase");
        for(Purchase curPurchase: purchases){
           updateTable(String.format("Select * from Art where Purchase_ID = %d",curPurchase.getPurchaseID()), "Art");
           for(Art thisArt:artworks){
               curArtworks.add(thisArt);
           }
        }
        return curArtworks;
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

    private static Image convertToFxImage(BufferedImage image)

        //Source Dan on: https://stackoverflow.com/questions/30970005/bufferedimage-to-javafx-image
    {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
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

    @FXML
    void LoginButtonClick(ActionEvent event) {
        if (txtEmail.getText().isBlank() || txtPassword.getText().isBlank()) {
            txtLoginAlert.setText("Invalid Login! Try Again.");
            txtLoginAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "SELECT COUNT(1) FROM Manager WHERE Email = '" + txtEmail.getText() + "' AND Password = '" + txtPassword.getText() + "'";
                ResultSet queryResult = sqlStatement.executeQuery(sql);

                while(queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        txtLoginAlert.setText("Logging in...");
                        txtLoginAlert.setTextAlignment(CENTER);
                        txtLoginAlert.setTextFill(GREEN);

                        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                        primaryStage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manager/ManagerLanding.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }
                }

                sqlStatement = newConnection.createStatement();
                sql = "SELECT COUNT(1) FROM Artist WHERE Email = '" + txtEmail.getText() + "' AND Password = '" + txtPassword.getText() + "'";
                queryResult = sqlStatement.executeQuery(sql);

                while(queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        txtLoginAlert.setText("Logging in...");
                        txtLoginAlert.setTextAlignment(CENTER);
                        txtLoginAlert.setTextFill(GREEN);

                        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                        primaryStage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Artist/ArtistLanding.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                        sqlStatement = newConnection.createStatement();
                        sql = "SELECT * FROM Artist WHERE Email = '" + txtEmail.getText() + "'";
                        queryResult = sqlStatement.executeQuery(sql);
                        while(queryResult.next()){
                            globalArtistID = queryResult.getInt("ArtistID");
                        }
                    }
                }

                sqlStatement = newConnection.createStatement();
                sql = "SELECT COUNT(1) FROM Viewer WHERE Email = '" + txtEmail.getText() + "' AND Password = '" + txtPassword.getText() + "'";
                queryResult = sqlStatement.executeQuery(sql);

                while(queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        txtLoginAlert.setText("Logging in...");
                        txtLoginAlert.setTextAlignment(CENTER);
                        txtLoginAlert.setTextFill(GREEN);

                        Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
                        primaryStage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Viewer/ViewerLanding.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        txtLoginAlert.setText("Invalid Login! Try Again.");
                        txtLoginAlert.setTextFill(RED);
                    }
                }
                newConnection.close();
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }
    @FXML
    void RegisterButtonClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnRegister.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Registration/RegistrationParent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void ArtistRegBtClick(ActionEvent event) throws IOException {
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Registration/ArtistRegistration.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#panelArtistReg");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    void ManagerRegBtClick(ActionEvent event) throws IOException {
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Registration/ManagerRegistration.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#panelManagerReg");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    void ViewerRegBtClick(ActionEvent event) throws IOException {
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource("Registration/ViewerRegistration.fxml"));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#panelViewerReg");
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
    @FXML
    void MCancelRegBtClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnMCancel.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void MConfirmRegBtClick(ActionEvent event) {
        if (txtMEmail.getText().isBlank() || txtMPassword.getText().isBlank() || txtMFName.getText().isBlank() || txtMLName.getText().isBlank() || txtMNumber.getText().isBlank()) {
            txtMRegAlert.setText("Registration Failed! Try Again.");
            txtMRegAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "INSERT INTO Manager (First_Name, Last_Name, Email, Password, Office_Phone) VALUES ('" + txtMFName.getText() + "','" + txtMLName.getText() + "','" + txtMEmail.getText() + "','" + txtMPassword.getText() + "','" + txtMNumber.getText() + "');";
                sqlStatement.executeUpdate(sql);

                txtMRegAlert.setText("Account Successfully Registered!");
                txtMRegAlert.setTextFill(GREEN);
                newConnection.close();
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    void ACancelRegBtClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnACancel.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AConfirmRegBtClick(ActionEvent event) {
        if (txtAEmail.getText().isBlank() || txtAPassword.getText().isBlank() || txtAFName.getText().isBlank() || txtALName.getText().isBlank() || txtABirthYear.getText().isBlank() || txtACountry.getText().isBlank()) {
            txtARegAlert.setText("Registration Failed! Try Again.");
            txtARegAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "INSERT INTO Artist (First_Name, Last_Name, Email, Password, Country, Birth_Year) VALUES ('" + txtAFName.getText() + "','" + txtALName.getText() + "','" + txtAEmail.getText() + "','" + txtAPassword.getText() + "','" + txtACountry.getText() + "','" + txtABirthYear.getText() + "');";
                sqlStatement.executeUpdate(sql);

                txtARegAlert.setText("Account Successfully Registered!");
                txtARegAlert.setTextFill(GREEN);
                newConnection.close();
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }

    @FXML
    void VCancelRegBtClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnVCancel.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Log-in.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void VConfirmRegBtClick(ActionEvent event) {
        if (txtVEmail.getText().isBlank() || txtVPassword.getText().isBlank() || txtVFName.getText().isBlank() || txtVLName.getText().isBlank() || txtVNumber.getText().isBlank()) {
            txtVRegAlert.setText("Registration Failed! Try Again.");
            txtVRegAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "INSERT INTO Viewer (First_Name, Last_Name, Email, Password, Phone) VALUES ('" + txtVFName.getText() + "','" + txtVLName.getText() + "','" + txtVEmail.getText() + "','" + txtVPassword.getText() + "','" + txtVNumber.getText() + "');";
                sqlStatement.executeUpdate(sql);

                txtVRegAlert.setText("Account Successfully Registered!");
                txtVRegAlert.setTextFill(GREEN);
                newConnection.close();
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }
        }
    }



}