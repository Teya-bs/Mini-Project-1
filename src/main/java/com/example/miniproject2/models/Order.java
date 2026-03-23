package com.example.miniproject2.models;

import javafx.beans.property.*;

public class Order {

    private final SimpleIntegerProperty orderId;
    private final SimpleStringProperty  customerName;
    private final SimpleStringProperty  product;
    private final SimpleIntegerProperty quantity;
    private final SimpleDoubleProperty  unitPrice;
    private final SimpleStringProperty  status;

    public Order(int id, String customer, String product, int qty, double price, String status) {
        this.orderId      = new SimpleIntegerProperty(id);
        this.customerName = new SimpleStringProperty(customer);
        this.product      = new SimpleStringProperty(product);
        this.quantity     = new SimpleIntegerProperty(qty);
        this.unitPrice    = new SimpleDoubleProperty(price);
        this.status       = new SimpleStringProperty(status);
    }

    public int    getOrderId()      { return orderId.get(); }
    public String getCustomerName() { return customerName.get(); }
    public String getProduct()      { return product.get(); }
    public int    getQuantity()     { return quantity.get(); }
    public double getUnitPrice()    { return unitPrice.get(); }
    public String getStatus()       { return status.get(); }

    public void setCustomerName(String v) { customerName.set(v); }
    public void setProduct(String v)      { product.set(v); }
    public void setQuantity(int v)        { quantity.set(v); }
    public void setUnitPrice(double v)    { unitPrice.set(v); }
    public void setStatus(String v)       { status.set(v); }

    public IntegerProperty orderIdProperty()      { return orderId; }
    public StringProperty  customerNameProperty() { return customerName; }
    public StringProperty  productProperty()      { return product; }
    public IntegerProperty quantityProperty()     { return quantity; }
    public DoubleProperty  unitPriceProperty()    { return unitPrice; }
    public StringProperty  statusProperty()       { return status; }
}