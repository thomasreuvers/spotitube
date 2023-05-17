package org.spotitube.Api.Resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("playlist")
public class PlaylistResource {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GetPlaylist() {
        return null;
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
