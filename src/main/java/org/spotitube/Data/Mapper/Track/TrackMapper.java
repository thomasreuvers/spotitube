package org.spotitube.Data.Mapper.Track;

import oracle.ucp.proxy.annotation.Pre;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.BaseMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrackMapper extends BaseMapper implements ITrackDAO<Track> {

    public TrackMapper(){
        super();
    }

    @Override
    public List<Track> getTracksByPlaylistId(int playlistId) {
        List<Track> tracks = new ArrayList<>();

        String query = "SELECT tracks.id, tracks.title, tracks.performer,\n" +
                "        tracks.duration, tracks.album, tracks.playcount,\n" +
                "        tracks.publicationDate, tracks.description, tracks.offlineAvailable\n" +
                "FROM tracks \n" +
                "JOIN playlistTracks ON tracks.id = playlistTracks.track \n" +
                "WHERE playlistTracks.playlist = ?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){
            stmt.setInt(1, playlistId);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                Track track = resultSetToTrack(resultSet);

                // Add track to the Track-list
                tracks.add(track);
            }

        }catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as per your needs
        }

        return tracks;
    }

    @Override
    public Optional<Track> getTrack(int trackId) {
        String query = "SELECT * FROM tracks WHERE trackId=?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){
            stmt.setInt(1, trackId);

            ResultSet resultSet = stmt.executeQuery();

            // Check if resultset is empty
            if (!resultSet.isBeforeFirst() && resultSet.getRow() == 0){
                return Optional.empty();
            }

            Track track = resultSetToTrack(resultSet);
            return Optional.of(track);

        }catch(SQLException ex){
            ex.printStackTrace();
        }

        return Optional.empty();
    }

    private Track resultSetToTrack(ResultSet resultSet) throws SQLException {
        Track track = new Track();

        // Set track properties
        track.setId(resultSet.getInt("id"));
        track.setTitle(resultSet.getString("title"));
        track.setPerformer(resultSet.getString("performer"));
        track.setDuration(resultSet.getInt("duration"));
        track.setAlbum(resultSet.getString("album"));
        track.setPlayCount(resultSet.getInt("playcount"));
        track.setPublicationDate(LocalDate.parse(resultSet.getDate("publicationDate").toString()));
        track.setDescription(resultSet.getString("description"));
        track.setOfflineAvailable(resultSet.getBoolean("offlineAvailable"));

        return track;
    }

    @Override
    public Optional<Track> find(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(Track track) {

    }

    @Override
    public void update(Track track) {

    }

    @Override
    public void delete(int id) {

    }
}
