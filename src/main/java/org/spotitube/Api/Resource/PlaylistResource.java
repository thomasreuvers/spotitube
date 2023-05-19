package org.spotitube.Api.Resource;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Data.Entity.Track;
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
    @RequireToken
    public Response AddNewPlaylist(@QueryParam("token") String token, Playlist playlist) {
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
    @RequireToken
    public Response EditPlaylist(@QueryParam("token") String token, Playlist playlist) {
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
    @RequireToken
    public Response DeletePlaylist(@QueryParam("id") int id, @QueryParam("token") String token) {
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
    @RequireToken
    public Response getPlaylistTracks(@PathParam("id") int id, @QueryParam("token") String token) {
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
    @RequireToken
    public Response getAvailableTracks(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        try{
            TracksResponse response = trackService.getAllAvailableTracksByPlaylistId(forPlaylist);
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }


    @POST
    @Path("{id}/tracks")
    @RequireToken
    public Response addTrackToPlaylist(@PathParam("id") int id, Track track, @QueryParam("token") String token) {
        try{
            trackService.addTrackToPlaylist(track, id);
            TracksResponse response = trackService.getTracksByPlaylistId(id);
            return Response.ok(response).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }
}
