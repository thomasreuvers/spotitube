package org.spotitube.Api.Resource;

import org.spotitube.Data.Entity.Playlist;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Service.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("playlist")
public class PlaylistResource extends BaseResource {

    @Inject
    private PlaylistService playlistService;

    @GET
    public Response GetPlaylists() {
        try {
            List<Playlist> playlists = playlistService.getAllPlaylists();
            return Response.ok(playlists).build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddNewPlaylist() {
        return null;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response EditPlaylist() {
        return null;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response DeletePlaylist() {
        return null;
    }
}
