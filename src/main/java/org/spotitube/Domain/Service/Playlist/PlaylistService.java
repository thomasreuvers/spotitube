package org.spotitube.Domain.Service.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.Playlist.IPlaylistMapper;
import org.spotitube.Data.Mapper.Track.ITrackMapper;
import org.spotitube.Data.Mapper.User.IUserMapper;
import org.spotitube.Domain.Model.PlaylistResponse;
import org.spotitube.Domain.Model.PlaylistViewModel;
import org.spotitube.Domain.Service.Track.ITrackService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class PlaylistService implements IPlaylistService {

    @Inject
    private IPlaylistMapper playlistMapper;

    @Inject
    private ITrackMapper trackMapper;

    @Inject
    private IUserMapper userMapper;

    /**
     * Retrieves all playlists and their details.
     * @param token The authentication token of the logged-in user.
     * @return A {@link PlaylistResponse} object containing a list of {@link PlaylistViewModel} objects and the total play time.
     */
    @Override
    public PlaylistResponse allPlaylists(String token) {
        List<Playlist> AllPlaylists = playlistMapper.allPlaylists();

        List<PlaylistViewModel> playlistViewModels = new ArrayList<>();

        for(Playlist playlist : AllPlaylists) {
            PlaylistViewModel playlistViewModel = new PlaylistViewModel();
            playlistViewModel.setId(playlist.getId());
            playlistViewModel.setName(playlist.getName());

            // Get all tracks from playlist
            List<Track> tracks = trackMapper.allTracksInPlaylist(playlist.getId());
            playlistViewModel.setTracks(tracks);

            User user = userMapper.findByToken(token);
            // Check if current logged-in user is the owner of the playlist
            if(user != null) {
                playlistViewModel.setOwner(user.getId() == playlist.getUserId());
            }else {
                playlistViewModel.setOwner(false);
            }

            playlistViewModels.add(playlistViewModel);
        }

        int totalLength = calculateTotalPlayTime(playlistViewModels);

        return new PlaylistResponse(playlistViewModels, totalLength);
    }

    @Override
    public void deletePlaylist(int playlistId) {
        playlistMapper.deletePlaylist(playlistId);
    }

    @Override
    public void addPlaylist(Playlist playlist, String token) {
        User user = userMapper.findByToken(token);

        if (user == null) {
            throw new RuntimeException("User does with given token does not exist!");
        }

        playlistMapper.newPlaylist(playlist, user.getId());
    }

    @Override
    public void updatePlaylist(int id, Playlist playlist) {
        playlist.setId(id);
        playlistMapper.updatePlaylist(playlist);
    }

    private int calculateTotalPlayTime(List<PlaylistViewModel> playlists) {
        int totalPlaytime = 0;

        for (PlaylistViewModel playlist: playlists) {
            for(Track track: playlist.getTracks()) {
                totalPlaytime += track.getDuration();
            }
        }
        return totalPlaytime;
    }
}
