package org.spotitube.Data.Mapper.Track;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Context.MockConnectionContext;
import org.spotitube.Data.Mapper.Playlist.PlaylistMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrackMapperTest {

    private TrackMapper trackMapper;
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
        trackMapper = new TrackMapper(new MockConnectionContext(connectionMock));
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(connectionMock, statementMock, resultSetMock);
        Mockito.verifyNoMoreInteractions(connectionMock, statementMock, resultSetMock);
    }

    @Test
    void testAllTracksInPlaylist() throws SQLException {
        // Arrange
        int playlistId = 1;
        Track track = new Track(1, "Title", "Performer", 2000, "Album", 300, LocalDate.now(), "Description", false);
        List<Track> expectedTracks = new ArrayList<>(); // Create expected tracks

        // Stub the method invocations on the mocks
        when(resultSetMock.next()).thenReturn(true, false); // Simulate one row in the result set
        when(resultSetMock.getObject("id")).thenReturn(1); // Stub the necessary columns
        when(resultSetMock.getObject("title")).thenReturn(track.getTitle());
        when(resultSetMock.getObject("performer")).thenReturn(track.getPerformer());
        when(resultSetMock.getObject("duration")).thenReturn(track.getDuration());
        when(resultSetMock.getObject("album")).thenReturn(track.getAlbum());
        when(resultSetMock.getObject("playcount")).thenReturn(track.getPlaycount());
        when(resultSetMock.getObject("publicationdate")).thenReturn(track.getPublicationDate());
        when(resultSetMock.getObject("description")).thenReturn(track.getDescription());
        when(resultSetMock.getObject("OfflineAvailable")).thenReturn((short) (track.isOfflineAvailable()?1:0));

        // Add the expected track to the list
        expectedTracks.add(track);

        // Act
        List<Track> actualTracks = trackMapper.allTracksInPlaylist(playlistId);

        // Assert
        assertEquals(expectedTracks, actualTracks);
        verify(statementMock).setObject(1, playlistId);
        verify(resultSetMock, times(2)).next(); // Called for one row and then reached end
        verify(resultSetMock).getObject("id");
        verify(resultSetMock).getObject("title");
        verify(resultSetMock).getObject("performer");
        verify(resultSetMock).getObject("duration");
        verify(resultSetMock).getObject("album");
        verify(resultSetMock).getObject("playcount");
        verify(resultSetMock).getObject("publicationdate");
        verify(resultSetMock).getObject("description");
        verify(resultSetMock).getBoolean("OfflineAvailable");
    }

}
