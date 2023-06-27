package org.spotitube.Api.ExceptionMapper;

import org.spotitube.Domain.Exception.AuthenticationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException> {

    @Override
    public Response toResponse(AuthenticationException exception) {
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .entity(exception.getMessage())
                .build();
    }
}
