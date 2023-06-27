package org.spotitube.Api.Controller;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
import org.spotitube.Domain.Model.PlaylistResponse;
import org.spotitube.Domain.Model.TracksResponse;
import org.spotitube.Domain.Service.Playlist.IPlaylistService;
import org.spotitube.Domain.Service.Track.ITrackService;

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
    @RequireToken
    public Response GetPlaylists(@QueryParam("token") String token) {
        PlaylistResponse response = playlistService.allPlaylists(token);
        return Response.ok(response).build();
    }

    @POST
    @RequireToken
    public Response AddNewPlaylist(@QueryParam("token") String token, Playlist playlist) {
        playlistService.addPlaylist(playlist, token);
        PlaylistResponse response = playlistService.allPlaylists(token);
        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @RequireToken
    public Response EditPlaylist(@PathParam("id") int id, @QueryParam("token") String token, Playlist playlist) {
        playlistService.updatePlaylist(id, playlist);
        PlaylistResponse response = playlistService.allPlaylists(token);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("{id}")
    @RequireToken
    public Response DeletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.deletePlaylist(id);
        PlaylistResponse response = playlistService.allPlaylists(token);
        return Response.ok(response).build();
    }

    @GET
    @Path("{id}/tracks")
    @RequireToken
    public Response getPlaylistTracks(@PathParam("id") int id, @QueryParam("token") String token) {
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }

    @POST
    @Path("{id}/tracks")
    @RequireToken
    public Response addTrackToPlaylist(@PathParam("id") int id, Track track, @QueryParam("token") String token) {
        trackService.addToPlaylist(track, id);
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("{id}/tracks/{trackId}")
    @RequireToken
    public Response deleteTrackFromPlaylist(@PathParam("id") int id, @PathParam("trackId")int trackId, @QueryParam("token") String token) {
        trackService.deleteFromPlaylist(id, trackId);
        TracksResponse response = trackService.allTracksInPlaylist(id);
        return Response.ok(response).build();
    }
}
