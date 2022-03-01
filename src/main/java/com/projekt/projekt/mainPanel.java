package com.projekt.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
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
    @FXML
    private Button buttonEdit;
    @FXML
    private TabPane tab;

    @FXML
    private ToggleButton toggle;

    ObservableList<tableFilmy> oblist = FXCollections.observableArrayList();
    ObservableList<tableFilmy> oblistFilmy = FXCollections.observableArrayList();
    ObservableList<tableAktorzy> oblistAktorzy = FXCollections.observableArrayList();
    ObservableList<tableGatunki> oblistGatunki = FXCollections.observableArrayList();
    ObservableList<tableRezyseria> oblistRezyseria = FXCollections.observableArrayList();

    String query = null;
    Connection connection = null ;
    tableFilmy film = new tableFilmy();
    tableGatunki gatunek = null ;
    tableRezyseria rezyser = null ;
    tableAktorzy aktor = null ;

    @Override
    public void initialize(URL location, ResourceBundle resources){loadData(); buttonEdit.setStyle("-fx-opacity:0.5");}

    public void editMode() throws SQLException {
        if(toggle.isSelected()){
            buttonEdit.setStyle("-fx-opacity:1");
            buttonEdit.setDisable(false);
            Connection connectDB = DatabaseConnection.getConnection();
            ResultSet rsReal = connectDB.createStatement().executeQuery("\n" +
                    "SELECT *" +
                    "FROM filmy AS f \n");
            while(rsReal.next()) {
                table_film.getItems().clear();
                oblistFilmy.add(new tableFilmy(
                        rsReal.getInt("film_id"),
                        rsReal.getString("film_tytul"),
                        rsReal.getString("film_rok"),
                        rsReal.getString("film_jezyk"),
                        rsReal.getString("film_cena"),
                        rsReal.getString("film_gatunek"),
                        rsReal.getString("film_rezyser"),
                        rsReal.getString("film_glownyAktor")));
            }
            table_film.setItems(oblistFilmy);
        }else{
            buttonEdit.setDisable(true);
            buttonEdit.setStyle("-fx-opacity:0.5");
            Connection connectDB = DatabaseConnection.getConnection();
            ResultSet rs = connectDB.createStatement().executeQuery("\n" +
                    "SELECT f.film_id,f.film_tytul,f.film_rok,f.film_jezyk,f.film_cena,gatunki.gatunek_nazwa," +
                    "rezyserowie.rezyser_imie,rezyserowie.rezyser_nazwisko,aktorzy.aktor_imie,aktorzy.aktor_nazwisko \n" +
                    "FROM filmy AS f \n" +
                    "INNER JOIN aktorzy ON aktorzy.aktor_id=f.film_glownyAktor \n" +
                    "INNER JOIN rezyserowie ON rezyserowie.rezyser_id=f.film_rezyser \n" +
                    "INNER JOIN gatunki ON gatunki.gatunek_id=f.film_gatunek");


            table_film.getItems().clear();
            while (rs.next()) {
                oblist.add(new tableFilmy(
                        rs.getInt("film_id"),
                        rs.getString("film_tytul"),
                        rs.getString("film_rok"),
                        rs.getString("film_jezyk"),
                        rs.getString("film_cena"),
                        rs.getString("gatunek_nazwa"),
                        rs.getString("rezyser_imie")+" "+rs.getString("rezyser_nazwisko"),
                        rs.getString("aktor_imie")+" "+rs.getString("aktor_nazwisko")));
                table_film.setItems(oblist);
            }
        }

    }

