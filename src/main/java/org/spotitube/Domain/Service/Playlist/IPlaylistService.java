package org.spotitube.Domain.Service.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Model.PlaylistResponse;

public interface IPlaylistService {

    PlaylistResponse allPlaylists(int userId);

    void deletePlaylist(int playlistId);

    void addPlaylist(Playlist playlist, int userId);

    void updatePlaylist(int id, Playlist playlist);
}
