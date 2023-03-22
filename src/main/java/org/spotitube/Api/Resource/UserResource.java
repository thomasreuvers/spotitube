package org.spotitube.Api.Resource;

import org.spotitube.Domain.Model.LoginModel;
import org.spotitube.Domain.Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;

@Path("user")
public class UserResource extends BaseResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    public void login(LoginModel model) {
    }

    @GET
    @Path("/calculate")
    public int calculate() {
        return userService.calculate(5, 2);
    }

    @GET
    @Path("/ping")
    public String ping() {
        userService.registerUser();
        return "registered!";
    }
}
