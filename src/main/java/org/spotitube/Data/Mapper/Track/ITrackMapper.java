package org.spotitube.Data.Mapper.Track;

import org.spotitube.Data.Entity.Track;

import java.util.List;

public interface ITrackMapper {

    List<Track> allTracksInPlaylist(int playlistId);
    List<Track> allAvailableTracks(int playlistId);

    void addToPlaylist(int id, int playlistId, boolean offlineAvailable);

    void deleteFromPlaylist(int id, int playlistId);
}
