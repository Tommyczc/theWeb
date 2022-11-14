package com.example.theweb.service.interfaceClass;

import com.example.theweb.dao.roleDao;
import com.example.theweb.dao.userDao;

import java.util.List;

public interface IroleList {
    public List<roleDao> selectListByUserName(String userName);
    public String insertRole(roleDao role);
    public List<roleDao> selectListAll();
    public void deleteRole(roleDao role);
}
