package com.dc.utilisocial.api;


public class UserTransfer {
    private String token;
    private String email;
    private String bitMask;
    private String title;
    private String id;
    private String username;
    private String role;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBitMask() {
        return bitMask;
    }

    public void setBitMask(String bitMask) {
        this.bitMask = bitMask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "token='" + token + '\'' +
                ", email='" + email + '\'' +
                ", bitMask='" + bitMask + '\'' +
                ", title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
