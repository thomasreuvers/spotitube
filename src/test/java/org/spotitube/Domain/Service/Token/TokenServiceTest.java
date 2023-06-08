package org.spotitube.Domain.Service.Token;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private IUserMapper userMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGenerateToken() {
        // Red: Write a failing test case
        String token = tokenService.GenerateToken();

        // Green: Write the minimum amount of code to make the test pass
        Assertions.assertNotNull(token);
        Assertions.assertNotEquals("", token);
    }

    // FIXME: NEEDS IMPLEMENTATION
    @Test
    public void testValidateToken_ValidToken() {
        // Red: Write a failing test case

        // Green: Write the minimum amount of code to make the test pass

        // Assertions
    }

    @Test
    public void testValidateToken_InvalidToken() {
        // Red: Write a failing test case
        String token = "invalid-token";

        Mockito.when(userMapper.findByToken(token)).thenReturn(null);

        // Green: Write the minimum amount of code to make the test pass
        boolean result = tokenService.validateToken(token);

        // Assertions
        Assertions.assertFalse(result);
    }
}