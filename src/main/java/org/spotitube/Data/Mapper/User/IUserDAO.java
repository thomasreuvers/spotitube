package org.spotitube.Data.Mapper.User;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.IBaseMapper;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface IUserDAO<T> extends IBaseMapper<T> {
    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
}
