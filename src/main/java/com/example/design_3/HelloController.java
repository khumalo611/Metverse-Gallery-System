package com.example.design_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
public class HelloController {
    // This is the controller for the Manager's page. This controls all events that take place for the Manager

    @FXML
    public Pane artworkLanding;
    @FXML
    public Button homeBt;
    @FXML
    public Button artworkBt;
    @FXML
    public Button artistBt;
    @FXML
    public Button clientBt;
    @FXML
    public Button requestBt;
    @FXML
    public Pane contentPn;
    @FXML
    public Pane homeContent;
    @FXML
    public HBox parentPane;
    @FXML
    public HBox parentBox;
    private TextField artSearchTf;
    private Pane curContent;

    @FXML
    protected void onHomeBtClick(ActionEvent event) throws IOException
    // Event handler for when home button is clicked
    {
        //creates an fxmlloader instance using the fxml file from the manager folder in resources
        FXMLLoader homePage = new FXMLLoader(HelloApplication.class.getResource("Manager/HomePage.fxml"));
        // Loads the file and creates an instance of a scene from that fxml
        Scene testScene = new Scene(homePage.load());
        // curContent are the names of all the content panes that we will be switching to. This uses a cached reference
        // to get the new content window we want displayed and saves it in curContent.
        curContent = (Pane)testScene.lookup("#homeContent");
        //ParentPane is the root of the main window which is the landing page, so this takes the second child of that pane
        // (which in this case is our display empty content window) and removes it.
        parentPane.getChildren().remove(contentPn);
        // This replaces the removed content window with the new content window we saved as curContent
        parentPane.getChildren().add(parentPane.getChildren().size(),curContent);

        // The others use the same code just copied and pasted across, I might create a method to clean this up so that
        // you just enter the destination fxml path and fx id of the element we want displayed then, boom.
    }

    @FXML
    protected void onArtBtClick(ActionEvent event) throws IOException{
        switchContent("Manager/ArtworkLanding.fxml", "artworkLanding");
    }
    @FXML
    protected void onArtistBtClick(ActionEvent event) throws IOException{
        switchContent("Manager/ArtistLanding.fxml", "artistLanding");
    }
    @FXML
    protected void onClientBtCLick(ActionEvent event) throws IOException{
        switchContent("Manager/ClientLanding.fxml", "clientLanding");
    }
    @FXML
    protected void onRequestsBtClick(ActionEvent event) throws IOException
    {
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
        switchContent("Manager/AddArtwork.fxml", "addArtContent");
    }
    // Will replace the whole paragraph that switches content with the one line you see in the
    // onRequestBtClick method
    private void switchContent(String targetFxmlPath, String targetContentID) throws IOException{
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource(targetFxmlPath));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#" + targetContentID);
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }
}
