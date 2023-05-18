package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Mapper.Playlist.IPlaylistDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class PlaylistServiceImpl implements PlaylistService{

    @Inject
    private IPlaylistDAO<Playlist> playlistMapper;

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistMapper.AllPlaylists();
    }
}
