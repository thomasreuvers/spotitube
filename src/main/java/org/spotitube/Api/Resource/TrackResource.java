package org.spotitube.Api.Resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("track")
public class TrackResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response AddTrackToPlaylist() {
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response DeleteTrackFromPlaylist() {
        return null;
    }
}
