package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Viewing {

    IntegerProperty viewingID = new SimpleIntegerProperty();
    StringProperty viewTime = new SimpleStringProperty();
    IntegerProperty viewCapacity = new SimpleIntegerProperty();
    IntegerProperty viewerID = new SimpleIntegerProperty();
    IntegerProperty artID = new SimpleIntegerProperty();

    public Viewing(int viewingID,String viewTime,
                   int viewCapacity, int viewerID, int artID) {
        this.viewTime.setValue(viewTime);
        this.viewingID.setValue(viewingID);
        this.viewCapacity.setValue(viewCapacity);
        this.viewerID.setValue(viewerID);
        this.artID.setValue(artID);
    }

    public int getViewingID() {
        return viewingID.get();
    }

    public IntegerProperty viewingIDProperty() {
        return viewingID;
    }

    public int getViewerID() {
        return viewerID.get();
    }

    public IntegerProperty viewerIDProperty() {
        return viewerID;
    }

    public int getArtID() {
        return artID.get();
    }

    public IntegerProperty artIDProperty() {
        return artID;
    }

    public String getViewTime() {
        return viewTime.get();
    }

    public StringProperty viewTimeProperty() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime.set(viewTime);
    }

    public int getViewCapacity() {
        return viewCapacity.get();
    }

    public IntegerProperty viewCapacityProperty() {
        return viewCapacity;
    }

    public void setViewCapacity(int viewCapacity) {
        this.viewCapacity.set(viewCapacity);
    }

    public boolean isEqual(Viewing compViewing){
        if(compViewing.getViewingID() == this.getViewingID())
            return true;
        return false;
    }
}
