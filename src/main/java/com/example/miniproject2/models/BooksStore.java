package com.example.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BooksStore {
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    public BooksStore() {
        this.books.addAll(
                new Book("Atomic habits", 220, "James Clear"),
                new Book("Surrounded by idiots", 300, "Thomas Erikson"),
                new Book("The Champion's Mind", 288, "Afremow")
        );
    }

    public ObservableList<Book> getBooksList() {
        return books;
    }

    public void addBook(Book book){
        if(book != null)
            this.books.add(book);
    }

    public void deleteBook(Book book){
        if(book != null)
            this.books.remove(book);
    }

    public void updateBook(Book book, String name, Integer page, String author)
    {
        if(book != null){
            book.setName(name);
            book.setPage(page);
            book.setAuthor(author);
        }
    }
}


