package org.spotitube.Api.Filter;

import io.jsonwebtoken.Claims;
import org.spotitube.Domain.Service.Token.ITokenService;

import javax.annotation.Priority;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class JWTAuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Inject
    private ITokenService tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext){
        Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod.isAnnotationPresent(RolesAllowed.class)) {
            // Get the Authorization header from the request
            String authorizationHeader = requestContext.getHeaderString("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                return;
            }

            // Extract the token from the Authorization header
            String token = authorizationHeader.substring("Bearer ".length()).trim();

            // Extract claims and validate token
            Claims claims = tokenService.ValidateAndParseToken(token);

            // Check if the user roles match the required roles specified by @RolesAllowed
            boolean isAuthorized = isAuthorized(claims.get("role", String.class));

            if (!isAuthorized) {
                // User does not have the required roles, return HTTP 403 Forbidden
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }

    private boolean isAuthorized(String role) {
        // Extract the required roles from the @RolesAllowed annotation of the resource method
        Method resourceMethod = resourceInfo.getResourceMethod();
        RolesAllowed rolesAllowed = resourceMethod.getAnnotation(RolesAllowed.class);
        List<String> requiredRoles = Arrays.asList(rolesAllowed.value());

        // Check if the user has at least one of the required roles
        return requiredRoles.contains(role);
    }
}
