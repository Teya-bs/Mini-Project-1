package com.example.miniproject2.controllers;

import com.example.miniproject2.CRUD;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController{
    public  LoginController(){

    }
    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

     public void userLogin(javafx.event.ActionEvent actionEvent) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {

        if(username.getText().toString().equals("Teya-bs") && password.getText().toString().equals("333")){
            wrongLogin.setText("Success!");
            CRUD.changeScene("/com/example/miniproject2/views/home-view.fxml");

        }
        else if(username.getText().toString().equals("AlvinA") && password.getText().toString().equals("960")){
            wrongLogin.setText("Success!");
            CRUD.changeScene("/com/example/miniproject2/views/home-view.fxml");
        }
        else if(username.getText().toString().equals("HibaC") && password.getText().toString().equals("555")){
            wrongLogin.setText("Success!");
            CRUD.changeScene("/com/example/miniproject2/views/home-view.fxml");
        }
        else if(username.getText().toString().equals("Josee") && password.getText().toString().equals("534")){
          wrongLogin.setText("Success!");
            CRUD.changeScene("/com/example/miniproject2/views/home-view.fxml");
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wrongLogin.setText("Please enter your data.");
        }
        else{
            wrongLogin.setText("Wrong username or password!");
        }
    }


}
