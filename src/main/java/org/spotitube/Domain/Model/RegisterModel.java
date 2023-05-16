package org.spotitube.Domain.Model;

import org.spotitube.Data.Entity.User;

public class RegisterModel {
    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public User asUserEntity(RegisterModel model){
        return new User(model.getUsername(), model.getPassword(), null);
    }
}
