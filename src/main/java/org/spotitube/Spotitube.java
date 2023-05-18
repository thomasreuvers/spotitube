package org.spotitube;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.spotitube.Api.Resource.PlaylistResource;
import org.spotitube.Api.Resource.UserResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Spotitube extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(JacksonJsonProvider.class);
        classes.add(UserResource.class);
        classes.add(PlaylistResource.class);
        return classes;
    }
}
