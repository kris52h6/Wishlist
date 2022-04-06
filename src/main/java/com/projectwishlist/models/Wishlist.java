package com.projectwishlist.models;

public class Wishlist {
    private int id;
    private String name;
    private String link;
    private int userId;

    public Wishlist(int id, String name, String link, int userId) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public int getUserId() {
        return userId;
    }

}
