package com.example.iacode;

import java.sql.Connection;
import java.sql.DriverManager;


/*This is a method that creates a connection to a database. Once the method is called, anywhere in the program, you can connected to all tables
in the database*/

public class ConnectionDatabase {
    public Connection linkToDataBase;

    public Connection getConnection(){
        String databaseName = "sys";
        String databaseUser = "root";
        String password = "COMPSCIIA1";
        String url = "jdbc:mysql://localhost/"+ databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            linkToDataBase = DriverManager.getConnection(url, databaseUser, password);

        }catch (Exception e){
            e.printStackTrace();
        }
        return linkToDataBase;
    }
}

