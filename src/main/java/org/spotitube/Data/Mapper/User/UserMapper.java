package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.BaseMapper;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.Optional;

@RequestScoped
public class UserMapper extends BaseMapper<User> implements IUserDao {

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
                PreparedStatement stmt = conn.prepareStatement(sql);
                ) {
            stmt.setString(1, user.getUsername());
            stmt.setString(1, user.getPassword());
            stmt.addBatch();
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User t) {

    }

    @Override
    public void delete(User t) {

    }
}
