package com.example.theweb.dao;

public class userAndRoleDao {
    private int id;
    private String username;
    private String password;
    private int age;
    private String address;
    private String roleName;

    public userAndRoleDao(int id,String username,String password, int age, String address, String roleName){
        this.id=id;
        this.address=address;
        this.age=age;
        this.roleName=roleName;
        this.username=username;
        this.password=password;
    }
    public userAndRoleDao(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void addRole(String role){
        if(roleName==null){
            this.roleName=role;
            return;
        }
        this.roleName+=role;
    }

}
