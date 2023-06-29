package org.spotitube.Domain.Service.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserMapper;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.Token.ITokenService;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private IUserMapper userMapper;

    @Mock
    private ITokenService tokenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoginUser_ValidCredentials() {
        // Red: Write a failing test case
        String unHashedPassword = "password";
        String expectedToken = "generatedToken";

        LoginRequest model = new LoginRequest("username", unHashedPassword);
        User user = new User("username", DigestUtils.sha256Hex(unHashedPassword));

        Mockito.when(userMapper.findByUsername(model.getUser())).thenReturn(user);
        Mockito.when(tokenService.GenerateToken(user.getUsername(), user.getRole())).thenReturn(expectedToken);

        // Green: Write the minimum amount of code to make the test pass
        LoginResponse response = userService.loginUser(model);

        // Assertions
        Assertions.assertEquals(expectedToken, response.getToken());
        Assertions.assertEquals(user.getUsername(), response.getUser());
        Mockito.verify(userMapper).updateToken(user.getId(), expectedToken);
    }

    @Test
    public void testLoginUser_UserDoesNotExist() {
        // Red: Write a failing test case
        LoginRequest model = new LoginRequest("username", "password");

        Mockito.when(userMapper.findByUsername(model.getUser())).thenReturn(null);

        // Green: Write the minimum amount of code to make the test pass
        Assertions.assertThrows(AuthenticationException.class, () -> userService.loginUser(model));
    }

    @Test
    public void testLoginUser_InvalidCredentials() {
        // Red: Write a failing test case
        LoginRequest model = new LoginRequest("username", "password");
        User user = new User( "username", "wrongPassword");

        Mockito.when(userMapper.findByUsername(model.getUser())).thenReturn(user);

        // Green: Write the minimum amount of code to make the test pass
        Assertions.assertThrows(AuthenticationException.class, () -> userService.loginUser(model));
    }

    @Test
    public void testRegisterUser() {
        // Red: Write a failing test case
        RegisterModel model = new RegisterModel("username", "password");
        User expectedUser = model.asUserEntity(model);

        // Hash password of expected user as the `asUserEntity()` method does not handle this.
        expectedUser.setPassword(DigestUtils.sha256Hex(expectedUser.getPassword()));
        expectedUser.setId(1);

        // Green: Write the minimum amount of code to make the test pass
        userService.registerUser(model);

        // Assertions
        Mockito.verify(userMapper).newUser(Mockito.any(User.class));
    }
}