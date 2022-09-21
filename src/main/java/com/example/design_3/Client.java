package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends Viewer{
    StringProperty clientAddress = new SimpleStringProperty();

    public Client(int viewerID, String viewerType, String viewerPassword, String viewerFName, String viewerLName, String viewerEmail, String viewerPhone) {
        super(viewerID, viewerType, viewerPassword, viewerFName, viewerLName, viewerEmail, viewerPhone);
    }

    public String getClientAddress() {
        return clientAddress.get();
    }
    public void setClientAddress(String clientAddress) {
        this.clientAddress.set(clientAddress);
    }
    @Override
    public String toString(){
        return String.format("%s   %s    %s    %s    %s     %s    %s    %s",viewerID.getValue(),
                getViewerType(),getViewerPassword(),getViewerFName(),getViewerLName(),getViewerEmail(),
                getViewerPhone(),getClientAddress());
    }

    public boolean isEqual(Client otherClient){
        if(otherClient.getViewerID() == this.getViewerID())
            return true;
        return false;
    }
}
