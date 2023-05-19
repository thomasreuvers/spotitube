package org.spotitube.Domain.Service;

import org.spotitube.Domain.Model.AllPlaylistResponse;

public interface PlaylistService {

    AllPlaylistResponse getAllPlaylists();

    void deletePlaylist(int playlistId);
}
