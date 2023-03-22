package org.spotitube.Data.Mapper;

import org.spotitube.Data.Entity.User;

import javax.enterprise.context.RequestScoped;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@RequestScoped
public class UserMapperImpl extends BaseMapperImpl<User> {
    @Override
    public Optional<User> find(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(User user) {
        Connection x = initializeConnection();
        try {
            Statement s = x.createStatement();
            String sql = "CREATE TABLE REGISTRATION " +
                    "(id INTEGER not NULL, " +
                    " first VARCHAR(255), " +
                    " last VARCHAR(255), " +
                    " age INTEGER, " +
                    " PRIMARY KEY ( id ))";

            s.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public  void update(User t) {

    }

    @Override
    public  void delete(User t) {

    }
}
