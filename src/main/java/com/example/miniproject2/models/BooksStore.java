
package com.example.miniproject2.models;

import com.example.miniproject2.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class BooksStore {

    public ObservableList<Book> getBooksList() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        String sql = "SELECT title, pages, author FROM books";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getString("title"),
                        rs.getInt("pages"),
                        rs.getString("author")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, pages, author) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setInt(2, book.getPage());
            pstmt.setString(3, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteBook(Book book) {
        String sql = "DELETE FROM books WHERE title = ? AND author = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateBook(Book book, String newTitle, int newPages, String newAuthor) {
        String sql = "UPDATE books SET title = ?, pages = ?, author = ? WHERE title = ? AND author = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newTitle);
            pstmt.setInt(2, newPages);
            pstmt.setString(3, newAuthor);
            pstmt.setString(4, book.getName());
            pstmt.setString(5, book.getAuthor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
