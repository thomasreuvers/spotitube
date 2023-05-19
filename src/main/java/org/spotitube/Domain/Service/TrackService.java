package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Domain.Model.TracksResponse;

public interface TrackService {
    TracksResponse getTracksByPlaylistId(int playlistId);

    TracksResponse getAllAvailableTracksByPlaylistId(int PlaylistId);

    void addTrackToPlaylist(Track track, int playlistId);
}
