package com.example.design_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectToDB {
    Statement sqlStatement;
    Connection newConnection;

    public ConnectToDB(){
        try {
            String dataBaseURL = "jdbc:ucanaccess://MetVerse_Gallery11.accdb";
            //Almost same code as the one from first semester
            newConnection = DriverManager.getConnection(dataBaseURL);
            System.out.println("Connected to MS Access database");
            sqlStatement = newConnection.createStatement();
        }
        catch (Exception e){
            System.out.println("Couldn't connect to Database");
        }
    }

    public void addToDB(String sqlString, String tableName) {
        try {
            sqlStatement.executeQuery(sqlString);
        }
        catch (Exception e){
            System.out.println("Couldn't add to database");
        }
    }

    public Statement getSqlStatement() {
        return sqlStatement;
    }

    public Connection getNewConnection() {
        return newConnection;
    }
}
