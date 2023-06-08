package org.spotitube.Data.Entity;

import java.time.LocalDate;

public class Track extends BaseEntity {
    private String Title;
    private String Performer;
    private int Duration;

    private String Album;

    private int Playcount;

    private LocalDate PublicationDate;
    private String Description;

    private boolean OfflineAvailable;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getPerformer() {
        return Performer;
    }

    public void setPerformer(String performer) {
        Performer = performer;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public int getPlaycount() {
        return Playcount;
    }

    public void setPlaycount(int playcount) {
        Playcount = playcount;
    }

    public LocalDate getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        PublicationDate = publicationDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isOfflineAvailable() {
        return OfflineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        OfflineAvailable = offlineAvailable;
    }
}
