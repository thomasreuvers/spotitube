package org.spotitube.Data.Entity;

import java.util.Objects;

public class User extends BaseEntity {
    private String Username;
    private String Password;
    private String Token;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getToken(), user.getToken());
    }
}
