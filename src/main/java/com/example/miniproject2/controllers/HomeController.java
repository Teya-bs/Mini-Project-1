package com.example.miniproject2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeController {

    @FXML
    private Button booksMa;

    @FXML
    private Button ClientsMa;


    @FXML
    private Button ordesMa;

    @FXML
    public void booksMa(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/example/miniproject2/views/books-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Books Management");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clientsMa(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/example/miniproject2/views/ClientsView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Clients Management");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void ordersMa(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                .getResource("/com/example/miniproject2/views/orders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        stage.setTitle("Orders Management");
        stage.setScene(scene);
        stage.show();
    }
}
