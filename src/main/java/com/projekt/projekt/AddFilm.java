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

public class AddFilm {
    @FXML
    private Button Confirm;
    @FXML
    private TextField fieldTitle;
    @FXML
    private TextField fieldYear;
    @FXML
    private TextField fieldLanguage;
    @FXML
    private TextField fieldPrice;
    @FXML
    private TextField fieldGenre;
    @FXML
    private TextField fieldDirector;
    @FXML
    private TextField fieldActor;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    tableFilmy film = null;
    private boolean update;
    int filmId;

    private void save(MouseEvent event) {

        connection = DatabaseConnection.getConnection();
        String title = fieldTitle.getText();
        String year = fieldYear.getText();
        String language = fieldLanguage.getText();
        String price = fieldPrice.getText();
        String genre = fieldGenre.getText();
        String director = fieldDirector.getText();
        String actor = fieldActor.getText();

        if (title.isEmpty() || year.isEmpty() || language.isEmpty() || price.isEmpty() || genre.isEmpty() || director.isEmpty() || actor.isEmpty()) {
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
        fieldTitle.setText(null);
        fieldYear.setText(null);
        fieldLanguage.setText(null);
        fieldPrice.setText(null);
        fieldGenre.setText(null);
        fieldDirector.setText(null);
        fieldActor.setText(null);
    }

    private void getQuery() {

        if (update == false) {

            query = "INSERT INTO `filmy` ( `film_tytul`, `film_rok`, `film_jezyk`, `film_cena`,`film_genre`,`film_rezyser`,`film_glownyAktor`) VALUES (?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `filmy` SET "
                    + "`film_tytul`=?,"
                    + "`film_rok`=?,"
                    + "`film_jezyk`=?,"
                    + "`film_cena`=?,"
                    + "`film_genre`=?,"
                    + "`film_rezyser`=?,"
                    + "`film_glownyAktor`= ? WHERE film_id = '"+ filmId +"'";
        }

    }

    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fieldTitle.getText());
            preparedStatement.setString(2, fieldYear.getText());
            preparedStatement.setString(3, fieldLanguage.getText());
            preparedStatement.setString(4, fieldPrice.getText());
            preparedStatement.setString(5, fieldGenre.getText());
            preparedStatement.setString(6, fieldDirector.getText());
            preparedStatement.setString(7, fieldActor.getText());
            preparedStatement.execute();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void setTextField(int id, String title, String year, String language, String price, String genre, String director, String actor) {

        filmId = id;
        fieldTitle.setText(title);
        fieldYear.setText(String.valueOf(year));
        fieldLanguage.setText(language);
        fieldPrice.setText(String.valueOf(price));
        fieldGenre.setText(String.valueOf(genre));
        fieldDirector.setText(String.valueOf(director));
        fieldActor.setText(String.valueOf(actor));

    }

    void setUpdate(boolean b) {
        this.update = b;

    }

}
