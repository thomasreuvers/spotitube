package org.spotitube.Data.Mapper.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.IBaseMapper;

import java.util.List;

public interface IPlaylistMapper {
    List<Playlist> allPlaylists();

    void newPlaylist(Playlist playlist, int userId);
    void updatePlaylist(Playlist playlist);
    void deletePlaylist(int id);
}
