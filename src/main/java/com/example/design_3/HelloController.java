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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.w3c.dom.Text;
import static javafx.scene.paint.Color.*;
import static javafx.scene.text.TextAlignment.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

public class HelloController {
    @FXML
    private Button btnLogin, btnRegister;

    @FXML
    private TextField txtEmail, txtPassword;

    @FXML
    private Label txtLoginAlert, txtMRegAlert, txtARegAlert, txtVRegAlert;

    @FXML
    private ToggleButton btnArtistReg, btnManagerReg, btnViewerReg;

    @FXML
    private ToggleGroup panelButtons;

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

    @FXML
    private Pane panelViewerReg, curContent;
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
    private TextField artSearchTf;
    private Button addArtBt;
    public TableView artSearchTable;
    public TableColumn artNamesTb;

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
    ObservableList <Artist> artists = FXCollections.observableArrayList();
    ObservableList <Art> artworks = FXCollections.observableArrayList();
    ObservableList <Manager> managers = FXCollections.observableArrayList();
    ObservableList <Viewer> viewers = FXCollections.observableArrayList();
    ObservableList <Client> clients = FXCollections.observableArrayList();
    ObservableList <Payment> payments = FXCollections.observableArrayList();
    ObservableList <Purchase> purchases = FXCollections.observableArrayList();
    ObservableList <Request> requests = FXCollections.observableArrayList();
    ObservableList <Viewing> viewings = FXCollections.observableArrayList();
    private Artist curArtist;

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
        updateTable("Select * From Artist", "Artist");
        curContent = (Pane) parentBox.getChildren().get(1);
        artistSearchTable = (TableView) curContent.lookup("#artistSearchTable");
        artNamesTb = new TableColumn<>("Name");
        artNamesTb.setCellValueFactory(new PropertyValueFactory<Artist,String>("artistFName"));
        artistSearchTable.getColumns().add(artNamesTb);
        artistSearchTable.setItems(artworks);
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
        curArtist = artists.get(2);
        viewArtistFName.setText(curArtist.getArtistFName().getValue());
        viewArtistLName.setText(curArtist.getArtistLName().getValue());
        viewArtistPseudonym.setText(curArtist.getArtistPseudonym().getValue());
        viewArtistEmail.setText(curArtist.getArtistEmail().getValue());
        viewArtistCountry.setText(curArtist.artistCountry.getValue());
        viewArtistBYear.setText(String.valueOf(curArtist.getArtistBYear().getValue()));
        viewArtistDYear.setText(String.valueOf(curArtist.getArtistDYear().getValue()));

    }
    @FXML
    void LoginButtonClick(ActionEvent event) {
        if (txtEmail.getText().isBlank() && txtPassword.getText().isBlank()) {
            txtLoginAlert.setText("Invalid Login! Try Again.");
            txtLoginAlert.setTextAlignment(CENTER);
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
                        txtLoginAlert.setTextAlignment(CENTER);
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void MConfirmRegBtClick(ActionEvent event) {
        if (txtAEmail.getText().isBlank() || txtAPassword.getText().isBlank() || txtAFName.getText().isBlank() || txtALName.getText().isBlank() || txtABirthYear.getText().isBlank() || txtACountry.getText().isBlank()) {
            txtARegAlert.setText("Registration Failed! Try Again.");
            txtARegAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "INSERT INTO Manager (First_Name, Last_Name, Email, Password, Country, Birth_Year) VALUES ('" + txtAFName.getText() + "','" + txtALName.getText() + "','" + txtAEmail.getText() + "','" + txtAPassword.getText() + "','" + txtACountry.getText() + "','" + txtABirthYear.getText() + "');";
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
    void ACancelRegBtClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnACancel.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void AConfirmRegBtClick(ActionEvent event) {
        if (txtMEmail.getText().isBlank() || txtMPassword.getText().isBlank() || txtMFName.getText().isBlank() || txtMLName.getText().isBlank() || txtMNumber.getText().isBlank()) {
            txtMRegAlert.setText("Registration Failed! Try Again.");
            txtMRegAlert.setTextFill(RED);
        } else {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            try {
                Connection newConnection = DriverManager.getConnection(dataBaseURL);
                System.out.println("Connected to MS Access database");
                Statement sqlStatement = newConnection.createStatement();
                String sql = "INSERT INTO Artist (First_Name, Last_Name, Email, Password, Office_Phone) VALUES ('" + txtMFName.getText() + "','" + txtMLName.getText() + "','" + txtMEmail.getText() + "','" + txtMPassword.getText() + "','" + txtMNumber.getText() + "');";
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
    void VCancelRegBtClick(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) btnVCancel.getScene().getWindow();
        primaryStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
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