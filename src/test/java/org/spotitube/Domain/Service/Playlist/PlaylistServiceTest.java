package org.spotitube.Domain.Service.Playlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.Playlist.IPlaylistMapper;
import org.spotitube.Data.Mapper.Track.ITrackMapper;
import org.spotitube.Data.Mapper.User.IUserMapper;
import org.spotitube.Domain.Model.PlaylistResponse;
import org.spotitube.Domain.Model.PlaylistViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistServiceTest {
    @InjectMocks
    private PlaylistService playlistService;

    @Mock
    private IPlaylistMapper playlistMapper;

    @Mock
    private ITrackMapper trackMapper;

    @Mock
    private IUserMapper userMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void allPlaylists() {
        // Red: Write a failing test case
        String token = "abc123";
        List<Playlist> playlists = Arrays.asList(
                new Playlist(1, "Playlist 1", 1),
                new Playlist(2, "Playlist 2", 2)
        );
        Mockito.when(playlistMapper.allPlaylists()).thenReturn(playlists);

        List<Track> tracks = Arrays.asList(
                new Track(1, "Track 1", "Track 1 performer",180, "album 1", 100, LocalDate.now(), "Track 1 description", true),
                new Track(2, "Track 2", "Track 2 performer", 240, "album 2", 2, LocalDate.now(), "Track 2 description", false)
        );
        Mockito.when(trackMapper.allTracksInPlaylist(1)).thenReturn(tracks);
        Mockito.when(trackMapper.allTracksInPlaylist(2)).thenReturn(new ArrayList<>());

        User user = new User(1,"John Doe", "test123", token);
        Mockito.when(userMapper.findByToken(token)).thenReturn(user);

        PlaylistResponse expectedResponse = new PlaylistResponse(
                Arrays.asList(
                        new PlaylistViewModel(1, "Playlist 1", true, tracks),
                        new PlaylistViewModel(2, "Playlist 2", false, new ArrayList<>())
                ),
                420
        );

        // Green: Write the minimum amount of code to make the test pass
        PlaylistResponse result = playlistService.allPlaylists(token);

        // Assertions
        Assertions.assertEquals(expectedResponse.getPlaylists().size(), result.getPlaylists().size());
        for (int i = 0; i < expectedResponse.getPlaylists().size(); i++) {
            PlaylistViewModel expectedPlaylist = expectedResponse.getPlaylists().get(i);
            PlaylistViewModel actualPlaylist = result.getPlaylists().get(i);
            Assertions.assertEquals(expectedPlaylist.getId(), actualPlaylist.getId());
            Assertions.assertEquals(expectedPlaylist.getName(), actualPlaylist.getName());
            Assertions.assertEquals(expectedPlaylist.getTracks().size(), actualPlaylist.getTracks().size());
        }
        Assertions.assertEquals(expectedResponse.getLength(), result.getLength());
    }

    @Test
    void deletePlaylist() {
        // Red: Write a failing test case
        int playlistId = 1;

        // Green: Write the minimum amount of code to make the test pass
        playlistService.deletePlaylist(playlistId);

        // Assertions
        Mockito.verify(playlistMapper).deletePlaylist(playlistId);
    }

    @Test
    void addPlaylist() {
        // Red: Write a failing test case
        Playlist playlist = new Playlist(1, "New Playlist", 1);
        String token = "abc123";

        User user = new User(1, "John Doe", "test123", token);
        Mockito.when(userMapper.findByToken(token)).thenReturn(user);

        // Green: Write the minimum amount of code to make the test pass
        playlistService.addPlaylist(playlist, token);

        // Assertions
        Mockito.verify(playlistMapper).newPlaylist(playlist, user.getId());
    }

    @Test
    void updatePlaylist() {
        // Red: Write a failing test case
        int playlistId = 1;

        Playlist playlist = new Playlist(1, "Updated Playlist", 1);

        // Green: Write the minimum amount of code to make the test pass
        playlistService.updatePlaylist(playlistId, playlist);

        // Assertions
        Mockito.verify(playlistMapper).updatePlaylist(playlist);
        Assertions.assertEquals(playlistId, playlist.getId());
    }
}