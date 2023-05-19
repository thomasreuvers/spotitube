package org.spotitube.Api.Resource;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Model.AllPlaylistResponse;
import org.spotitube.Domain.Service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlist")
public class PlaylistResource extends BaseResource {

    @Inject
    private PlaylistService playlistService;

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
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Consumes(MediaType.APPLICATION_JSON)
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
}
