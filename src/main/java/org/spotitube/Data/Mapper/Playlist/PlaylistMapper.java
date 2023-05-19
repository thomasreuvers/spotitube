package org.spotitube.Data.Mapper.Playlist;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Data.Mapper.BaseMapper;
import org.spotitube.Data.Mapper.Track.ITrackDAO;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaylistMapper extends BaseMapper implements IPlaylistDAO<Playlist>{

    @Inject
    private ITrackDAO<Track> trackMapper;

    public PlaylistMapper() {
        super();
    }

    @Override
    public List<Playlist> AllPlaylists() {
       List<Playlist> playlists = new ArrayList<>();

       String query = "SELECT * FROM playlists";

       try(
               Connection conn = getConnection();
               Statement stmt = conn.createStatement()
               ){
           ResultSet resultSet = stmt.executeQuery(query);

           while (resultSet.next()) {
               Playlist playlist = new Playlist();

               // Set the properties of the playlist
               playlist.setId(resultSet.getInt("id"));
               playlist.setName(resultSet.getString("name"));
               playlist.setOwner(resultSet.getBoolean("owner"));

               // Fetch tracks associated with the playlist
               List<Track> tracks = trackMapper.getAllTracksByPlaylistId(playlist.getId());
               playlist.setTracks(tracks);

               // Add the playlist to the list
               playlists.add(playlist);
           }

       } catch (SQLException e) {
           e.printStackTrace(); // Handle or log the exception as per your needs
       }

       return playlists;
    }

    @Override
    public Optional<Playlist> find(int id) {
        return Optional.empty();
    }

    @Override
    public void insert(Playlist playlist) {
        String query = "INSERT INTO playlists(name, owner) VALUES(?,?)";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){
            stmt.setString(1, playlist.getName());
            stmt.setBoolean(2, playlist.getOwner());

            stmt.executeUpdate();

        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Playlist playlist) {
        String query = "UPDATE playlists SET name=?, owner=? WHERE id=?";

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                ){

            stmt.setString(1, playlist.getName());
            stmt.setBoolean(2, playlist.getOwner());
            stmt.setInt(3, playlist.getId());

            stmt.executeUpdate();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM playlists WHERE id = ?"; // TODO: REMOVE JOINTABLE?

        try(
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
                )
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
