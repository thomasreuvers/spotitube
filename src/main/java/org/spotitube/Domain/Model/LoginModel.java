package org.spotitube.Domain.Model;

public class LoginModel {
    private String Username;
    private String Password;

    public LoginModel(String username, String password) {
    }

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
}
