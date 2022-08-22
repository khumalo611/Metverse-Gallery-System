package com.example.design_3;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.Date;

public class Art {
    IntegerProperty artID = new SimpleIntegerProperty();
    StringProperty artTitle = new SimpleStringProperty();
    StringProperty artDate = new SimpleStringProperty();
    StringProperty artType = new SimpleStringProperty();
    StringProperty artStyle = new SimpleStringProperty();
    StringProperty artInterpretation = new SimpleStringProperty();
    boolean displayStatus;
    StringProperty artSaleStatus = new SimpleStringProperty();
    DoubleProperty artPrice = new SimpleDoubleProperty();
    IntegerProperty artistID = new SimpleIntegerProperty();
    IntegerProperty purchaseID = new SimpleIntegerProperty();
    BufferedImage artImage;
    //Image artImage;

    public BufferedImage getArtImage() {
        return artImage;
    }

    public Art(int artID, String artTitle, String artDate,
               String artType, String artStyle, String artInterpretation,
               boolean displayStatus, String artSaleStatus, Double artPrice, int artistID,
               int purchaseID, BufferedImage artImage){

        this.artID.setValue(artID);
        this.artTitle.setValue(artTitle);
        this.artDate.setValue(artDate);
        this.artType.setValue(artType);
        this.artStyle.setValue(artStyle);
        this.artInterpretation.setValue(artInterpretation);
        this.displayStatus = displayStatus;
        this.artSaleStatus.setValue(artSaleStatus);
        this.artPrice.setValue(artPrice);
        this.artistID.setValue(artistID);
        this.purchaseID.setValue(purchaseID);
        this.artImage = artImage;
    }

    public int getArtID() {
        return artID.get();
    }

    public IntegerProperty artIDProperty() {
        return artID;
    }

    public int getArtistID() {
        return artistID.get();
    }

    public IntegerProperty artistIDProperty() {
        return artistID;
    }

    public int getPurchaseID() {
        return purchaseID.get();
    }

    public IntegerProperty purchaseIDProperty() {
        return purchaseID;
    }

    public String getArtTitle() {
        return artTitle.get();
    }

    public StringProperty artTitleProperty() {
        return artTitle;
    }

    public void setArtTitle(String artTitle) {
        this.artTitle.set(artTitle);
    }

    public String getArtDate() {
        return artDate.get();
    }

    public StringProperty artDateProperty() {
        return artDate;
    }

    public void setArtDate(String artDate) {
        this.artDate.set(artDate);
    }

    public String getArtType() {
        return artType.get();
    }

    public StringProperty artTypeProperty() {
        return artType;
    }

    public void setArtType(String artType) {
        this.artType.set(artType);
    }

    public String getArtStyle() {
        return artStyle.get();
    }

    public StringProperty artStyleProperty() {
        return artStyle;
    }

    public void setArtStyle(String artStyle) {
        this.artStyle.set(artStyle);
    }

    public String getArtInterpretation() {
        return artInterpretation.get();
    }

    public StringProperty artInterpretationProperty() {
        return artInterpretation;
    }

    public void setArtInterpretation(String artInterpretation) {
        this.artInterpretation.set(artInterpretation);
    }

    public boolean isDisplayStatus() {
        return displayStatus;
    }

    public void setDisplayStatus(boolean displayStatus) {
        this.displayStatus = displayStatus;
    }

    public String getArtSaleStatus() {
        return artSaleStatus.get();
    }

    public StringProperty artSaleStatusProperty() {
        return artSaleStatus;
    }

    public void setArtSaleStatus(String artSaleStatus) {
        this.artSaleStatus.set(artSaleStatus);
    }

    public double getArtPrice() {
        return artPrice.get();
    }

    public DoubleProperty artPriceProperty() {
        return artPrice;
    }

    public void setArtPrice(double artPrice) {
        this.artPrice.set(artPrice);
    }
    @Override
    public String toString(){
        return String.format("%s    %s    %s    %s    %s    %s    %s    %s    %s    %s    %s",
                getArtID(),getArtTitle(),getArtDate(),getArtType(),getArtStyle(),getArtInterpretation(),isDisplayStatus(),
                getArtSaleStatus(),getArtPrice(),getArtID(),getPurchaseID(),getArtImage());
    }
}
