package com.example.myapplication;

public class Item {
    private String des;
    private String imageUrl;

    public Item(String des, String imageUrl) {
        this.des = des;
        this.imageUrl = imageUrl;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des){
        this.des = des;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
    public String getImageUrl() {
        return imageUrl;
    }
}