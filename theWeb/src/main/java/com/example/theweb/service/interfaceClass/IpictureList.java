package com.example.theweb.service.interfaceClass;

import com.example.theweb.dao.pictureDao;

import java.util.List;

public interface IpictureList {
    public void inserPicture(pictureDao picture);
    public List<pictureDao> selectListByUserName(String userName);
    public void deletePicture(pictureDao picture);
    public List<pictureDao> selectList();
}
