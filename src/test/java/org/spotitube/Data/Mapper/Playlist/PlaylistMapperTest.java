package org.spotitube.Data.Mapper.Playlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Mapper.Context.MockConnectionContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        when(connectionMock.prepareStatement(Mockito.anyString())).thenReturn(statementMock);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);

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
        // Arrange
        String query = "SELECT * FROM playlists";

        // Stub the result set to return multiple rows with the expected data
        when(resultSetMock.next()).thenReturn(true, true, false);
        when(resultSetMock.getObject("id")).thenReturn(1, 2);
        when(resultSetMock.getObject("name")).thenReturn("Playlist 1", "Playlist 2");
        when(resultSetMock.getObject("userId")).thenReturn(3, 4);

        // Act
        List<Playlist> playlists = playlistMapper.allPlaylists();

        // Verify the interactions and assertions
        verify(connectionMock).prepareStatement(query);
        verify(statementMock).executeQuery();


        // Assert
        assertEquals(2, playlists.size());

        // Playlist 1
        assertEquals(1, playlists.get(0).getId());
        assertEquals("Playlist 1", playlists.get(0).getName());
        assertEquals(3, playlists.get(0).getUserId());

        // Playlist 2
        assertEquals(2, playlists.get(1).getId());
        assertEquals("Playlist 2", playlists.get(1).getName());
        assertEquals(4, playlists.get(1).getUserId());
    }

    @Test
    void testNewPlaylist() throws SQLException {
        // Arrange
        int userId = 1;
        Playlist playlist = new Playlist();
        playlist.setName("Playlist 1");
        playlist.setUserId(userId);

        // Stub the method invocations on the mocks
        when(statementMock.executeUpdate()).thenReturn(1);

        // Act
        playlistMapper.newPlaylist(playlist, userId);

        // Assert
        verify(connectionMock).prepareStatement("INSERT INTO playlists(name, userId) VALUES(?,?)");
        verify(statementMock).setObject(1, "Playlist 1");
        verify(statementMock).setObject(2, userId);
        verify(statementMock).executeUpdate();
    }

    @Test
    void testUpdatePlaylist() throws SQLException {
        // Arrange
        Playlist playlist = new Playlist(1, "Playlist 2", 3);

        // Act
        playlistMapper.updatePlaylist(playlist);

        // Assert
        verify(connectionMock).prepareStatement("UPDATE playlists SET name=? WHERE id=?");
        verify(statementMock).setObject(1, playlist.getName());
        verify(statementMock).setObject(2, playlist.getId());
        verify(statementMock).executeUpdate();
    }

    @Test
    void testDeletePlaylist() throws SQLException {
        // Arrange
        int id = 1;

        // Stub the method invocations on the mocks
        when(statementMock.executeUpdate())
                .thenReturn(1) // Return 1 for the first invocation
                .thenReturn(0); // Return 0 for the second invocation

        // Act
        playlistMapper.deletePlaylist(id);

        // Assert
        verify(statementMock, times(2)).executeUpdate(); // Verify it was called twice
        verify(statementMock, times(2)).setObject(1, id);
        verify(statementMock, times(2)).close(); // Verify it was closed twice
    }
}
