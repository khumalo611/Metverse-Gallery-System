package com.example.design_3;

import java.sql.*;

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

    public void addToDB(String sqlString) {
        try {
            sqlStatement.executeQuery(sqlString);
        }
        catch (Exception e){
            System.out.println("Couldn't add to database");
            System.out.println(e.getMessage());
        }
    }
    public void close() throws SQLException {
        newConnection.close();
    }
    public Statement getSqlStatement() {
        return sqlStatement;
    }

    public Connection getNewConnection() {
        return newConnection;
    }
}
