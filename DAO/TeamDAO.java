package DAO;

import com.example.miniproject2.DBConnection;
import models.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {

    // Insert
    public void addTeam(Team team) {
        String sql = "INSERT INTO teams (name, role, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setString(2, team.getRole());
            stmt.setString(3, team.getEmail());
            stmt.executeUpdate();

            System.out.println("Team member added!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Select all
    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM teams";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                teams.add(new Team(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("email")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }

    // Update
    public void updateTeam(Team team) {
        String sql = "UPDATE teams SET name = ?, role = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, team.getName());
            stmt.setString(2, team.getRole());
            stmt.setString(3, team.getEmail());
            stmt.setInt(4, team.getId());
            stmt.executeUpdate();

            System.out.println("Team member updated!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteTeam(int id) {
        String sql = "DELETE FROM teams WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("Team member deleted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
