package org.spotitube.Domain.Service.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Model.PlaylistResponse;

public interface IPlaylistService {

    PlaylistResponse allPlaylists(String token);

    void deletePlaylist(int playlistId);

    void addPlaylist(Playlist playlist, String token);

    void updatePlaylist(int id, Playlist playlist);
}
