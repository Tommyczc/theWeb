package com.example.theweb.dao;

public class pictureDao {
    private String userName;
    private String pictureUrl;
    private String description;

    public pictureDao(){}

    public pictureDao(String userName,String pictureUrl,String description){
        this.userName=userName;
        this.pictureUrl=pictureUrl;
        this.description=description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
