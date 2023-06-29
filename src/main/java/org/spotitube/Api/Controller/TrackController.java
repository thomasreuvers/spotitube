package org.spotitube.Api.Controller;

import org.spotitube.Domain.Model.TracksResponse;
import org.spotitube.Domain.Service.Track.ITrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController extends BaseController {
    @Inject
    private ITrackService trackService;

    @GET
    public Response getAvailableTracks(@QueryParam("forPlaylist") int forPlaylist) {
        TracksResponse response = trackService.allAvailableTracks(forPlaylist);
        return Response.ok(response).build();
    }
}
