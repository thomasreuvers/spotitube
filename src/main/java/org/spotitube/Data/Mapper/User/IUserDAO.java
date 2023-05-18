package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.IBaseMapper;

import java.util.Optional;

public interface IUserDAO<T> extends IBaseMapper<T> {
    Optional<User> findByUsername(String username);
}
