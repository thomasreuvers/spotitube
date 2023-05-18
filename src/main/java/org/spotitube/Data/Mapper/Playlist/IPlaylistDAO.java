package org.spotitube.Data.Mapper.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Mapper.IBaseMapper;

import java.util.List;

public interface IPlaylistDAO<T> extends IBaseMapper<T> {

    List<Playlist> AllPlaylists();
}
