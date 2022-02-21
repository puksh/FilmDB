package com.projekt.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController{

    @FXML
    private Button ButtonLogin;
    @FXML
    private Button ButtonRegister;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField TextFieldUsername;
    @FXML
    private PasswordField TextFieldPassword;

    public void ButtonLoginOnAction(ActionEvent event){
        if(TextFieldUsername.getText().isBlank() == false && TextFieldPassword.getText().isBlank() == false){
            loginMessageLabel.setText("Trying to login");
            validateLogin();
        } else{
            loginMessageLabel.setText("Please enter username and password");
        }
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM users WHERE user_username='" + TextFieldUsername.getText()
                + "' AND user_password= '" + TextFieldPassword.getText() +"'";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    loginMessageLabel.setText("Logged in");
                    try{
                        Parent root = FXMLLoader.load(getClass().getResource("mainPanel.fxml"));
                        Stage registerStage = new Stage();
                        registerStage.setScene(new Scene(root,900,400));
                        registerStage.show();
                    }catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
                }else{
                    loginMessageLabel.setText("Invalid login. Username or Password are incorrect");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
    public void loginForm(){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("lang/UIResources");
            Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"),bundle);
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root,550,323));
            registerStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAccoutForm(){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle("lang/UIResources");
            Parent root = FXMLLoader.load(getClass().getResource("registerForm.fxml"),bundle);
            Stage registerStage = new Stage();
            registerStage.setScene(new Scene(root,583,430));
            registerStage.setResizable(false);
            registerStage.show();
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}