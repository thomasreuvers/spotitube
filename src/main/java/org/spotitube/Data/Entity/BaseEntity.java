package org.spotitube.Data.Entity;

public abstract class BaseEntity {
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }
}
