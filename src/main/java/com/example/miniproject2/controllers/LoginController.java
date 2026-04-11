package com.example.miniproject2.controllers;

import com.example.miniproject2.CRUD;
import com.example.miniproject2.DBConnection;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

    @FXML
    private PasswordField password;
    @FXML
    private TextField username;
    @FXML
    private Label wrongLogin;

    // Your existing boolean login logic
    public boolean login(String username, String password) {
        try {
            Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM accounts WHERE username='"
                    + username + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(query);
            boolean valid = rs.next();
            rs.close();
            stmt.close();
            conn.close();
            return valid;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @FXML
    public void userLogin(javafx.event.ActionEvent actionEvent) throws IOException  {
        String usernamee = username.getText();
        String passwordd = password.getText();

        boolean success = login(usernamee, passwordd);

        if (success) {
            wrongLogin.setText("Login successful!");
            CRUD.changeScene("/com/example/miniproject2/views/home-view.fxml");
        } else {
            wrongLogin.setText("Login failed. Try again.");
        }
    }
}