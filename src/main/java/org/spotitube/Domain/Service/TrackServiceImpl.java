package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Track.ITrackDAO;
import org.spotitube.Domain.Model.TracksResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TrackServiceImpl implements TrackService {

    @Inject
    private ITrackDAO<Track> trackMapper;

    @Override
    public TracksResponse getTracksByPlaylistId(int playlistId) {
        TracksResponse response = new TracksResponse();
        response.setTracks(trackMapper.getAllTracksByPlaylistId(playlistId));
        return response;
    }

    @Override
    public TracksResponse getAllAvailableTracksByPlaylistId(int PlaylistId) {
        TracksResponse response = new TracksResponse();
        response.setTracks(trackMapper.getAllAvailableTracksByPlaylistId(PlaylistId));
        return response;
    }
}
