package com.example.design_3;

import javafx.beans.property.*;


public class Request {
    IntegerProperty requestID = new SimpleIntegerProperty();
    StringProperty reqDate = new SimpleStringProperty();
    StringProperty reqMessage = new SimpleStringProperty();
    StringProperty reqResponse = new SimpleStringProperty();
    IntegerProperty managerID = new SimpleIntegerProperty();
    IntegerProperty artistID = new SimpleIntegerProperty();

    public Request(int requestID, String reqDate, String reqMessage,
                   String reqResponse, int managerID, int artistID) {
        this.requestID.setValue(requestID);
        this.reqDate.setValue(reqDate);
        this.reqMessage.setValue(reqMessage);
        this.reqResponse.setValue(reqResponse);
        this.managerID.setValue(managerID);
        this.artistID.setValue(artistID);
    }

    public int getRequestID() {
        return requestID.get();
    }

    public IntegerProperty requestIDProperty() {
        return requestID;
    }

    public int getManagerID() {
        return managerID.get();
    }

    public IntegerProperty managerIDProperty() {
        return managerID;
    }

    public int getArtistID() {
        return artistID.get();
    }

    public IntegerProperty artistIDProperty() {
        return artistID;
    }

    public String getReqDate() {
        return reqDate.get();
    }

    public StringProperty reqDateProperty() {
        return reqDate;
    }

    public void setReqDate(String reqDate) {
        this.reqDate.set(reqDate);
    }

    public String getReqMessage() {
        return reqMessage.get();
    }

    public StringProperty reqMessageProperty() {
        return reqMessage;
    }

    public void setReqMessage(String reqMessage) {
        this.reqMessage.set(reqMessage);
    }

    public String isReqResponse() {
        return reqResponse.get();
    }

    public StringProperty reqResponseProperty() {
        return reqResponse;
    }

    public String getReqResponse() {
        return reqResponse.get();
    }

    public void setReqResponse(String reqResponse) {
        this.reqResponse.set(reqResponse);
    }

    public boolean isEqual(Request compRequest){
        if(compRequest.getRequestID() == this.getRequestID())
            return true;
        return false;
    }
}
