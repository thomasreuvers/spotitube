package org.spotitube.Domain.Service;

public interface TokenService {

    String GenerateToken();

    Boolean validateToken(String token);
}
