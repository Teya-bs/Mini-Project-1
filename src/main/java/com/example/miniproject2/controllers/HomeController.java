package com.example.miniproject2.controllers;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class HomeController {
    @FXML
    private Button booksMa;

    public void booksMa(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().
                getResource("/com/example/miniproject2/views/books-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage() ;
        stage.setTitle("Books Management");
        stage.setScene(scene);
        stage.show();
    }
}
