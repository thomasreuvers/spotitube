package org.spotitube.Data.Mapper.Track;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.IBaseMapper;

import java.util.List;
import java.util.Optional;

public interface ITrackDAO<T> extends IBaseMapper<T> {
    List<T> getAllTracksByPlaylistId(int playlistId);
    List<Track> getAllAvailableTracksByPlaylistId(int playlistId);

    Optional<T> getTrack(int trackId);
}
