package org.spotitube.Data.Entity;

public class User extends BaseEntity {
    private String Username;
    private String Password;
    private String Token;

    public User(String username, String password, String token) {
        this.Username = username;
        this.Password = password;
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

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
