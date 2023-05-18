package org.spotitube.Data.Entity;

import java.util.List;

public class Playlist extends BaseEntity {
    private String Name;
    private Boolean Owner;

    private List<Track> Tracks;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getOwner() {
        return Owner;
    }

    public void setOwner(Boolean owner) {
        Owner = owner;
    }

    public List<Track> getTracks() {
        return Tracks;
    }

    public void setTracks(List<Track> tracks) {
        Tracks = tracks;
    }
}
