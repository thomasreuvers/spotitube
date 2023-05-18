package org.spotitube.Data.Mapper.Track;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.IBaseMapper;

import java.util.List;

public interface ITrackDAO<T> extends IBaseMapper<T> {
    List<Track> getTracksByPlaylistId(int playlistId);
}
