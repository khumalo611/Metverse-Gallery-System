package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Viewer {
    IntegerProperty viewerID = new SimpleIntegerProperty();
    StringProperty viewerType = new SimpleStringProperty();
    StringProperty viewerPassword = new SimpleStringProperty();
    StringProperty viewerFName = new SimpleStringProperty();
    StringProperty viewerLName = new SimpleStringProperty();
    StringProperty viewerEmail = new SimpleStringProperty();
    StringProperty viewerPhone = new SimpleStringProperty();

    public Viewer(int viewerID, String viewerType, String viewerPassword,
                  String viewerFName, String viewerLName, String viewerEmail, String viewerPhone) {
        this.viewerID.setValue(viewerID);
        this.viewerType.setValue(viewerType);
        this.viewerPassword.setValue(viewerPassword);
        this.viewerFName.setValue(viewerFName);
        this.viewerLName.setValue(viewerLName);
        this.viewerEmail.setValue(viewerEmail);
        this.viewerPhone.setValue(viewerPhone);
    }

    public int getViewerID() {
        return viewerID.get();
    }

    public IntegerProperty viewerIDProperty() {
        return viewerID;
    }

    public String getViewerType() {
        return viewerType.get();
    }

    public StringProperty viewerTypeProperty() {
        return viewerType;
    }

    public void setViewerType(String viewerType) {
        this.viewerType.set(viewerType);
    }

    public String getViewerPassword() {
        return viewerPassword.get();
    }

    public StringProperty viewerPasswordProperty() {
        return viewerPassword;
    }

    public void setViewerPassword(String viewerPassword) {
        this.viewerPassword.set(viewerPassword);
    }

    public String getViewerFName() {
        return viewerFName.get();
    }

    public StringProperty viewerFNameProperty() {
        return viewerFName;
    }

    public void setViewerFName(String viewerFName) {
        this.viewerFName.set(viewerFName);
    }

    public String getViewerLName() {
        return viewerLName.get();
    }

    public StringProperty viewerLNameProperty() {
        return viewerLName;
    }

    public void setViewerLName(String viewerLName) {
        this.viewerLName.set(viewerLName);
    }

    public String getViewerEmail() {
        return viewerEmail.get();
    }

    public StringProperty viewerEmailProperty() {
        return viewerEmail;
    }

    public void setViewerEmail(String viewerEmail) {
        this.viewerEmail.set(viewerEmail);
    }

    public String getViewerPhone() {
        return viewerPhone.get();
    }

    public StringProperty viewerPhoneProperty() {
        return viewerPhone;
    }

    public void setViewerPhone(String viewerPhone) {
        this.viewerPhone.set(viewerPhone);
    }
    @Override
    public String toString(){
        return String.format("%s   %s    %s    %s    %s     %s    %s",viewerID.getValue(),
                getViewerType(),getViewerPassword(),getViewerFName(),getViewerLName(),getViewerEmail(),
                getViewerPhone());
    }

    public boolean isEqual(Viewer compViewer){
        if(compViewer.getViewerID() == this.getViewerID())
            return true;
        return false;
    }
}

