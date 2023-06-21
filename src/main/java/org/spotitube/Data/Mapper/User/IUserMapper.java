package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;


public interface IUserMapper {
    User findByUsername(String username);
    User findByToken(String token);
    void newUser(User user);
    void updateToken(int id, String token);
}
