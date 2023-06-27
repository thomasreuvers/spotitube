package org.spotitube.Api.ExceptionMapper;

import org.spotitube.Domain.Exception.CustomException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<CustomException> {
    @Override
    public Response toResponse(CustomException exception) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage())
                .build();
    }
}
