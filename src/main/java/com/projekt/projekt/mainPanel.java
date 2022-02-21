package com.projekt.projekt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private TableColumn<tableFilmy, String> Acol_edit;

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

    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    tableFilmy film = new tableFilmy();
    tableGatunki gatunek = null ;
    tableRezyseria rezyser = null ;
    tableAktorzy aktor = null ;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        loadData();
    }

public void refreshTable(){
    //Uzupelnianie tabelki z filmami
    try {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
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
                    rs.getString("aktor_id"),
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
                    rs.getString("rezyser_id"),
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
                    rs.getString("gatunek_id"),
                    rs.getString("gatunek_nazwa")));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    Dcol_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    Dcol_nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));

    table_gatunki.setItems(oblistGatunki);

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


        Callback<TableColumn<tableFilmy, String>, TableCell<tableFilmy, String>> cellFactory = (TableColumn<tableFilmy, String> param) -> {
            // make cell containing buttons
            final TableCell<tableFilmy, String> cell = new TableCell<tableFilmy, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        Button deleteIcon = new Button();
                        Button editIcon = new Button();

                        deleteIcon.setText("Delete");
                        editIcon.setText("Edit");

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-fx-fill:#00E676;"
                        );
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                            try {
                                film = table_film.getSelectionModel().getSelectedItem();
                                DatabaseConnection connectNow = new DatabaseConnection();
                                Connection connectDB = connectNow.getConnection();
                                query = "DELETE FROM `filmy` WHERE id  ="+film.getId();
                                ResultSet rs = connectDB.createStatement().executeQuery(query);
                                refreshTable();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }





                        });
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            if(table_film.getSelectionModel().getSelectedItem()==null){}
                                else{
                                 film = table_film.getSelectionModel().getSelectedItem();
                                DatabaseConnection connectNow = new DatabaseConnection();
                                Connection connectDB = connectNow.getConnection();
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
                            }
                        );

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);
                        setText(null);

                    }

                }

            };
            return cell;




    };
        Acol_edit.setCellFactory(cellFactory);
        table_film.setItems(oblist);
    }


}
