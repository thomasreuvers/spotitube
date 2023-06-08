package org.spotitube.Domain.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserMapper;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Service.Token.ITokenService;
import org.spotitube.Domain.Service.User.IUserService;
import org.spotitube.Domain.Service.User.UserService;

import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {
    private IUserMapper userMapper;
    private ITokenService ITokenService;
    private IUserService IUserService;

    @BeforeEach
    public void setUp() throws Exception {
        userMapper = mock(IUserMapper.class);
        ITokenService = mock(ITokenService.class);
        IUserService = new UserService();

        Field userMapperField = UserService.class.getDeclaredField("userMapper");
        userMapperField.setAccessible(true);
        userMapperField.set(IUserService, userMapper);

        Field tokenServiceField = UserService.class.getDeclaredField("tokenService");
        tokenServiceField.setAccessible(true);
        tokenServiceField.set(IUserService, ITokenService);
    }

    @Test
    public void testLoginUser() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("thomas", "test123!");
        User expectedUser = new User(loginRequest.getUser(), loginRequest.getPassword(), null);
        when(userMapper.findByUsername(loginRequest.getUser())).thenReturn(Optional.of(expectedUser));

        // Act
        LoginResponse response = IUserService.loginUser(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals(loginRequest.getUser(), expectedUser.getUsername());
        assertEquals(loginRequest.getPassword(), expectedUser.getPassword());
        verify(userMapper, times(1)).findByUsername(loginRequest.getUser());
    }
}
