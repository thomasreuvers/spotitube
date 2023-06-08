package org.spotitube.Domain.Model;

import org.spotitube.Data.Entity.Track;

import java.util.List;

public class PlaylistViewModel {
    private int id;
    private String Name;
    private boolean Owner;
    private List<Track> Tracks;

    public PlaylistViewModel() {

    }
    public PlaylistViewModel(int id, String name, boolean owner, List<Track> tracks) {
        this.id = id;
        Name = name;
        Owner = owner;
        Tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isOwner() {
        return Owner;
    }

    public void setOwner(boolean owner) {
        Owner = owner;
    }

    public List<Track> getTracks() {
        return Tracks;
    }

    public void setTracks(List<Track> tracks) {
        Tracks = tracks;
    }
}
