package com.projekt.projekt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class registerForm  {
    @FXML
    private Button Register;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField PasswordConfirm;
    @FXML
    private Label Registered;
    @FXML
    private Label PasswordDoesNotMatch;
    @FXML
    private TextField UserName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField AddressA;
    @FXML
    private TextField AddressB;
    @FXML
    private TextField Postcode;
    @FXML
    private TextField City;
    @FXML
    private TextField Phone;
    @FXML
    private TextField Email;

    @FXML
    private RadioButton langEnglish;
    @FXML
    private RadioButton langPolish;
    @FXML
    private RadioButton themeLight;
    @FXML
    private RadioButton themeDark;

    int userid;
    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    ToggleGroup lang = new ToggleGroup();
    ToggleGroup theme = new ToggleGroup();
    private boolean update;


    public void registerButtonOnAction(ActionEvent event){
        if (FirstName.getText()==null || LastName.getText()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please fill all data");
            alert.showAndWait();

        } else {
            registerUser();
            PasswordDoesNotMatch.setText(" ");

        }
    }

    public void registerUser(){
    DatabaseConnection connectNow = new DatabaseConnection();
    Connection connectDB = connectNow.getConnection();

    String firstname = FirstName.getText();
    String lastname = LastName.getText();
    String username = UserName.getText();
    String password = Password.getText();
    String addressA =  AddressA.getText();
    String addressB = AddressB.getText();
    String postcode =  Postcode.getText();
    String city = City.getText();
    String phone = Phone.getText();
    String email = Email.getText();


    String insertFields = "INSERT INTO users(user_lastname,user_firstname,user_username,user_password,user_addressA,user_addressB,user_postcode,user_city,user_phone,user_email) VALUES ('";
    String insertValues = lastname+"','"+firstname+"','"+username+"','"+password+"','"+addressA+"','"+addressB+"','"+postcode+"','"+city+"','"+phone+"','"+email+"')";
    String insertToRegister = insertFields + insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);

            Registered.setText("You have been registered");;

        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }


    void setTextField(int id, String firstname, String lastname, String addressA, String addressB,
                      String phone, String postcode, String city, String email, String password,
                      String theme,String language) {

        userid = id;
        FirstName.setText(firstname);
        LastName.setText(lastname);
        UserName.setText(firstname);
        AddressA.setText(lastname);
        AddressB.setText(firstname);
        Phone.setText(lastname);
        Postcode.setText(firstname);
        City.setText(lastname);
        Email.setText(firstname);
        switch(theme){
            case "Light":
                break;
            case "Dark":
                break;

        }
        switch(language){
            case "pl":
                break;
            case "eng":
                break;
        }
    }

    public void save() {

        connection = DatabaseConnection.getConnection();
        String firstname = FirstName.getText();
        String lastname = LastName.getText();
        String username = UserName.getText();
        String addressa = AddressA.getText();
        String addressb = AddressB.getText();
        String phone = Phone.getText();
        String postcode = Postcode.getText();
        String city = City.getText();
        String email =  Email.getText();

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

    private void getQuery() {

        if (!update) {

            query = "INSERT INTO `users` (user_lastname,user_firstname,user_username,user_password,user_addressA,user_addressB,user_postcode,user_city,user_phone,user_email) VALUES (?,?,?,?,?,?,?,?,?,?)";

        }else{
            query = "UPDATE `users` SET "
                    + "`user_lastname`=?,"
                    + "`user_firstname`=?,"
                    + "`user_username`=?,"
                    + "`user_password`=?,"
                    + "`user_addressA`=?,"
                    + "`user_addressB`=?,"
                    + "`user_postcode`=?,"
                    + "`user_city`=?,"
                    + "`user_phone`=?,"
                    + "`user_email`=?, WHERE aktor_id = '"+ userid +"'";
        }

    }

    void setUpdate(boolean b) {
        this.update = b;

    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, UserName.getText());
            preparedStatement.setString(2, FirstName.getText());
            preparedStatement.setString(3, LastName.getText());
            preparedStatement.setString(4, AddressA.getText());
            preparedStatement.setString(5, AddressB.getText());
            preparedStatement.setString(6, Phone.getText());
            preparedStatement.setString(7, Postcode.getText());
            preparedStatement.setString(8, City.getText());
            preparedStatement.setString(9, Email.getText());
            preparedStatement.setString(10, Password.getText());
            preparedStatement.execute();

        }  catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void clean() {
        UserName.setText(null);
        FirstName.setText(null);
        LastName.setText(null);
        AddressA.setText(null);
        AddressB.setText(null);
        Phone.setText(null);
        Postcode.setText(null);
        City.setText(null);
        Email.setText(null);
        Password.setText(null);
        PasswordConfirm.setText(null);
    }
}
