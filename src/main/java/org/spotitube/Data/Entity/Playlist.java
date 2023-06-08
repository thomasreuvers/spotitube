package org.spotitube.Data.Entity;


public class Playlist extends BaseEntity {
    private String Name;
    private int UserId;

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
