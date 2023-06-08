package org.spotitube.Data.Mapper.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.BaseMapper;

import javax.enterprise.context.RequestScoped;
import java.sql.*;
import java.util.Arrays;
import java.util.Optional;

@RequestScoped
public class UserMapper extends BaseMapper<User> implements IUserMapper {

    public UserMapper() {
        super();
    }

    @Override
    public Optional<User> find(int id) {
        String sql = "SELECT * FROM users WHERE id=?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            User u = null;
            if (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("password"), rs.getString("token"));
                u.setId(rs.getInt("id"));
            }

            if (u != null){
                return Optional.of(u);
            } else {
                return Optional.empty();
            }

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
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
    public Optional<User> findByToken(String token) {
        String sql = "SELECT * FROM users WHERE token=?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();

            User u = null;
            if (rs.next()) {
                u = new User(rs.getString("username"), rs.getString("password"), rs.getString("token"));
                u.setId(rs.getInt("id"));
            }

            if (u != null && u.getToken().contains(token)){
                return Optional.of(u);
            } else {
                return Optional.empty();
            }

        }catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void newUser(User user) {
        String query = "INSERT INTO users(username, password) VALUES(?,?)";
        save(query, Arrays.asList(user.getUsername(), DigestUtils.sha256Hex(user.getPassword())));
    }

    @Override
    public void updateToken(int id, String token) {
        String query = "UPDATE users SET token=? WHERE id=?";
        save(query, Arrays.asList(token, id));
    }
}
