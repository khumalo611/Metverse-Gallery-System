package com.example.design_3;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Artist {
    IntegerProperty artistID = new SimpleIntegerProperty();
    StringProperty artistFName = new SimpleStringProperty();
    StringProperty artistLName = new SimpleStringProperty();
    StringProperty artistEmail = new SimpleStringProperty();
    StringProperty artistCountry = new SimpleStringProperty();
    IntegerProperty artistBYear = new SimpleIntegerProperty();
    StringProperty artistPassword = new SimpleStringProperty();
    StringProperty artistPseudonym = new SimpleStringProperty();
    IntegerProperty artistDYear = new SimpleIntegerProperty();

    public Artist(int artistID, String artistFName, String artistLName, String artistEmail, String artistCountry,
                  int artistBYear, String artistPassword, String artistPseudonym, int artistDYear) {
        this.artistID.setValue(artistID);
        this.artistFName.setValue(artistFName);
        this.artistLName.setValue(artistLName);
        this.artistEmail.setValue(artistEmail);
        this.artistCountry.setValue(artistCountry);
        this.artistBYear.setValue(artistBYear);
        this.artistPassword.setValue(artistPassword);
        this.artistPseudonym.setValue(artistPseudonym);
        this.artistDYear.setValue(artistDYear);
    }

    public int getArtistID() {
        return artistID.get();
    }

    public IntegerProperty artistIDProperty() {
        return artistID;
    }

    public StringProperty getArtistFName() {
        return artistFName;
    }

    public StringProperty artistFNameProperty() {
        return artistFName;
    }

    public void setArtistFName(String artistFName) {
        this.artistFName.set(artistFName);
    }

    public StringProperty getArtistLName() {
        return artistLName;
    }

    public StringProperty artistLNameProperty() {
        return artistLName;
    }

    public void setArtistLName(String artistLName) {
        this.artistLName.set(artistLName);
    }

    public StringProperty getArtistEmail() {
        return artistEmail;
    }

    public StringProperty artistEmailProperty() {
        return artistEmail;
    }

    public void setArtistEmail(String artistEmail) {
        this.artistEmail.set(artistEmail);
    }

    public StringProperty getArtistCountry() {
        return artistCountry;
    }

    public StringProperty artistCountryProperty() {
        return artistCountry;
    }

    public void setArtistCountry(String artistCountry) {
        this.artistCountry.set(artistCountry);
    }

    public IntegerProperty getArtistBYear() {
        return artistBYear;
    }

    public IntegerProperty artistBYearProperty() {
        return artistBYear;
    }

    public void setArtistBYear(int artistBYear) {
        this.artistBYear.set(artistBYear);
    }

    public StringProperty getArtistPassword() {
        return artistPassword;
    }

    public StringProperty artistPasswordProperty() {
        return artistPassword;
    }

    public void setArtistPassword(String artistPassword) {
        this.artistPassword.set(artistPassword);
    }

    public StringProperty getArtistPseudonym() {
        return artistPseudonym;
    }

    public StringProperty artistPseudonymProperty() {
        return artistPseudonym;
    }

    public void setArtistPseudonym(String artistPseudonym) {
        this.artistPseudonym.set(artistPseudonym);
    }

    public IntegerProperty getArtistDYear() {
        return artistDYear;
    }

    public IntegerProperty artistDYearProperty() {
        return artistDYear;
    }

    public void setArtistDYear(int artistDYear) {
        this.artistDYear.set(artistDYear);
    }
    @Override
    public String toString(){
        return String.format("%s   %s   %s   %s   %s   %s   %s   %s  %s",
                artistID.getValue(),artistFName.getValue(),artistLName.getValue(),artistEmail.getValue(),artistCountry.getValue(),
                artistBYear.getValue(),artistPassword.getValue(),artistPseudonym.getValue(),
                artistDYear.getValue());
    }
}
