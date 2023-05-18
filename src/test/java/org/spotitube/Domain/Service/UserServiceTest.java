package org.spotitube.Domain.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserDAO;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {
    private IUserDAO userMapper;
    private TokenService tokenService;
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        userMapper = mock(IUserDAO.class);
        tokenService = mock(TokenService.class);
        userService = new UserServiceImpl();

        Field userMapperField = UserServiceImpl.class.getDeclaredField("userMapper");
        userMapperField.setAccessible(true);
        userMapperField.set(userService, userMapper);

        Field tokenServiceField = UserServiceImpl.class.getDeclaredField("tokenService");
        tokenServiceField.setAccessible(true);
        tokenServiceField.set(userService, tokenService);
    }

    @Test
    public void testLoginUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("thomas", "test123!");
        User expectedUser = new User(loginRequest.getUser(), loginRequest.getPassword(), null);
        when(userMapper.findByUsername(loginRequest.getUser())).thenReturn(Optional.of(expectedUser));

        // Act
        LoginResponse response = userService.loginUser(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(loginRequest.getUser(), expectedUser.getUsername());
        assertEquals(loginRequest.getPassword(), expectedUser.getPassword());
        verify(userMapper, times(1)).findByUsername(loginRequest.getUser());
    }
}
