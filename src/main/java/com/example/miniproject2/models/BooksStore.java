package com.example.miniproject2.models;

import com.example.miniproject2.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;


public class BooksStore {

   public ObservableList<Book> getBooksList() {
       ObservableList<Book> books = FXCollections.observableArrayList();

       try {
           Connection con = DBConnection.getConnection();
           String query = "SELECT * FROM books";
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery(query);

           while (rs.next()) {
               books.add(new Book(
                       rs.getString("title"),
                       rs.getInt("pages"),
                       rs.getString("author")
               ));
           }

           con.close();

       } catch (Exception e) {
           e.printStackTrace();
       }

       return books;
   }
    public void addBook(Book book){
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO books(title, pages, author) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, book.getName());
            ps.setInt(2, book.getPage());
            ps.setString(3, book.getAuthor());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(Book book){
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM books WHERE title=? AND pages=? AND author=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, book.getName());
            ps.setInt(2, book.getPage());
            ps.setString(3, book.getAuthor());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateBook(Book book, String name, Integer page, String author){
        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE books SET title=?, pages=?, author=? WHERE title=? AND pages=? AND author=?";
            PreparedStatement ps = con.prepareStatement(query);

            // new values
            ps.setString(1, name);
            ps.setInt(2, page);
            ps.setString(3, author);

            // old values
            ps.setString(4, book.getName());
            ps.setInt(5, book.getPage());
            ps.setString(6, book.getAuthor());

            ps.executeUpdate();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteAllBooks() {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            String query = "DELETE FROM books";
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





