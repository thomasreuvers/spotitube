package org.spotitube.Api.Resource;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Model.AllPlaylistResponse;
import org.spotitube.Domain.Model.TracksResponse;
import org.spotitube.Domain.Service.PlaylistService;
import org.spotitube.Domain.Service.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("playlist")
public class PlaylistResource extends BaseResource {

    @Inject
    private PlaylistService playlistService;

    @Inject
    private TrackService trackService;

    @GET
    @RequireToken
    public Response GetPlaylists(@QueryParam("token") String token) {
        try {
            AllPlaylistResponse response = playlistService.getAllPlaylists();
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @POST
    public Response AddNewPlaylist(Playlist playlist) {
        try {
            playlistService.addPlaylist(playlist);
            AllPlaylistResponse response = playlistService.getAllPlaylists();
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @PUT
    public Response EditPlaylist(Playlist playlist) {
        try {
            playlistService.updatePlaylist(playlist);
            AllPlaylistResponse response = playlistService.getAllPlaylists();
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    //Spotitube/playlist?id={id}
    @DELETE
    public Response DeletePlaylist(@QueryParam("id") int id) {
        try{
            playlistService.deletePlaylist(id);
            AllPlaylistResponse response = playlistService.getAllPlaylists();
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @GET
    @Path("{id}/tracks")
    public Response getPlaylistTracks(@PathParam("id") int id) {
        try{
            TracksResponse response = trackService.getTracksByPlaylistId(id);
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/tracks")
    public Response getAvailableTracks(@QueryParam("forPlaylist") int forPlaylist) {
        try{
            TracksResponse response = trackService.getAllAvailableTracksByPlaylistId(forPlaylist);
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
