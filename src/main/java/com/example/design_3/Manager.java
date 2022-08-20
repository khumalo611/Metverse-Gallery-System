package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Manager {
    IntegerProperty managerID = new SimpleIntegerProperty();
    StringProperty managerFName = new SimpleStringProperty();
    StringProperty managerLName = new SimpleStringProperty();
    StringProperty managerEmail = new SimpleStringProperty();
    StringProperty managerPassword = new SimpleStringProperty();
    StringProperty managerOfficeNum = new SimpleStringProperty();

    public Manager(int managerID, String managerFName, String managerLName,
                   String managerEmail, String managerPassword, String managerOfficeNum) {
        this.managerID.setValue(managerID);
        this.managerFName.setValue(managerFName);
        this.managerLName.setValue(managerLName);
        this.managerEmail.setValue(managerEmail);
        this.managerPassword.setValue(managerPassword);
        this.managerOfficeNum.setValue(managerOfficeNum);
    }

    public int getManagerID() {
        return managerID.get();
    }

    public IntegerProperty managerIDProperty() {
        return managerID;
    }

    public String getManagerFName() {
        return managerFName.get();
    }

    public StringProperty managerFNameProperty() {
        return managerFName;
    }

    public void setManagerFName(String managerFName) {
        this.managerFName.set(managerFName);
    }

    public String getManagerLName() {
        return managerLName.get();
    }

    public StringProperty managerLNameProperty() {
        return managerLName;
    }

    public void setManagerLName(String managerLName) {
        this.managerLName.set(managerLName);
    }

    public String getManagerEmail() {
        return managerEmail.get();
    }

    public StringProperty managerEmailProperty() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail.set(managerEmail);
    }

    public String getManagerPassword() {
        return managerPassword.get();
    }

    public StringProperty managerPasswordProperty() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword.set(managerPassword);
    }

    public String getManagerOfficeNum() {
        return managerOfficeNum.get();
    }

    public StringProperty managerOfficeNumProperty() {
        return managerOfficeNum;
    }

    public void setManagerOfficeNum(String managerOfficeNum) {
        this.managerOfficeNum.set(managerOfficeNum);
    }
}
