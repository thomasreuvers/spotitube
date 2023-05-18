package org.spotitube.Data.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Track extends BaseEntity {
    private String Title;
    private String Performer;
    private int Duration;

    private String Album;
    private int PlayCount;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date PublicationDate;
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

    public int getPlayCount() {
        return PlayCount;
    }

    public void setPlayCount(int playCount) {
        PlayCount = playCount;
    }

    public Date getPublicationDate() {
        return PublicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
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
