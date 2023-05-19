package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Playlist.IPlaylistDAO;
import org.spotitube.Domain.Model.AllPlaylistResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PlaylistServiceImpl implements PlaylistService{

    @Inject
    private IPlaylistDAO<Playlist> playlistMapper;

    @Override
    public AllPlaylistResponse getAllPlaylists() {
        List<Playlist> AllPlaylists = playlistMapper.AllPlaylists();
        int totalLength = calculateTotalPlayTime(AllPlaylists);

        return new AllPlaylistResponse(AllPlaylists, totalLength);
    }

    @Override
    public void deletePlaylist(int playlistId) {
        playlistMapper.delete(playlistId);
    }

    private int calculateTotalPlayTime(List<Playlist> playlists) {
        int totalPlaytime = 0;

        for (Playlist playlist: playlists) {
            for(Track track: playlist.getTracks()) {
                totalPlaytime += track.getDuration();
            }
        }
        return totalPlaytime;
    }
}
