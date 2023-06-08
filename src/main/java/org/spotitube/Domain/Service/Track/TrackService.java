package org.spotitube.Domain.Service.Track;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Track.ITrackMapper;
import org.spotitube.Domain.Model.TracksResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TrackService implements ITrackService {

    @Inject
    private ITrackMapper trackMapper;

    @Override
    public TracksResponse allTracksInPlaylist(int playlistId) {
        TracksResponse tracksResponse = new TracksResponse();
        tracksResponse.setTracks(trackMapper.allTracksInPlaylist(playlistId));
        return tracksResponse;
    }

    @Override
    public TracksResponse allAvailableTracks(int playlistId) {
        TracksResponse response = new TracksResponse();
        response.setTracks(trackMapper.allAvailableTracks(playlistId));
        return response;
    }

    @Override
    public void addToPlaylist(Track track, int playlistId) {
        trackMapper.addToPlaylist(track.getId(), playlistId, track.isOfflineAvailable());
    }

    @Override
    public void deleteFromPlaylist(int playlistId, int trackId) {
        trackMapper.deleteFromPlaylist(playlistId, trackId);
    }
}
