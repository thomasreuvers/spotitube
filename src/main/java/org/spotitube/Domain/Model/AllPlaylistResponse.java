package org.spotitube.Domain.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.spotitube.Data.Entity.Playlist;

import java.util.List;

public class AllPlaylistResponse {
    @JsonProperty("playlists")
    private List<Playlist> playlists;

    @JsonProperty("length")
    private int length;

    @JsonCreator
    public AllPlaylistResponse(@JsonProperty("playlists") List<Playlist> playlists, @JsonProperty("length") int length) {
        this.playlists = playlists;
        this.length = length;
    }

    @JsonProperty("playlists")
    public List<Playlist> getPlaylists() {
        return playlists;
    }

    @JsonProperty("playlists")
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @JsonProperty("length")
    public int getLength() {
        return length;
    }

    @JsonProperty("length")
    public void setLength(int length) {
        this.length = length;
    }
}
