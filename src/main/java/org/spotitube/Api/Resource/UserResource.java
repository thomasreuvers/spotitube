package org.spotitube.Api.Resource;

import org.spotitube.Data.Entity.User;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginModel;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("user")
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response login(LoginModel model) {
        try {
            User user = userService.loginUser(model);
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
    @Consumes(MediaType.APPLICATION_JSON)
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
