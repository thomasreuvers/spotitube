package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Model.AllPlaylistResponse;

public interface PlaylistService {

    AllPlaylistResponse getAllPlaylists();

    void deletePlaylist(int playlistId);

    void addPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);
}
