package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Client;
import com.example.miniproject2.models.ClientsStore;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ClientsController {

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, String> phoneColumn;

    @FXML
    private TableColumn<Client, String> emailColumn;

    private ClientsStore clientsStore = new ClientsStore();

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        clientsTable.setItems(clientsStore.getClients());

        clientsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selectedClient) -> {
            if (selectedClient != null) {
                idField.setText(String.valueOf(selectedClient.getId()));
                nameField.setText(selectedClient.getName());
                phoneField.setText(selectedClient.getPhone());
                emailField.setText(selectedClient.getEmail());
            }
        });
    }

    @FXML
    public void addClient() {
        Client client = new Client(
                Integer.parseInt(idField.getText()),
                nameField.getText(),
                phoneField.getText(),
                emailField.getText()
        );

        clientsStore.addClient(client);
        clearFields();
    }

    @FXML
    public void updateClient() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            Client updatedClient = new Client(
                    Integer.parseInt(idField.getText()),
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText()
            );

            clientsStore.updateClient(selectedClient, updatedClient);
            clearFields();
        }
    }

    @FXML
    public void deleteClient() {
        Client selectedClient = clientsTable.getSelectionModel().getSelectedItem();

        if (selectedClient != null) {
            clientsStore.deleteClient(selectedClient);
            clearFields();
        }
    }

    @FXML
    public void clearFields() {
        idField.clear();
        nameField.clear();
        phoneField.clear();
        emailField.clear();
        clientsTable.getSelectionModel().clearSelection();
    }
}