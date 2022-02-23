package com.projekt.projekt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addGenre {

    @FXML
    private Button buttonConfirm;
    @FXML
    private TextField fieldName;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    tableGatunki gatunek = null;
    private boolean update;
    int gatunekid;

    @FXML
    private void save(MouseEvent event) {

        connection = DatabaseConnection.getConnection();
        String firstname = fieldName.getText();

        if (firstname.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();

        } else {
            getQuery();
            insert();
            clean();

        }

    }

    @FXML
    private void clean() {
        fieldName.setText(null);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `gatunki` ( `gatunek_nazwa`) VALUES (?)";

        }else{
            query = "UPDATE `filmy` SET "
                    + "`gatunek_nazwa`= ? WHERE gatunek_id = '"+ gatunekid +"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fieldName.getText());
            preparedStatement.execute();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void setTextField(int id, String name) {

        gatunekid = id;
        fieldName.setText(name);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}

