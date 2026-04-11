package com.example.miniproject2.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

public class ClientsStore {

    private ObservableList<Client> clients;

    private Connection connect() {
        try {
             Connection conn =DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/uni",
                     "root",
                    "123456"
            );
            System.out.println("CONNECTED SUCCESSFULLY");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ClientsStore() {
        clients = FXCollections.observableArrayList();
        loadClientsFromDatabase();
    }

    public ObservableList<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        try (Connection conn = connect()) {

            String sql = "INSERT INTO client (id, name, phone, email) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, client.getId());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getPhone());
            stmt.setString(4, client.getEmail());

            stmt.executeUpdate();

            clients.add(client);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(Client client) {
        try (Connection conn = connect()) {

            String sql = "DELETE FROM client WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, client.getId());

            stmt.executeUpdate();

            clients.remove(client);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client oldClient, Client newClient) {
        try (Connection conn = connect()) {

            String sql = "UPDATE client SET name=?, phone=?, email=? WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, newClient.getName());
            stmt.setString(2, newClient.getPhone());
            stmt.setString(3, newClient.getEmail());
            stmt.setInt(4, oldClient.getId());

            stmt.executeUpdate();

            int index = clients.indexOf(oldClient);
            if (index != -1) {
                clients.set(index, newClient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadClientsFromDatabase() {
        clients.clear();

        try (Connection conn = connect()) {

            String sql = "SELECT * FROM client";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Client client = new Client(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                );

                clients.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}