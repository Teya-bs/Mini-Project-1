package com.example.miniproject2.controllers;

import com.example.miniproject2.models.Book;
import com.example.miniproject2.models.BooksStore;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;


public class BooksController{
    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TextField authorFld;

    @FXML
    private TableColumn<Book, Number> pageCol;

    @FXML
    private TextField pageFld;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Book, String> nameCol;

    @FXML
    private TextField nameFld;

    @FXML
    private TableView<Book> booksTable;

    @FXML
    private Button updateBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Text errorMsg;

    private final BooksStore bookStore = new BooksStore();

    private ObservableList<Book> bookList = FXCollections.observableArrayList();
    @FXML
    public void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        pageCol.setCellValueFactory(new PropertyValueFactory<>("page"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));

        //ObservableList<Book> books = bookStore.getBooksList();
        booksTable.setItems(bookList);

        booksTable.getSelectionModel().selectedItemProperty().addListener(evt -> {
            Book selectedBook = booksTable.getSelectionModel().getSelectedItem();
            if(selectedBook != null) {
                nameFld.setText(selectedBook.getName());
                pageFld.setText(Integer.toString(selectedBook.getPage()));
                authorFld.setText(selectedBook.getAuthor());
            }
        });

    }

    @FXML
    void addBook(ActionEvent event) {
        String error = "";
        boolean isValid = true;

        String name = nameFld.getText();
        if(name.isEmpty()) {
            error += "Error: Name is required\n";
            isValid = false;
        }

        Integer page = null;
        if(pageFld.getText().isEmpty()) {
            error += "Error: Page number is required\n";
            isValid = false;
        }
        else try {
            page = Integer.parseInt(pageFld.getText());
        }catch(NumberFormatException ex){
            error += "Error: Invalid page value!\n";
            isValid = false;
        }

        String author = authorFld.getText();
        if(author.isEmpty()) {
            error += "Error: Author name is required\n";
            isValid = false;
        }

        if(isValid) {
            bookStore.addBook(new Book(name, page, author));
            Book newBook = new Book(name, page, author);
            bookList.add(newBook);
            nameFld.setText("");
            pageFld.setText("");
            authorFld.setText("");
            errorMsg.setText("");
        }
        else
            errorMsg.setText(error);

    }

    @FXML
    void deleteBook(ActionEvent event) {
        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if(selectedBook != null) {
            booksTable.getItems().remove(selectedBook);

        }
    }
    @FXML
    void clearBooks(ActionEvent event) {
        bookStore.getBooksList().clear();
        nameFld.setText("");
        pageFld.setText("");
        authorFld.setText("");
        errorMsg.setText("");
        bookList.clear();
    }

    @FXML
    void updateBook(ActionEvent event) {

        Book selectedBook = booksTable.getSelectionModel().getSelectedItem();

        if(selectedBook != null)
        {
            String error = "";
            boolean isValid = true;

            String name = nameFld.getText();
            if(name.isEmpty()) {
                error += "Error: Name is required!\n";
                isValid = false;
            }

            Integer page = null;
            if(pageFld.getText().isEmpty()){
                error += "Error: Page number is required!\n";
                isValid = false;
            }
            else try {
                page = Integer.parseInt(pageFld.getText());
            }catch(NumberFormatException ex){
                error += "Error: Invalid page value!\n";
                isValid = false;
            }

            String author = authorFld.getText();
            if(author.isEmpty()){
                error += "Error: Address is required!";
            }

            if(isValid) {
                bookStore.updateBook(selectedBook, name, page, author);
                errorMsg.setText("");
            }
            else
                errorMsg.setText(error);

        }

    }

}



