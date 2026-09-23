package com.ptms.app.dao;

import com.ptms.app.model.Roles;
import com.ptms.app.model.User;
import com.ptms.app.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public void addUser(User user) {

        String sql = """
                INSERT INTO users
                (username, password, email, role_id)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRoleId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding user", e);
        }
    }

    @Override
    public User getUserById(int id) {

        String sql = """
                SELECT id, username, password, email, role_id
                FROM users
                WHERE id = ?
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }

        return null;
    }

    @Override
    public User getUserByUsername(String username) {

        String sql = """
                SELECT id, username, password, email, role_id
                FROM users
                WHERE username = ?
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUser(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding user", e);
        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = """
                SELECT id, username, password, email, role_id
                FROM users
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(mapUser(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching users", e);
        }

        return users;
    }

    @Override
    public void updateUser(User user) {

        String sql = """
                UPDATE users
                SET username = ?,
                    password = ?,
                    email = ?,
                    role_id = ?
                WHERE id = ?
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user", e);
        }
    }

    @Override
    public void deleteUser(int id) {

        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;

        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    @Override
    public Roles getRoleById(int id) {
        return null;
    }

    // Converts ResultSet into User object
    private User mapUser(ResultSet rs) throws SQLException {

        User user = new User();

        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
//        user.setEmail(rs.getString("email"));
        user.setRoleId(rs.getInt("role_id"));

        return user;
    }

    static void main(String[] args) {

    }
}