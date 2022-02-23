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

public class addDirector {
    @FXML
    private Button buttonConfirm;
    @FXML
    private TextField fieldFirstname;
    @FXML
    private TextField fieldLastname;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    tableRezyseria rezyser = null;
    private boolean update;
    int rezyserid;

    @FXML
    private void save(MouseEvent event) {

        connection = DatabaseConnection.getConnection();
        String firstname = fieldFirstname.getText();
        String lastname = fieldLastname.getText();

        if (firstname.isEmpty() || lastname.isEmpty()) {
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
        fieldFirstname.setText(null);
        fieldLastname.setText(null);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `rezyserowie` ( `rezyser_imie`, `rezyser_nazwisko`) VALUES (?,?)";

        }else{
            query = "UPDATE `rezyserowie` SET "
                    + "`rezyser_imie`=?,"
                    + "`rezyser_nazwisko`= ? WHERE rezyser_id = '"+ rezyserid +"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fieldFirstname.getText());
            preparedStatement.setString(2, fieldLastname.getText());
            preparedStatement.execute();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void setTextField(int id, String firstname, String lastname) {

        rezyserid = id;
        fieldFirstname.setText(firstname);
        fieldLastname.setText(lastname);

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
}
