package org.spotitube.Data.Mapper.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.BaseMapper;
import org.spotitube.Data.Mapper.Track.ITrackMapper;
import org.spotitube.Data.Mapper.User.IUserMapper;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PlaylistMapper extends BaseMapper<Playlist> implements IPlaylistMapper {

    public PlaylistMapper() {
        super();
    }

    @Override
    public List<Playlist> allPlaylists() {
        String query = "SELECT * FROM playlists";
        return all(query, new ArrayList<>());
    }

    @Override
    public void newPlaylist(Playlist playlist, int userId) {
        String query = "INSERT INTO playlists(name, userId) VALUES (?, ?)";
        save(query, Arrays.asList(playlist.getName(), userId));
    }

    @Override
    public void updatePlaylist(Playlist playlist) {
        String query = "UPDATE playlists SET name=? WHERE id=?";
        save(query, Arrays.asList(playlist.getName(), playlist.getId()));
    }

    @Override
    public void deletePlaylist(int id) {
        deletePlaylistTrackEntry(id);
        String query = "DELETE FROM playlists WHERE id=?";
        save(query, Arrays.asList(id));
    }

    private void deletePlaylistTrackEntry(int id) {
        String query = "DELETE FROM playlistTracks WHERE playlistId=?";
        save(query, Arrays.asList(id));
    }
}
