package org.spotitube.Api.Controller;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Domain.Model.PlaylistResponse;
import org.spotitube.Domain.Model.TracksResponse;
import org.spotitube.Domain.Service.Playlist.IPlaylistService;
import org.spotitube.Domain.Service.Track.ITrackService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("playlists")
public class PlaylistController extends BaseController {

    @Inject
    private IPlaylistService playlistService;

    @Inject
    private ITrackService trackService;

    @GET
    @RolesAllowed("admin")
    public Response GetPlaylists(int userId) {
        PlaylistResponse response = playlistService.allPlaylists(userId);
        return Response.ok(response).build();
    }

    @POST
    public Response AddNewPlaylist(int userId, Playlist playlist) {
        playlistService.addPlaylist(playlist, userId);
        PlaylistResponse response = playlistService.allPlaylists(userId);
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    public Response EditPlaylist(@PathParam("id") int id, Playlist playlist, int userId) {
        playlistService.updatePlaylist(id, playlist);
        PlaylistResponse response = playlistService.allPlaylists(userId);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("{id}")
    public Response DeletePlaylist(@PathParam("id") int id, int userId) {
        playlistService.deletePlaylist(id);
        PlaylistResponse response = playlistService.allPlaylists(userId);
        return Response.ok(response).build();
    }

    @GET
    @Path("{id}/tracks")
    public Response getPlaylistTracks(@PathParam("id") int id) {
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }

    @POST
    @Path("{id}/tracks")
    public Response addTrackToPlaylist(@PathParam("id") int id, Track track) {
        trackService.addToPlaylist(track, id);
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("{id}/tracks/{trackId}")
    public Response deleteTrackFromPlaylist(@PathParam("id") int id, @PathParam("trackId")int trackId) {
        trackService.deleteFromPlaylist(id, trackId);
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }
}
