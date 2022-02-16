package com.projekt.projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection(){
        String databaseName = "projekt_db";
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser,databasePassword);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        System.out.println("Connected to database!");
        return databaseLink;

    }

    public Connection checkDatabase(){
        String databaseUser = "root";
        String databasePassword = "root";
        String url = "jdbc:mysql://localhost/";
        String sql = "CREATE DATABASE IF NOT EXISTS projekt_db";

        try{
            Connection conn = DriverManager.getConnection(url, databaseUser, databasePassword);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            databaseLink = DriverManager.getConnection(url, databaseUser,databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Database exists!");
        return databaseLink;
    }

}
