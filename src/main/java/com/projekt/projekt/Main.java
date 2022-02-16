package com.projekt.projekt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.checkDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(loginController.class.getResource("startupPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 226.0, 29);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args){launch(args);}
}
