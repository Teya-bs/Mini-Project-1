package com.example.miniproject2.models;

import javafx.collections.*;
import java.sql.*;

public class OrdersStore {

    private final ObservableList<Order> orders = FXCollections.observableArrayList();

    private static final String URL  = "jdbc:mysql://localhost:3306/workshopidb";
    private static final String USER = "root";
    private static final String PASS = "root1232006"; // change to your password

    private Connection connect() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public OrdersStore() {
        loadFromDB();
    }

    private void loadFromDB() {
        orders.clear();
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("orderId"),
                        rs.getString("customerName"),
                        rs.getString("product"),
                        rs.getInt("quantity"),
                        rs.getDouble("unitPrice"),
                        rs.getString("status")
                ));
            }
            rs.close(); stmt.close(); conn.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public ObservableList<Order> getOrders() { return orders; }

    public void add(String customer, String product, int qty, double price, String status) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO Orders (customerName, product, quantity, unitPrice, status) VALUES ('"
                    + customer + "', '" + product + "', " + qty + ", " + price + ", '" + status + "')");
            stmt.close(); conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        loadFromDB();
    }

    public void update(Order o, String customer, String product, int qty, double price, String status) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Orders SET customerName='" + customer + "', product='" + product +
                    "', quantity=" + qty + ", unitPrice=" + price + ", status='" + status +
                    "' WHERE orderId=" + o.getOrderId());
            stmt.close(); conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        loadFromDB();
    }

    public void delete(Order o) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Orders WHERE orderId=" + o.getOrderId());
            stmt.close(); conn.close();
        } catch (Exception e) { e.printStackTrace(); }
        loadFromDB();
    }
}
