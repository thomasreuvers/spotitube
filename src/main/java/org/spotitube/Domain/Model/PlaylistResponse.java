package org.spotitube.Domain.Model;

import java.util.List;

public class PlaylistResponse {

    private List<PlaylistViewModel> playlists;
    private int length;

    public PlaylistResponse(List<PlaylistViewModel> playlists, int length) {
        this.playlists = playlists;
        this.length = length;
    }

    public List<PlaylistViewModel> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistViewModel> playlists) {
        this.playlists = playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
