package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.UUID;

@RequestScoped
public class TokenServiceImpl implements TokenService {

    @Inject
    private IUserDAO<User> userMapper;

    @Override
    public String GenerateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Boolean validateToken(String token) {
        return userMapper.findByToken(token).isPresent();
    }
}
