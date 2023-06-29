package org.spotitube.Domain.Service.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Exception.CustomException;
import org.spotitube.Spotitube;

import javax.crypto.SecretKey;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@RequestScoped
public class TokenService implements ITokenService {
    // Secret key for signing and verifying the token
    private SecretKey secretKey;

    // Token expirationDate
    private long expirationTime;

    public TokenService() {
        try(InputStream input = Spotitube.class.getClassLoader().getResourceAsStream("token.properties"))
        {
            Properties prop = new Properties();

            if(input == null) {
                System.out.println("Sorry, unable to find 'token.properties'");
                return;
            }

            prop.load(input);

            // Load secret key from properties file
            secretKey = Keys.hmacShaKeyFor(prop.getProperty("secret-key").getBytes());

            // Load expiration time from properties file
            expirationTime = (long) Integer.parseInt(prop.getProperty("expiration-time-in-minutes")) * 60 * 1000;
        }catch(IOException e) {
            throw new CustomException(e);
        }
    }

    @Override
    public String GenerateToken(String subject, String role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime);

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(secretKey);

        return builder.compact();
    }

    @Override
    public Claims ValidateAndParseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // Token verification failed
            throw new AuthenticationException("Invalid JWT token");
        }
    }
}
