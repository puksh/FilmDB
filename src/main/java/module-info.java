module com.projekt.projekt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.projekt.projekt to javafx.fxml;
    exports com.projekt.projekt;
}