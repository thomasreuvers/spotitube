package org.spotitube.Api.Resource;

import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource extends BaseResource {

    @Inject
    private UserService userService;

    @POST
    @Path("/login")
    public Response login(LoginRequest model) {
        try {
            LoginResponse user = userService.loginUser(model);
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
            userService.registerUser(model);
        }catch(Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), ex.getMessage()
            ).build();
        }
        return Response.ok().build();
    }

}
