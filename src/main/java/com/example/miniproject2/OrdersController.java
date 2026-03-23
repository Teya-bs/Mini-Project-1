package com.example.miniproject2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class OrdersController {

    @FXML private TextField customerNameFld, productFld, quantityFld, unitPriceFld;
    @FXML private ComboBox<String> statusCombo;
    @FXML private Text errorMsg;

    @FXML private TableView<Order>            ordersTable;
    @FXML private TableColumn<Order, Integer> orderIdCol, quantityCol;
    @FXML private TableColumn<Order, String>  customerNameCol, productCol, statusCol;
    @FXML private TableColumn<Order, Double>  unitPriceCol;

    private final OrdersStore store = new OrdersStore();

    @FXML
    public void initialize() {
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        productCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        ordersTable.setItems(store.getOrders());

        statusCombo.getItems().addAll("Pending", "Shipped", "Delivered", "Cancelled");
        statusCombo.setValue("Pending");

        ordersTable.getSelectionModel().selectedItemProperty().addListener((obs, old, o) -> {
            if (o != null) {
                customerNameFld.setText(o.getCustomerName());
                productFld.setText(o.getProduct());
                quantityFld.setText(String.valueOf(o.getQuantity()));
                unitPriceFld.setText(String.valueOf(o.getUnitPrice()));
                statusCombo.setValue(o.getStatus());
            }
        });
    }

    @FXML void addOrder(ActionEvent e) {
        if (!validate()) return;
        store.add(customerNameFld.getText(), productFld.getText(),
                Integer.parseInt(quantityFld.getText()),
                Double.parseDouble(unitPriceFld.getText()),
                statusCombo.getValue());
        clearFields(null);
    }

    @FXML void updateOrder(ActionEvent e) {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected == null) { errorMsg.setText("Select an order first."); return; }
        if (!validate()) return;
        store.update(selected, customerNameFld.getText(), productFld.getText(),
                Integer.parseInt(quantityFld.getText()),
                Double.parseDouble(unitPriceFld.getText()),
                statusCombo.getValue());
        errorMsg.setText("Updated successfully.");
    }

    @FXML void deleteOrder(ActionEvent e) {
        Order selected = ordersTable.getSelectionModel().getSelectedItem();
        if (selected == null) { errorMsg.setText("Select an order first."); return; }
        store.delete(selected);
        clearFields(null);
    }

    @FXML void clearFields(ActionEvent e) {
        customerNameFld.clear(); productFld.clear();
        quantityFld.clear();     unitPriceFld.clear();
        statusCombo.setValue("Pending");
        errorMsg.setText("");
        ordersTable.getSelectionModel().clearSelection();
    }

    private boolean validate() {
        if (customerNameFld.getText().isBlank() || productFld.getText().isBlank()) {
            errorMsg.setText("Customer name and product are required."); return false;
        }
        try { Integer.parseInt(quantityFld.getText()); }
        catch (NumberFormatException ex) { errorMsg.setText("Quantity must be a number."); return false; }
        try { Double.parseDouble(unitPriceFld.getText()); }
        catch (NumberFormatException ex) { errorMsg.setText("Unit price must be a number."); return false; }
        errorMsg.setText("");
        return true;
    }
}
