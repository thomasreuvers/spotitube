package org.spotitube.Api.Controller;

import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.User.IUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class UserController extends BaseController {

    @Inject
    private IUserService IUserService;

    @POST
    @Path("/login")
    public Response login(LoginRequest model) {
        try {
            LoginResponse user = IUserService.loginUser(model);
            return Response.ok(user).build();
        }catch(AuthenticationException ex){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage())
                    .build();
        }catch(Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(ex.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/register")
    public Response register(RegisterModel model) {
        try {
            IUserService.registerUser(model);
        }catch(Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage()
            ).build();
        }
        return Response.ok().build();
    }

}
