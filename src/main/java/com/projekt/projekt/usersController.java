package com.projekt.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class usersController implements Initializable {
    @FXML
    private TableView<tableUsers> tableUsers;

    tableUsers user = null;

    ObservableList<tableUsers> users = FXCollections.observableArrayList();
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void loadData() throws SQLException {

        tableUsers.getItems().clear();

        Connection connectDB = DatabaseConnection.getConnection();
        ResultSet rsReal = connectDB.createStatement().executeQuery("\n" +
                "SELECT *" +
                "FROM users AS f \n");
        while(rsReal.next()) {
            users.add(new tableUsers(
                    rsReal.getInt("user_id"),
                    rsReal.getString("user_username"),
                    rsReal.getString("user_firstname"),
                    rsReal.getString("user_lastname"),
                    rsReal.getString("user_addressA"),
                    rsReal.getString("user_addressB"),
                    rsReal.getString("user_phone"),
                    rsReal.getString("user_postcode"),
                    rsReal.getString("user_city"),
                    rsReal.getString("user_email"),
                    rsReal.getString("user_password"),
                    rsReal.getString("user_theme"),
                    rsReal.getString("user_language"),
                    rsReal.getInt("user_admin")));
        }
        tableUsers.setItems(users);
    }

//    public void edit() {
//        if (tableUsers.getSelectionModel().getSelectedItem() == null) {
//        } else {
//            user = tableUsers.getSelectionModel().getSelectedItem();
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("addUser.fxml"));
//            try {
//                loader.load();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            AddUser addUserController = loader.getController();
//            addUserController.setUpdate(true);
//            addUserController.setTextField(
//                    user.getId(),
//                    user.getUsername(),
//                    user.getFirstname(),
//                    user.getLastname(),
//                    user.getAddressA(),
//                    user.getAddressB(),
//                    user.getPhone(),
//                    user.getPostcode(),
//                    user.getCity(),
//                    user.getEmail(),
//                    user.getPassword(),
//                    user.getPostcode()
//            );
//            Parent parent = loader.getRoot();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(parent));
//            stage.initStyle(StageStyle.UTILITY);
//            stage.show();
//        }
//    }
}
