package org.spotitube.Data.Mapper.Playlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Mapper.Context.MockConnectionContext;
import org.spotitube.Data.Mapper.User.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class PlaylistMapperTest {
    private PlaylistMapper playlistMapper;
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
        playlistMapper = new PlaylistMapper(new MockConnectionContext(connectionMock));
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(connectionMock, statementMock, resultSetMock);
        Mockito.verifyNoMoreInteractions(connectionMock, statementMock, resultSetMock);
    }

    @Test
    void testAllPlaylists() throws SQLException {
        String query = "SELECT * FROM playlists";

        // Stub the result set to return multiple rows with the expected data
        Mockito.when(resultSetMock.next()).thenReturn(true, true, false);
        Mockito.when(resultSetMock.getInt("id")).thenReturn(1, 2);
        Mockito.when(resultSetMock.getString("name")).thenReturn("Playlist 1", "Playlist 2");
        Mockito.when(resultSetMock.getObject("userId")).thenReturn(null, null);

        // Invoke the allPlaylists method
        List<Playlist> playlists = playlistMapper.allPlaylists();

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement(query);
        verify(statementMock).executeQuery();

        assertEquals(2, playlists.size());
        // Assert the values of the returned playlists
        assertEquals(1, playlists.get(0).getId());
        assertEquals("Playlist 1", playlists.get(0).getName());
        assertEquals(0, playlists.get(0).getUserId());
    }
}
