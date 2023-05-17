package org.spotitube.Domain.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spotitube.Api.Resource.UserResource;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserDao;
import org.spotitube.Data.Mapper.User.UserMapper;
import org.spotitube.Domain.Model.LoginModel;

import javax.ws.rs.core.Response;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class UserServiceTest {
    private IUserDao userMapper;
    private TokenService tokenService;
    private UserService userService;

    @BeforeEach
    public void setUp() throws Exception {
        userMapper = mock(IUserDao.class);
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
        LoginModel loginModel = new LoginModel("thomas", "test123!");
        User expectedUser = new User(loginModel.getUsername(), loginModel.getPassword(), null);
        when(userMapper.findByUsername(loginModel.getUsername())).thenReturn(Optional.of(expectedUser));

        // Act
        User user = userService.loginUser(loginModel);

        // Assert
        assertNotNull(user);
        assertEquals(loginModel.getUsername(), user.getUsername());
        assertEquals(loginModel.getPassword(), user.getPassword());
        verify(userMapper, times(1)).findByUsername(loginModel.getUsername());
    }
}
