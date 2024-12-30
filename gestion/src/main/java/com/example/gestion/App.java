package com.example.gestion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Connection con = DBConnexion.getCon();

        // Charger l'interface utilisateur à partir de FXML
        Parent parent = FXMLLoader.load(getClass().getResource("/Article.fxml"));
        Scene scene = new Scene(parent);
        stage.setTitle("Crud article");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
