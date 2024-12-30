module com.example.gestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;
    requires eu.hansolo.toolbox;

    opens com.example.gestion to javafx.fxml;
    exports com.example.gestion;
}
