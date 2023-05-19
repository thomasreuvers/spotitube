package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.Track.ITrackDAO;
import org.spotitube.Domain.Model.TracksInPlaylistResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TrackServiceImpl implements TrackService {

    @Inject
    private ITrackDAO<Track> trackMapper;

    @Override
    public TracksInPlaylistResponse getTracksByPlaylistId(int playlistId) {
        TracksInPlaylistResponse response = new TracksInPlaylistResponse();
        response.setTracks(trackMapper.getTracksByPlaylistId(playlistId));
        return response;
    }
}
