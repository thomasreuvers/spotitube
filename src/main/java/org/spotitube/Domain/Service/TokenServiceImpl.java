package org.spotitube.Domain.Service;

import javax.enterprise.context.RequestScoped;
import java.util.UUID;

@RequestScoped
public class TokenServiceImpl implements TokenService{
    @Override
    public String GenerateToken() {
        return UUID.randomUUID().toString();
    }
}
