package com.example.theweb.service.impl;

import com.example.theweb.dao.pictureDao;
import com.example.theweb.service.interfaceClass.IpictureList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class pictureListImpl implements IpictureList {

    @Autowired
    private IuserList iuserList;
    private SqlSession session;

    @PostConstruct
    public void init(){
        this.session=iuserList.getSession();
    }




    @Override
    public void inserPicture(pictureDao picture) {
        try{
            session.insert("com.example.theweb.mapper.pictureListmapper.insertPicture",picture);
        }catch(SqlSessionException e){System.out.println("sql exception: "+e.getMessage());}
    }

    @Override
    public List<pictureDao> selectListByUserName(String userName) {
        return session.selectList("com.example.theweb.mapper.pictureListmapper.selectListByUserName",userName);
    }

    @Override
    public void deletePicture(pictureDao picture) {
        session.delete("com.example.theweb.mapper.pictureListmapper.deletePicture",picture);
    }

    @Override
    public List<pictureDao> selectList() {
        return session.selectList("com.example.theweb.mapper.pictureListmapper.selectList");
    }
}
