package org.spotitube.Domain.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResponse {
    private String token;
    private String user;

    public LoginResponse()
    {
    }

    @JsonCreator
    public LoginResponse(@JsonProperty("token") String token, @JsonProperty("user") String user) {
        this.token = token;
        this.user = user;
    }

    @JsonProperty("token")
    public String getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(String token) {
        this.token = token;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }
}
