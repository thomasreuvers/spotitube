package org.spotitube.Domain.Service.Token;

import io.jsonwebtoken.Claims;

public interface ITokenService {

    String GenerateToken(String subject, String role);

    Claims ValidateAndParseToken(String token);
}
