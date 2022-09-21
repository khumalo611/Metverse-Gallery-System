package com.example.design_3;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Purchase {
    IntegerProperty purchaseID = new SimpleIntegerProperty();
    DoubleProperty purchaseAmount = new SimpleDoubleProperty();
    IntegerProperty viewerID = new SimpleIntegerProperty();

    public Purchase(int purchaseID,
                    double purchaseAmount, int viewerID) {
        this.purchaseID.setValue(purchaseID);
        this.purchaseAmount.setValue(purchaseAmount);
        this.viewerID.setValue(viewerID);
    }

    public int getPurchaseID() {
        return purchaseID.get();
    }

    public IntegerProperty purchaseIDProperty() {
        return purchaseID;
    }

    public int getViewerID() {
        return viewerID.get();
    }

    public IntegerProperty viewerIDProperty() {
        return viewerID;
    }

    public double getPurchaseAmount() {
        return purchaseAmount.get();
    }

    public DoubleProperty purchaseAmountProperty() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(double purchaseAmount) {
        this.purchaseAmount.set(purchaseAmount);
    }

    public boolean isEqual(Purchase compPurchase){
        if(compPurchase.getPurchaseID() == this.getPurchaseID())
            return true;
        return false;
    }
}
