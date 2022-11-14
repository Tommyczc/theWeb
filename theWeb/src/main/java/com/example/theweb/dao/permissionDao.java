package com.example.theweb.dao;

public class permissionDao {


    private String rolename;
    private String permission;

    public permissionDao(){}

    public permissionDao(String rolename, String permission){
        this.permission=permission;
        this.rolename=rolename;
    }


    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }



}
