package com.projekt.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
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

    @FXML
    private RadioButton langEnglish;
    @FXML
    private RadioButton langPolish;
    @FXML
    private RadioButton themeLight;
    @FXML
    private RadioButton themeDark;


    ToggleGroup lang = new ToggleGroup();
    ToggleGroup theme = new ToggleGroup();


    public void registerButtonOnAction(ActionEvent event){
        if(UserName.getText().isEmpty()){
            Registered.setText("Username is empty");
        }else{
            if(FirstName.getText().isEmpty()){
                Registered.setText("Firstname is empty");
            }else{
                if(LastName.getText().isEmpty()){
                    Registered.setText("Lastname is empty");
                }else{
                    if(Password.getText().equals(PasswordConfirm.getText())){
                        if(Password.getLength()>=8){
                            registerUser();
                            PasswordDoesNotMatch.setText(" ");
                        }else{
                            PasswordDoesNotMatch.setText("Password is too short");
                        }

                    }else{
                        PasswordDoesNotMatch.setText("Passwords do not match");
                    }
                }
            }
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
