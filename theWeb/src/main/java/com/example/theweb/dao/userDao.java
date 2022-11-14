package com.example.theweb.dao;

public class userDao {



    private int id;
    private String username;
    private String password;
    private int age;
    private String address;


    public userDao(String username, String password, int age, String address){
        this.username=username;
        this.password=password;
        this.age=age;
        this.address=address;
    }

    public userDao(String username, String password) {
        this(username,password,0,null);
    }
    public userDao(){}



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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
