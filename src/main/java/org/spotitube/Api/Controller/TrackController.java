package org.spotitube.Api.Controller;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Domain.Model.TracksResponse;
import org.spotitube.Domain.Service.Track.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController extends BaseController {
    @Inject
    private ITrackService trackService;

    @GET
    @RequireToken
    public Response getAvailableTracks(@QueryParam("forPlaylist") int forPlaylist, @QueryParam("token") String token) {
        TracksResponse response = trackService.allAvailableTracks(forPlaylist);
        return Response.ok(response).build();
    }
}
