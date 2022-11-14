package com.example.theweb.dao;

public class roleDao {


    private String userName;
    private String roleName;

    public roleDao(String userName, String roleName){
        this.roleName=roleName;
        this.userName=userName;
    }

    public roleDao(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
