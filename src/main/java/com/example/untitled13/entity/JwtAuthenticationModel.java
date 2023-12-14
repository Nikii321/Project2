package com.example.untitled13.entity;

public class JwtAuthenticationModel {
    private String username;
    private String password;

    public JwtAuthenticationModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public JwtAuthenticationModel(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
