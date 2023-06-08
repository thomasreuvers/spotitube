package org.spotitube.Domain.Service.Track;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Track.ITrackMapper;
import org.spotitube.Domain.Model.TracksResponse;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrackServiceTest {

    @InjectMocks
    private TrackService trackService;

    @Mock
    private ITrackMapper trackMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAllTracksInPlaylist() {
        // Red: Write a failing test case
        int playlistId = 1;
        List<Track> tracks = Arrays.asList(
                new Track(1, "Track 1", "Track 1 performer",180, "album 1", 100, LocalDate.now(), "Track 1 description", true),
                new Track(2, "Track 2", "Track 2 performer", 240, "album 2", 2, LocalDate.now(), "Track 2 description", false)
        );
        Mockito.when(trackMapper.allTracksInPlaylist(playlistId)).thenReturn(tracks);

        TracksResponse expectedResponse = new TracksResponse();
        expectedResponse.setTracks(tracks);

        // Green: Write the minimum amount of code to make the test pass
        TracksResponse result = trackService.allTracksInPlaylist(playlistId);

        // Assertions
        Assertions.assertEquals(expectedResponse.getTracks(), result.getTracks());
    }

    @Test
    public void testAllAvailableTracks() {
        // Red: Write a failing test case
        int playlistId = 1;
        List<Track> tracks = Arrays.asList(
                new Track(1, "Track 1", "Track 1 performer",180, "album 1", 100, LocalDate.now(), "Track 1 description", true),
                new Track(2, "Track 2", "Track 2 performer", 240, "album 2", 2, LocalDate.now(), "Track 2 description", false)
        );
        Mockito.when(trackMapper.allAvailableTracks(playlistId)).thenReturn(tracks);

        TracksResponse expectedResponse = new TracksResponse();
        expectedResponse.setTracks(tracks);

        // Green: Write the minimum amount of code to make the test pass
        TracksResponse result = trackService.allAvailableTracks(playlistId);

        // Assertions
        Assertions.assertEquals(expectedResponse.getTracks(), result.getTracks());
    }

    @Test
    public void testAddToPlaylist() {
        // Red: Write a failing test case
        Track track = new Track(1, "Track 1", "Track 1 performer",180, "album 1", 100, LocalDate.now(), "Track 1 description", true);
        int playlistId = 1;

        // Green: Write the minimum amount of code to make the test pass
        trackService.addToPlaylist(track, playlistId);

        // Assertions
        Mockito.verify(trackMapper).addToPlaylist(track.getId(), playlistId, track.isOfflineAvailable());
    }

    @Test
    public void testDeleteFromPlaylist() {
        // Red: Write a failing test case
        int playlistId = 1;
        int trackId = 1;

        // Green: Write the minimum amount of code to make the test pass
        trackService.deleteFromPlaylist(playlistId, trackId);

        // Assertions
        Mockito.verify(trackMapper).deleteFromPlaylist(playlistId, trackId);
    }
}