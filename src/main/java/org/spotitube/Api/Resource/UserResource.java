package org.spotitube.Api.Resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.spotitube.Domain.Model.LoginModel;
import org.spotitube.Domain.Service.IUserService;
import javax.inject.Inject;

@Path("user")
public class UserResource {

    @Inject
    private IUserService _userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public void login(LoginModel model) {
    }

    @GET
    @Path("/calculate")
    public int calculate() {
        return _userService.calculate(5, 2);
    }

    @GET
    @Path("/ping")
    public String ping() {
        return "Pong!";
    }
}
