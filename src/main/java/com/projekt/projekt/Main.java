package com.projekt.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("loginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 550, 323);
        stage.setTitle("Login!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){launch(args);}
}
