package org.spotitube.Domain.Service.Token;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.UUID;

@RequestScoped
public class TokenService implements ITokenService {

    @Inject
    private IUserMapper userMapper;

    @Override
    public String GenerateToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Boolean validateToken(String token) {
        User user = userMapper.findByToken(token);
        return user != null;
    }
}
