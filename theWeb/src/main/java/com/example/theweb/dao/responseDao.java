package com.example.theweb.dao;

public class responseDao {
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public responseDao(String response){
        this.response=response;
    }
}
