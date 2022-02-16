package com.projekt.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class startupPanel {
    @FXML
    private Button english;
    @FXML
    private Button polish;

    public void ButtonPolish(ActionEvent event){

        //load login panel
        loginController polish = new loginController();
        polish.loginForm();
    }
    public void ButtonEnglish(ActionEvent event){

        //load login panel
        loginController english = new loginController();
        userInfo currentUser = new userInfo();
        currentUser.setLanguage("en");
        english.loginForm();
    }



}
