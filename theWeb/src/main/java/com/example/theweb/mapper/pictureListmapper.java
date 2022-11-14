package com.example.theweb.mapper;
import com.example.theweb.dao.pictureDao;

import java.util.List;

public interface pictureListmapper {
    public void inserPicture(pictureDao picture);
    public List<pictureDao> selectListByUserName(String userName);
    public void deletePicture(pictureDao picture);
}
