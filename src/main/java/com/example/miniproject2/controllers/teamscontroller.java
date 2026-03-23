package com.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

// Simple Team model class (you can move this to its own file if needed)
class Team {
    private String name;
    private String role;
    private String email;

    public Team(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
}

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

    private ObservableList<Team> teamList;

    @FXML
    public void initialize() {
        // Set up columns
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Sample data
        teamList = FXCollections.observableArrayList(
                new Team("Alice", "Developer", "alice@example.com"),
                new Team("Bob", "Designer", "bob@example.com")
        );

        tableTeams.setItems(teamList);
    }

    @FXML
    private void handleAdd(ActionEvent event) {
        // Example: add a dummy team member
        teamList.add(new Team("New Member", "Role", "email@example.com"));
    }

    @FXML
    private void handleEdit(ActionEvent event) {
        Team selected = tableTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            // Example: update role
            teamList.set(teamList.indexOf(selected),
                    new Team(selected.getName(), "Updated Role", selected.getEmail()));
        }
    }

    @FXML
    private void handleDelete(ActionEvent event) {
        Team selected = tableTeams.getSelectionModel().getSelectedItem();
        if (selected != null) {
            teamList.remove(selected);
        }
    }
}
