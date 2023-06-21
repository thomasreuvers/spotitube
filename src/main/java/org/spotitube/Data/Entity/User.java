package org.spotitube.Data.Entity;

public class User extends BaseEntity {
    private String Username;
    private String Password;
    private String Token;

    private String Role;

    public User(){
    }

    public User(String username, String password) {
        this.Username = username;
        this.Password = password;
    }

    public User(String username, String password, String token) {
        this.Username = username;
        this.Password = password;
        this.Token = token;
    }

    public User(int id, String username, String password, String token) {
        this.Id = id;
        this.Username = username;
        this.Password = password;
        this.Token = token;
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

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
