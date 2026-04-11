
package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Book;
import com.example.miniproject2.models.BooksStore;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BooksController {

    @FXML private TableView<Book> booksTable;
    @FXML private TableColumn<Book, String> nameCol;
    @FXML private TableColumn<Book, Integer> pageCol;
    @FXML private TableColumn<Book, String> authorCol;

    @FXML private TextField nameFld;
    @FXML private TextField pageFld;
    @FXML private TextField authorFld;

    private BooksStore booksStore = new BooksStore();

    @FXML
    public void initialize() {
        // Bind columns to Book properties
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        pageCol.setCellValueFactory(cellData -> cellData.getValue().pageProperty().asObject());
        authorCol.setCellValueFactory(cellData -> cellData.getValue().authorProperty());

        // Load initial data
        refreshTable();
        booksTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameFld.setText(newSelection.getName());
                pageFld.setText(String.valueOf(newSelection.getPage()));
                authorFld.setText(newSelection.getAuthor());
            }
        });
    }

    private void refreshTable() {
        ObservableList<Book> books = booksStore.getBooksList();
        booksTable.setItems(books);
    }

    @FXML
    public void addBook() {
        try {
            String title = nameFld.getText();
            int pages = Integer.parseInt(pageFld.getText());
            String author = authorFld.getText();

            Book newBook = new Book(title, pages, author);
            booksStore.addBook(newBook);

            // Update table
            refreshTable();
            clearBooks();
        } catch (NumberFormatException e) {
            showAlert("Pages must be a number.");
        }
    }

    @FXML
    public void deleteBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            booksStore.deleteBook(selected);
            refreshTable();
        } else {
            showAlert("Please select a book to delete.");
        }
    }

    @FXML
    public void updateBook() {
        Book selected = booksTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                String newTitle = nameFld.getText();
                int newPages = Integer.parseInt(pageFld.getText());
                String newAuthor = authorFld.getText();

                booksStore.updateBook(selected, newTitle, newPages, newAuthor);
                refreshTable();
                clearBooks();
            } catch (NumberFormatException e) {
                showAlert("Pages must be a number.");
            }
        } else {
            showAlert("Please select a book to update.");
        }
    }

    @FXML
    private void clearBooks() {
        nameFld.clear();
        pageFld.clear();
        authorFld.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}



