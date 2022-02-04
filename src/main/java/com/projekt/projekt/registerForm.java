package com.projekt.projekt;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.io.IOException;
import java.sql.Statement;

public class registerForm  {
    @FXML
    private Button Register;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField PasswordConfirm;
    @FXML
    private Label Registered;
    @FXML
    private Label PasswordDoesNotMatch;
    @FXML
    private TextField UserName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField FirstName;


    public void registerButtonOnAction(ActionEvent event){
        if(Password.getText().equals(PasswordConfirm.getText())){
            registerUser();
            PasswordDoesNotMatch.setText(" ");
        }else{
            PasswordDoesNotMatch.setText("Passwords do not match");
        }

    }

    public void registerUser(){
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    String firstname = FirstName.getText();
    String lastname = LastName.getText();
    String username = UserName.getText();
    String password = Password.getText();


    String insertFields = "INSERT INTO users(user_lastname,user_firstname,user_username,user_password) VALUES ('";
    String insertValues = lastname+"','"+firstname+"','"+username+"','"+password+"')";
    String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            Registered.setText("You have been registered");;

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }


}
