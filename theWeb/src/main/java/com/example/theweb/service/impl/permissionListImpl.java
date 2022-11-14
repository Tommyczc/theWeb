package com.example.theweb.service.impl;

import com.example.theweb.dao.permissionDao;
import com.example.theweb.service.interfaceClass.IpermissionList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;



@Service
public class permissionListImpl implements IpermissionList {

    @Autowired
    private IuserList iuserList;
    private SqlSession session;

//    public permissionListImpl(){
//        session=iuserList.getSession();
//
//    }

    @PostConstruct
    public void init(){
        this.session=iuserList.getSession();

    }


    @Override
    public List<permissionDao> selectListByRoleName(String roleName) {
        return session.selectList("com.example.theweb.mapper.permissionListmapper.selectListByRoleName",roleName);
    }
}
