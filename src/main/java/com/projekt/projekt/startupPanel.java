package com.projekt.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


public class startupPanel {
    @FXML
    private Button english;
    @FXML
    private Button polish;
    @FXML
    private AnchorPane startupPanel;

    public void ButtonPolish(ActionEvent event){

        //load login panel
        loginController polish = new loginController();
        polish.loginForm();
    }
    public void ButtonEnglish(ActionEvent event){

        //load login panel
        loginController english = new loginController();
        tableUsers currentUser = new tableUsers();
        currentUser.setLanguage("en");
        english.loginForm();
    }


}
