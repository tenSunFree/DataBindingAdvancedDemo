package com.example.administrator.databindingadvanceddemo.bean;

public class Content {

    private String name;
    private String menu;
    private String imageUrl;
    private String distance;

    public Content(String name, String menu, String imageUrl, String distance) {
        this.name = name;
        this.menu = menu;
        this.imageUrl = imageUrl;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
