package org.spotitube.Data.Mapper.Track;

import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.BaseMapper;

import java.util.Arrays;
import java.util.List;

public class TrackMapper extends BaseMapper<Track> implements ITrackMapper {

    public TrackMapper(){
        super();
    }

    @Override
    public List<Track> allTracksInPlaylist(int playlistId) {
        String query = "SELECT tracks.id, tracks.title, tracks.performer,\n" +
                "        tracks.duration, tracks.album, tracks.playcount,\n" +
                "        tracks.publicationDate, tracks.description, tracks.offlineAvailable\n" +
                "FROM tracks \n" +
                "JOIN playlistTracks ON tracks.id = playlistTracks.trackId \n" +
                "WHERE playlistTracks.playlistId = ?";
        List<Track> tracksInPlaylist = all(query, List.of(playlistId));
        return tracksInPlaylist;
    }

    @Override
    public List<Track> allAvailableTracks(int playlistId) {
        String query = "SELECT t.*\n" +
                "FROM tracks t\n" +
                "LEFT JOIN playlistTracks pt ON t.id = pt.trackId AND pt.playlistId = ?\n" +
                "WHERE pt.playlistId IS NULL;";
        return all(query, Arrays.asList(playlistId));
    }

    @Override
    public void deleteFromPlaylist(int playlistId, int id) {
        String query = "DELETE FROM playlistTracks WHERE trackId=? AND playlistId=?";
        save(query, Arrays.asList(id, playlistId));
    }

    @Override
    public void addToPlaylist(int id, int playlistId, boolean offlineAvailable) {
        String query = "INSERT INTO playlistTracks (trackId, playlistId) VALUES (?,?)";
        save(query, Arrays.asList(id, playlistId));

        // Update offline availability
        String query2 = "UPDATE tracks SET offlineAvailable=? WHERE id=?";
        save(query2, Arrays.asList(offlineAvailable, id));
    }
}
