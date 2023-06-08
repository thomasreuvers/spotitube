package org.spotitube;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.spotitube.Api.Resource.PlaylistController;
import org.spotitube.Api.Resource.TrackController;
import org.spotitube.Api.Resource.UserController;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class Spotitube extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(JacksonJsonProvider.class);
        classes.add(UserController.class);
        classes.add(PlaylistController.class);
        classes.add(TrackController.class);
        return classes;
    }
}
