package org.spotitube.Domain.Service;

import org.spotitube.Domain.Model.TracksInPlaylistResponse;

public interface TrackService {
    TracksInPlaylistResponse getTracksByPlaylistId(int playlistId);
}
