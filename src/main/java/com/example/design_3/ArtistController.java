package com.example.design_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Date;

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

        }
        catch(Exception e){
            System.out.println("Couldn't switch to request meeting");
            System.out.println(e.getMessage());
        }
    }
    @FXML
    protected void onRequestMeetingConfirm(ActionEvent event){
        if(checkContent(requestMessageTf,requestDateDp)){
            String message = requestMessageTf.getText();
            String date = requestDateDp.getValue().toString();
            ConnectToDB curConnection = new ConnectToDB();
            curConnection.addToDB("");
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