public void refreshTable(){
    //Uzupelnianie tabelki z filmami
    table_film.getItems().clear();
    table_aktorzy.getItems().clear();
    table_rezyseria.getItems().clear();
    table_gatunki.getItems().clear();

    buttonEdit.setDisable(true);
    buttonEdit.setStyle("-fx-opacity:0.5");

    try {
        Connection connectDB = DatabaseConnection.getConnection();
        ResultSet rs = connectDB.createStatement().executeQuery("\n" +
                "SELECT f.film_id,f.film_tytul,f.film_rok,f.film_jezyk,f.film_cena,gatunki.gatunek_nazwa," +
                "rezyserowie.rezyser_imie,rezyserowie.rezyser_nazwisko,aktorzy.aktor_imie,aktorzy.aktor_nazwisko \n" +
                "FROM filmy AS f \n" +
                "INNER JOIN aktorzy ON aktorzy.aktor_id=f.film_glownyAktor \n" +
                "INNER JOIN rezyserowie ON rezyserowie.rezyser_id=f.film_rezyser \n" +
                "INNER JOIN gatunki ON gatunki.gatunek_id=f.film_gatunek");

        while (rs.next()) {
            oblist.add(new tableFilmy(
                    rs.getInt("film_id"),
                    rs.getString("film_tytul"),
                    rs.getString("film_rok"),
                    rs.getString("film_jezyk"),
                    rs.getString("film_cena"),
                    rs.getString("gatunek_nazwa"),
                    rs.getString("rezyser_imie")+" "+rs.getString("rezyser_nazwisko"),
                    rs.getString("aktor_imie")+" "+rs.getString("aktor_nazwisko")));
            table_film.setItems(oblist);

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
            oblistAktorzy.add(new tableAktorzy(
                    rs.getInt("aktor_id"),
                    rs.getString("aktor_imie"),
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
            oblistRezyseria.add(new tableRezyseria(
                    rs.getInt("rezyser_id"),
                    rs.getString("rezyser_imie"),
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
            oblistGatunki.add(new tableGatunki(
                    rs.getInt("gatunek_id"),
                    rs.getString("gatunek_nazwa")));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    Dcol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    Dcol_nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));

    table_gatunki.setItems(oblistGatunki);

}

public void usersmenu(){

    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("users.fxml"));
    try {
        loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    Parent parent = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(parent));
    stage.initStyle(StageStyle.UTILITY);
    stage.show();

}

public void update() throws SQLException {
    {
        if(tab.getSelectionModel().getSelectedIndex()==0){
            if(table_film.getSelectionModel().getSelectedItem()==null){}
            else{
                film = table_film.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addFilm.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AddFilm addFilmController = loader.getController();
                addFilmController.setUpdate(true);
                addFilmController.setTextField(
                        film.getId(),
                        film.getTytul(),
                        film.getRok(),
                        film.getJezyk(),
                        film.getCena(),
                        film.getGatunek(),
                        film.getRezyser(),
                        film.getAktor()
                );
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        }else if(tab.getSelectionModel().getSelectedIndex()==1){
            if(table_aktorzy.getSelectionModel().getSelectedItem()==null){}
            else{
                aktor = table_aktorzy.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addActor.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                addActor addActorController = loader.getController();
                addActorController.setUpdate(true);
                addActorController.setTextField(
                        aktor.getId(),
                        aktor.getImie(),
                        aktor.getNazwisko()
                );
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        }else if(tab.getSelectionModel().getSelectedIndex()==2){
            if(table_rezyseria.getSelectionModel().getSelectedItem()==null){}
            else{
                rezyser = table_rezyseria.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addDirector.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                addDirector addDirectorController = loader.getController();
                addDirectorController.setUpdate(true);
                addDirectorController.setTextField(
                        rezyser.getId(),
                        rezyser.getImie(),
                        rezyser.getNazwisko()
                );
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        }else if(tab.getSelectionModel().getSelectedIndex()==3){
            if(table_gatunki.getSelectionModel().getSelectedItem()==null){}
            else{
                gatunek = table_gatunki.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addGenre.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                addGenre addGenreController = loader.getController();
                addGenreController.setUpdate(true);
                addGenreController.setTextField(
                        gatunek.getId(),
                        gatunek.getNazwa()
                );
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        }
    }
}

public void add(){
    {
        if(tab.getSelectionModel().getSelectedIndex()==0){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addFilm.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        else if(tab.getSelectionModel().getSelectedIndex()==1){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addActor.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        else if(tab.getSelectionModel().getSelectedIndex()==2){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addDirector.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
            }
        else if(tab.getSelectionModel().getSelectedIndex()==3){
                gatunek = table_gatunki.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("addGenre.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent parent = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(parent));
                stage.initStyle(StageStyle.UTILITY);
                stage.show();

        }
    }
}

public void delete(){
    try {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        if(tab.getSelectionModel().getSelectedItem().getText()=="Filmy"){
            film = table_film.getSelectionModel().getSelectedItem();
            query = "DELETE FROM `filmy` WHERE id  ="+film.getId();
            ResultSet rs = connectDB.createStatement().executeQuery(query);
        }else if(tab.getSelectionModel().getSelectedItem().getText()=="Aktorzy"){
            aktor = table_aktorzy.getSelectionModel().getSelectedItem();
            query = "DELETE FROM `aktorzy` WHERE id  ="+aktor.getId();
            ResultSet rsB = connectDB.createStatement().executeQuery(query);
        }else if(tab.getSelectionModel().getSelectedItem().getText()=="Rezyseria"){
            rezyser = table_rezyseria.getSelectionModel().getSelectedItem();
            query = "DELETE FROM `rezyserowie` WHERE id  ="+rezyser.getId();
            ResultSet rsD = connectDB.createStatement().executeQuery(query);
        }else if(tab.getSelectionModel().getSelectedItem().getText()=="Gatunki"){
            gatunek = table_gatunki.getSelectionModel().getSelectedItem();
            query = "DELETE FROM `gatunki` WHERE id  ="+gatunek.getId();
            ResultSet rsC = connectDB.createStatement().executeQuery(query);
        }
        refreshTable();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public void loadData() {

        connection = DatabaseConnection.getConnection();
        refreshTable();

        Acol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Acol_tytul.setCellValueFactory(new PropertyValueFactory<>("tytul"));
        Acol_rok.setCellValueFactory(new PropertyValueFactory<>("rok"));
        Acol_jezyk.setCellValueFactory(new PropertyValueFactory<>("jezyk"));
        Acol_cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        Acol_gatunek.setCellValueFactory(new PropertyValueFactory<>("gatunek"));
        Acol_rezyser.setCellValueFactory(new PropertyValueFactory<>("rezyser"));
        Acol_glownyAktor.setCellValueFactory(new PropertyValueFactory<>("aktor"));


        table_film.setItems(oblist);
    }


}
