package org.spotitube.Domain.Model;

import org.spotitube.Data.Entity.Track;

import java.util.List;

public class TracksInPlaylistResponse {
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}
