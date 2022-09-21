package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Payment {
    IntegerProperty purchaseID = new SimpleIntegerProperty();
    StringProperty cardOwner = new SimpleStringProperty();

    public Payment(int purchaseID, String cardOwner) {
        this.purchaseID.setValue(purchaseID);
        this.cardOwner.setValue(cardOwner);
    }

    public int getPurchaseID() {
        return purchaseID.get();
    }

    public IntegerProperty purchaseIDProperty() {
        return purchaseID;
    }

    public String getCardOwner() {
        return cardOwner.get();
    }

    public StringProperty cardOwnerProperty() {
        return cardOwner;
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner.set(cardOwner);
    }

    public boolean isEqual(Payment compPayment){
        if(compPayment.getPurchaseID() == this.getPurchaseID())
            return true;
        return false;
    }
}
