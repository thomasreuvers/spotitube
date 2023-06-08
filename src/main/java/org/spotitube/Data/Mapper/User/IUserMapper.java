package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;

import java.util.Optional;

public interface IUserMapper {
    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
    void newUser(User user);
    void updateToken(int id, String token);
}
