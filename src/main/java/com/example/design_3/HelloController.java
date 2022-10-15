package com.example.design_3;

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
import javafx.stage.Stage;
import static javafx.scene.paint.Color.*;
import static javafx.scene.text.TextAlignment.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
    public Pane contentPn;
    @FXML
    public HBox parentBox;

    public HelloController(){
        //connectToDB();
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
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