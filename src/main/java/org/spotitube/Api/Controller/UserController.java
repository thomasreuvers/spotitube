package org.spotitube.Api.Controller;

import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.User.IUserService;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

public class UserController extends BaseController {

    @Inject
    private IUserService IUserService;

    @POST
    @Path("/login")
    public Response login(LoginRequest model) {
        LoginResponse user = IUserService.loginUser(model);
        return Response.ok(user).build();
    }

    @POST
    @Path("/register")
    public Response register(RegisterModel model) {
        IUserService.registerUser(model);
        return Response.ok().build();
    }

}
