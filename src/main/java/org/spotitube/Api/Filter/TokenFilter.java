package org.spotitube.Api.Filter;

import org.spotitube.Api.Annotation.RequireToken;
import org.spotitube.Domain.Service.Token.ITokenService;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
@Priority(1)
public class TokenFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private ITokenService ITokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod.isAnnotationPresent(RequireToken.class)) {
            String token = requestContext.getUriInfo().getQueryParameters().getFirst("token");
            if (!isValidToken(token)) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid token")
                        .build());
            }
        }
    }

    private boolean isValidToken(String token) {
        if (token == null) return false;
        return ITokenService.validateToken(token);
    }
}
