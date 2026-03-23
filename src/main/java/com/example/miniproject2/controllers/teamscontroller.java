package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Book;
import com.example.miniproject2.models.Team;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class teamscontroller {

    @FXML
    private TableView<Team> tableTeams;

    @FXML
    private TableColumn<Team, String> colName;

    @FXML
    private TableColumn<Team, String> colRole;

    @FXML
    private TableColumn<Team, String> colEmail;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtEmail;
    private ObservableList<Team> teamList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        // Set up columns
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));


        tableTeams.setItems(teamList);
        tableTeams.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtName.setText(newSelection.getName());
                txtRole.setText(newSelection.getRole());
                txtEmail.setText(newSelection.getEmail());
            }
        });
    }

    @FXML
    private void addPlayer(ActionEvent event) {
        String name = txtName.getText();
        String role = txtRole.getText();
        String email = txtEmail.getText();

        if (!name.isEmpty() && !role.isEmpty() && !email.isEmpty()) {
            teamList.add(new Team(name, role, email));
            txtName.clear();
            txtRole.clear();
            txtEmail.clear();
        } else {
            System.out.println("Please fill in all fields");
        }
    }

    @FXML
    private void updatePlayer(ActionEvent event) {
        Team selected = tableTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            int index = teamList.indexOf(selected);
            teamList.set(index, new Team(
                    txtName.getText(),
                    txtRole.getText(),
                    txtEmail.getText()
            ));
        }
    }

    @FXML
    private void deletePlayer(ActionEvent event) {
        Team selected = tableTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teamList.remove(selected);
        }
    }
}
