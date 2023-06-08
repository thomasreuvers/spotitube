package org.spotitube.Data.Entity;


public class Playlist extends BaseEntity {
    private String Name;
    private int UserId;

    public Playlist(){
    }

    public Playlist(int id, String name, int userId) {
        Id = id;
        Name = name;
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }
}
