package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;

import java.util.Optional;

public interface IUserDao {
    Optional<User> findByUsername(String username);
}
