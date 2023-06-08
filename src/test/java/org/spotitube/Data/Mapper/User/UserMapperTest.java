package org.spotitube.Data.Mapper.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.Context.MockConnectionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class UserMapperTest {
    private UserMapper userMapper;
    private Connection connectionMock;
    private PreparedStatement statementMock;
    private ResultSet resultSetMock;

    @BeforeEach
    void setUp() throws SQLException {
        // Create mocks for connection, statement, and result set
        connectionMock = Mockito.mock(Connection.class);
        statementMock = Mockito.mock(PreparedStatement.class);
        resultSetMock = Mockito.mock(ResultSet.class);

        // Stub the method invocations on the mocks
        Mockito.when(connectionMock.prepareStatement(Mockito.anyString())).thenReturn(statementMock);
        Mockito.when(statementMock.executeQuery()).thenReturn(resultSetMock);

        // Create the UserMapper instance with the mock connection
        userMapper = new UserMapper(new MockConnectionContext(connectionMock));
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(connectionMock, statementMock, resultSetMock);
        Mockito.verifyNoMoreInteractions(connectionMock, statementMock, resultSetMock);
    }

    @Test
    void testFindByUsername() throws SQLException {
        // Stub the result set to return a single row with the expected data
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(resultSetMock.getObject("id")).thenReturn(1);
        Mockito.when(resultSetMock.getObject("username")).thenReturn("testuser");
        Mockito.when(resultSetMock.getObject("password")).thenReturn("password123");

        // Invoke the findByUsername method
        User user = userMapper.findByUsername("testuser");

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement("SELECT * FROM users WHERE username=?");
        verify(statementMock).setString(1, "testuser");
        verify(statementMock).executeQuery();

        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }

    @Test
    void testFindByToken() throws SQLException {
        // Stub the result set to return a single row with the expected data
        Mockito.when(resultSetMock.next()).thenReturn(true);
        Mockito.when(resultSetMock.getObject("id")).thenReturn(1);
        Mockito.when(resultSetMock.getObject("username")).thenReturn("testuser");
        Mockito.when(resultSetMock.getObject("password")).thenReturn("password123");
        Mockito.when(resultSetMock.getObject("token")).thenReturn("token123");


        // Invoke the findByUsername method
        User user = userMapper.findByToken("token123");

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement("SELECT * FROM users WHERE token=?");
        verify(statementMock).setString(1, "token123");
        verify(statementMock).executeQuery();

        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("token123", user.getToken());
    }

    // FIXME: THIS ERRORS
    @Test
    void testNewUser() throws SQLException {
        // Create a user object
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password123");

        // Stub the executeUpdate method to return a value (e.g., 1)
        Mockito.when(statementMock.executeUpdate()).thenReturn(1);

        // Invoke the newUser method
        userMapper.newUser(user);

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement("INSERT INTO users(username, password) VALUES(?,?)");
        verify(statementMock).setString(1, "testuser");
        verify(statementMock).setString(2, DigestUtils.sha256Hex("password123"));
        verify(statementMock).executeUpdate();
    }

    // FIXME: THIS ERRORS
    @Test
    void testUpdateToken() throws SQLException {
        // Invoke the updateToken method
        userMapper.updateToken(1, "token123");

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement("UPDATE users SET token=? WHERE id=?");
        verify(statementMock).setString(1, "token123");
        verify(statementMock).setInt(2, 1);
        verify(statementMock).executeUpdate();

        // Additional assertions can be made depending on the expected behavior
    }
}