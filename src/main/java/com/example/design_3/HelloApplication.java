package com.example.design_3;
import java.sql.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Manager/ManagerLanding.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 894, 542);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
        //ConnectToDB();
    }
//    public static void ConnectToDB()
//    //Connects to the database, this one was just testing by printing all the names of the art pieces in the database
//    {
//        String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
//
//        try {
//            //Almost same code as the one from first semester
//            Connection newConnection = DriverManager.getConnection(dataBaseURL);
//            System.out.println("Connected to MS Access database");
//            Statement sqlStatement = newConnection.createStatement();
//            String sql = "Select * From Artist";
//            ResultSet allArt = sqlStatement.executeQuery(sql);
//            if(allArt != null){
//                System.out.println("Successfully selected all data.");
//            }
//            while(allArt.next()){
//                String artistName = allArt.getString("First_Name");
//                System.out.println(artistName);
//            }
//            newConnection.close();
//        }
//        catch(Exception e){
//            System.out.println("Try Again Buddy");
//        }
//    }
}