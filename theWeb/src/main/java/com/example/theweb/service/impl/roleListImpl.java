package com.example.theweb.service.impl;

import com.example.theweb.dao.roleDao;
import com.example.theweb.service.interfaceClass.IroleList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class roleListImpl implements IroleList {

    @Autowired
    private IuserList iuserList;
    private SqlSession session;

    @PostConstruct
    public void init(){
        this.session=iuserList.getSession();

    }

    @Override
    public List<roleDao> selectListByUserName(String userName) {
        return session.selectList("com.example.theweb.mapper.roleListmapper.selectListByUserName",userName);
    }

    @Override
    public String insertRole(roleDao role) {
        try{
            session.insert("com.example.theweb.mapper.roleListmapper.insertRole",role);
        }catch(SqlSessionException e){
            return e.getMessage();
        }
        return "success";
    }

    @Override
    public List<roleDao> selectListAll() {
        return session.selectList("com.example.theweb.mapper.roleListmapper.selectListAll");
    }

    @Override
    public void deleteRole(roleDao role) {
        session.delete("com.example.theweb.mapper.roleListmapper.deleteRole",role);
    }
}
