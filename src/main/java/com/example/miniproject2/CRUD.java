package com.example.miniproject2;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static javafx.application.Application.launch;

public class CRUD extends Application {
    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception {
        stg= primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniproject2/views/Login-view.fxml"));
        primaryStage.setTitle("CRUD APP");
        primaryStage.setScene(new Scene(root, 600,400));
        primaryStage.show();




    }
    public static void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(CRUD.class.getResource("/com/example/miniproject2/views/home-view.fxml"));
        stg.setScene(new Scene(pane));
        stg.show();


    }
    public static void main(String[] args) {
        launch();
    }





}
