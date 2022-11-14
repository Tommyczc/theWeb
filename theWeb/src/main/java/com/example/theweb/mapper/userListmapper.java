package com.example.theweb.mapper;


import com.example.theweb.dao.userDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface userListmapper {

    public List<userDao> loginByUserNmaeAndPassword(String userName, String Password);
    public List<userDao> selectListByUserName(String userName);
    public void insertUser(userDao user);
}
