package org.spotitube.Domain.Service.Track;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Domain.Model.TracksResponse;

public interface ITrackService {
    TracksResponse allTracksInPlaylist(int playlistId);

    TracksResponse allAvailableTracks(int playlistId);

    void addToPlaylist(Track track, int playlistId);

    void deleteFromPlaylist(int playlistId, int trackId);
}
