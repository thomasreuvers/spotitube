package org.spotitube.Domain.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private String user;
    private String password;

    public LoginRequest(){

    }

    @JsonCreator
    public LoginRequest(@JsonProperty("user") String user, @JsonProperty("password") String password) {
        this.user = user;
        this.password = password;
    }

    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    @JsonProperty("user")
    public void setUser(String user) {
        this.user = user;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }
}
