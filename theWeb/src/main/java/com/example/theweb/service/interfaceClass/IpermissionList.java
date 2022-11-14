package com.example.theweb.service.interfaceClass;

import com.example.theweb.dao.permissionDao;

import java.util.List;

public interface IpermissionList {
    public List<permissionDao> selectListByRoleName(String roleName);
}
