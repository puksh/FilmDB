package com.projekt.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class mainPanel implements Initializable {

    @FXML
    private TableView<tableFilmy> table_film;
    @FXML
    private TableColumn<tableFilmy, String> Acol_id;
    @FXML
    private TableColumn<tableFilmy, String> Acol_tytul;
    @FXML
    private TableColumn<tableFilmy, String> Acol_rok;
    @FXML
    private TableColumn<tableFilmy, String> Acol_jezyk;
    @FXML
    private TableColumn<tableFilmy, String> Acol_cena;
    @FXML
    private TableColumn<tableFilmy, String> Acol_gatunek;
    @FXML
    private TableColumn<tableFilmy, String> Acol_rezyser;
    @FXML
    private TableColumn<tableFilmy, String> Acol_glownyAktor;

    @FXML
    private TableView<tableAktorzy> table_aktorzy;
    @FXML
    private TableColumn<tableAktorzy, String> Bcol_id;
    @FXML
    private TableColumn<tableAktorzy, String> Bcol_imie;
    @FXML
    private TableColumn<tableAktorzy, String> Bcol_nazwisko;

    @FXML
    private TableView<tableRezyseria> table_rezyseria;
    @FXML
    private TableColumn<tableRezyseria, String> Ccol_id;
    @FXML
    private TableColumn<tableRezyseria, String> Ccol_imie;
    @FXML
    private TableColumn<tableRezyseria, String> Ccol_nazwisko;

    @FXML
    private TableView<tableGatunki> table_gatunki;
    @FXML
    private TableColumn<tableGatunki, String> Dcol_id;
    @FXML
    private TableColumn<tableGatunki, String> Dcol_nazwa;

    ObservableList<tableFilmy> oblist = FXCollections.observableArrayList();
    ObservableList<tableAktorzy> oblistAktorzy = FXCollections.observableArrayList();
    ObservableList<tableGatunki> oblistGatunki = FXCollections.observableArrayList();
    ObservableList<tableRezyseria> oblistRezyseria = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Uzupelnianie tabelki z filmami
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            ResultSet rs = connectDB.createStatement().executeQuery("\n" +
                    "SELECT f.film_id,f.film_tytul,f.film_rok,f.film_jezyk,f.film_cena,gatunki.gatunek_nazwa,rezyserowie.rezyser_imie,rezyserowie.rezyser_nazwisko,aktorzy.aktor_imie,aktorzy.aktor_nazwisko \n" +
                    "FROM filmy AS f \n" +
                    "INNER JOIN aktorzy ON aktorzy.aktor_id=f.film_glownyAktor \n" +
                    "INNER JOIN rezyserowie ON rezyserowie.rezyser_id=f.film_rezyser \n" +
                    "INNER JOIN gatunki ON gatunki.gatunek_id=f.film_gatunek");

            while (rs.next()) {
                oblist.add(new tableFilmy(rs.getString("film_id"), rs.getString("film_tytul"),
                        rs.getString("film_rok"), rs.getString("film_jezyk"), rs.getString("film_cena"),
                        rs.getString("gatunek_nazwa"), rs.getString("rezyser_imie")+" "+rs.getString("rezyser_nazwisko"),
                        rs.getString("aktor_imie")+" "+rs.getString("aktor_nazwisko")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Acol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Acol_tytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        Acol_rok.setCellValueFactory(new PropertyValueFactory<>("rok"));
        Acol_jezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        Acol_cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        Acol_gatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));
        Acol_rezyser.setCellValueFactory(new PropertyValueFactory<>("rezyser"));
        Acol_glownyAktor.setCellValueFactory(new PropertyValueFactory<>("aktor"));

        table_film.setItems(oblist);

        //Uzupelnianie tabelki aktorow
        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM aktorzy ");

            while (rs.next()) {
                oblistAktorzy.add(new tableAktorzy(rs.getString("aktor_id"), rs.getString("aktor_imie"),
                        rs.getString("aktor_nazwisko")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bcol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Bcol_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        Bcol_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        table_aktorzy.setItems(oblistAktorzy);

        //uzupelnianie rezyserow

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM rezyserowie");

            while (rs.next()) {
                oblistRezyseria.add(new tableRezyseria(rs.getString("rezyser_id"), rs.getString("rezyser_imie"),
                        rs.getString("rezyser_nazwisko")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Ccol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Ccol_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        Ccol_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        table_rezyseria.setItems(oblistRezyseria);

        //uzupelnianie gatunkow

        try {
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            ResultSet rs = connectDB.createStatement().executeQuery("SELECT * FROM gatunki");

            while (rs.next()) {
                oblistGatunki.add(new tableGatunki(rs.getString("gatunek_id"), rs.getString("gatunek_nazwa")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Dcol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Dcol_nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));

        table_gatunki.setItems(oblistGatunki);


    }


}
