package com.projectwishlist.models;

public class User
{
    private int userId;
    private String username;
    private String password;
    private String firstName;

    public User(int userId, String username, String password, String firstName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
