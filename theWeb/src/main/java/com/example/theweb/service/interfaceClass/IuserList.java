package com.example.theweb.service.interfaceClass;

import com.example.theweb.dao.userDao;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface IuserList {

    public int generateSqlSession();
    public boolean loginByUserNameAndPassword(userDao user);
    public List<userDao> selectListByUserName(String userName);
    public SqlSession getSession();
    public void commitSession();
    public void closeSession();
    public String insertUser(userDao user);
    public List<userDao> selectListAll();
    public void deleteUser(int id);
    public void updateUser(userDao user);
}
