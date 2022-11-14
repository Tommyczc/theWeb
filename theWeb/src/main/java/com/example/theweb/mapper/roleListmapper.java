package com.example.theweb.mapper;

import com.example.theweb.dao.roleDao;
import com.example.theweb.dao.userDao;

import java.util.List;

public interface roleListmapper {
    public List<roleDao> lselectListByUserName(String userName);
    public void insertRole(roleDao role);
}
