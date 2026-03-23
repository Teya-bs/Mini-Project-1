package com.example.miniproject2;

import javafx.collections.*;

public class OrdersStore {

    private final ObservableList<Order> orders = FXCollections.observableArrayList();
    private int nextId = 4;

    public OrdersStore() {
        orders.addAll(
                new Order(1, "Alice", "Laptop",   2, 999.99, "Pending"),
                new Order(2, "Bob",   "Mouse",    5,  25.00, "Shipped"),
                new Order(3, "Carol", "Keyboard", 3,  45.50, "Delivered")
        );
    }

    public ObservableList<Order> getOrders() { return orders; }

    public void add(String customer, String product, int qty, double price, String status) {
        orders.add(new Order(nextId++, customer, product, qty, price, status));
    }

    public void update(Order o, String customer, String product, int qty, double price, String status) {
        o.setCustomerName(customer);
        o.setProduct(product);
        o.setQuantity(qty);
        o.setUnitPrice(price);
        o.setStatus(status);
    }

    public void delete(Order o) { orders.remove(o); }
}
