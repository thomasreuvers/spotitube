package org.spotitube.Data.Mapper.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.spotitube.Data.Context.IConnectionContext;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.BaseMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserMapper extends BaseMapper<User> implements IUserMapper {

    @Inject
    public UserMapper(IConnectionContext context) {
        super(context);
    }

    @Override
    public User findByUserId(int userId) {
        String query = "SELECT * FROM users WHERE id=?";
        Optional<User> user = find(query, List.of(userId));
        return user.orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username=?";

        Optional<User> user = find(query, List.of(username));

        return user.orElse(null);
    }

    @Override
    public User findByToken(String token) {
        String query = "SELECT * FROM users WHERE token=?";

        Optional<User> user = find(query, List.of(token));

        return user.orElse(null);
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
