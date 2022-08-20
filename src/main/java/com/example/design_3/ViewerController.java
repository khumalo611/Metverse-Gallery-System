package com.example.design_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

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
    protected void onHomeBtClick(ActionEvent event) throws IOException {
        switchContent("Artist/HomePage.fxml","homeContent");
    }
    @FXML
    protected void onBookingBtClick(ActionEvent event) throws IOException {
        switchContent("Viewer/BookingLanding.fxml","bookingContent");
    }
    @FXML
    protected void onBuyBtClick(ActionEvent event) throws IOException {
        switchContent("Viewer/BuyLanding.fxml","buyContent");
    }


    //Switches the content on the display section of the window
    private void switchContent(String targetFxmlPath, String targetContentID) throws IOException {
        FXMLLoader artPage = new FXMLLoader(HelloApplication.class.getResource(targetFxmlPath));
        Scene testScene = new Scene(artPage.load());
        curContent = (Pane)testScene.lookup("#" + targetContentID);
        parentBox.getChildren().remove(1);
        parentBox.getChildren().add(parentBox.getChildren().size(),curContent);
    }


}
