package org.spotitube.Domain.Service.Token;

public interface ITokenService {

    String GenerateToken();

    Boolean validateToken(String token);
}
