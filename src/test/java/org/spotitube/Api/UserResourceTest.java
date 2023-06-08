package org.spotitube.Api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.spotitube.Api.Resource.UserController;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Service.User.IUserService;

import javax.ws.rs.core.Response;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserResourceTest {
    private UserController userResource;
    private IUserService IUserService;

    @BeforeEach
    public void setUp() throws Exception {
        IUserService = mock(IUserService.class);
        userResource = new UserController();

        Field userServiceField = UserController.class.getDeclaredField("userService");
        userServiceField.setAccessible(true);
        userServiceField.set(userResource, IUserService);
    }


//    @Test
//    public void testLogin_Successful() {
//        // Arrange
//        LoginModel loginModel = new LoginModel("thomas", "test123!");
//        User expectedUser = new User(loginModel.getUsername(), loginModel.getPassword(), null);
//        when(userService.loginUser(loginModel)).thenReturn(expectedUser);
//
//        // Act
//        Response response = userResource.login(loginModel);
//
//        // Assert
//        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
//        assertEquals(expectedUser, response.getEntity());
//        verify(userService, times(1)).loginUser(loginModel);
//    }

    @Test
    public void testLogin_Successful() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest("thomas", "test123!");
        LoginResponse expectedResponse = new LoginResponse("", loginRequest.getUser());
        when(IUserService.loginUser(loginRequest)).thenReturn(expectedResponse);

        // Act
        Response response = userResource.login(loginRequest);

        // Assert
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expectedResponse, response.getEntity());
        verify(IUserService, times(1)).loginUser(loginRequest);
    }

//    @Test
//    public void testLogin_AuthenticationException() {
//        // Arrange
//        LoginModel loginModel = new LoginModel("username", "password");
//        AuthenticationException exception = new AuthenticationException("Invalid credentials");
//        when(userService.loginUser(loginModel)).thenThrow(exception);
//
//        // Act
//        Response response = userResource.login(loginModel);
//
//        // Assert
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
//        assertEquals(exception.getMessage(), response.getEntity());
//        verify(userService, times(1)).loginUser(loginModel);
//    }
//
//    @Test
//    public void testLogin_InternalServerError() {
//        // Arrange
//        LoginModel loginModel = new LoginModel("username", "password");
//        Exception exception = new Exception("An error occurred");
//        when(userService.loginUser(loginModel)).thenThrow(exception);
//
//        // Act
//        Response response = userResource.login(loginModel);
//
//        // Assert
//        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
//        assertEquals(exception.getMessage(), response.getEntity());
//        verify(userService, times(1)).loginUser(loginModel);
//    }
}
