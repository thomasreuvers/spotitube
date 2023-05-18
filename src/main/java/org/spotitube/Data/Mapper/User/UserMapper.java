package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.BaseMapper;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.Optional;

@RequestScoped
public class UserMapper extends BaseMapper implements IUserDAO<User> {

    public UserMapper() {
        super();
    }

    @Override
    public Optional<User> find(int id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username=?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            User u = null;
            if (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("password"), rs.getString("token"));
                u.setId(rs.getInt("id"));
            }

            if (u != null && u.getUsername().contains(username)){
                return Optional.of(u);
            } else {
                return Optional.empty();
            }

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO users(username, password) VALUES(?,?)";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
                ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.addBatch();
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User t) {
        String updateQuery = "UPDATE users SET ";
        String conditions = " WHERE id = ?";
        int parameterIndex = 1;

        if (t.getUsername() != null) {
            updateQuery += "Username = ?, ";
        }
        if (t.getPassword() != null) {
            updateQuery += "Password = ?, ";
        }
        if (t.getToken() != null) {
            updateQuery += "Token = ?, ";
        }

        // Remove the trailing comma and space
        updateQuery = updateQuery.substring(0, updateQuery.length() - 2);

        updateQuery += conditions;

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(updateQuery)
                ) {

            // Set the parameter values for the update statement
            if (t.getUsername() != null) {
                stmt.setString(parameterIndex++, t.getUsername());
            }
            if (t.getPassword() != null) {
                stmt.setString(parameterIndex++, t.getPassword());
            }
            if (t.getToken() != null) {
                stmt.setString(parameterIndex++, t.getToken());
            }

            // Set the ID parameter value
            stmt.setInt(parameterIndex, t.getId());

            // Execute the update statement
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your needs
        }
    }

    @Override
    public void delete(User t) {

    }
}
