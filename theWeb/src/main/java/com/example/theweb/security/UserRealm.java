package com.example.theweb.security;

import com.example.theweb.dao.permissionDao;
import com.example.theweb.dao.roleDao;
import com.example.theweb.dao.userDao;
import com.example.theweb.service.interfaceClass.IpermissionList;
import com.example.theweb.service.interfaceClass.IroleList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class UserRealm extends AuthorizingRealm {
    @Autowired
    private IuserList iuserList;
    @Autowired
    private IroleList iroleList;
    @Autowired
    private IpermissionList ipermissionList;

    //前面被authc拦截后，需要认证，SecurityBean会调用这里进行认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        //登录验证
        //很尴尬的发现token里面的密码是char[]格式，toString函数只能返回数列的哈希值，并不能直接转化成String
        userDao user = new userDao(token.getUsername(), String.copyValueOf(token.getPassword()));
        boolean login=iuserList.loginByUserNameAndPassword(user);

        System.out.println(token.getUsername());
        //System.out.println(String.copyValueOf(token.getPassword()));
        System.out.println("login state:   "+login);

        if (!login) {
            System.out.println("not login");
            return null;
        } else {
            //System.out.println("login");
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), "com.example.theweb.security.UserRealm");
        }
    }




    // 10. 前面被roles拦截后，需要授权才能登录，SecurityManager需要调用这里进行权限查询
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username=(String)principalCollection.getPrimaryPrincipal();
        Set<String> roles=getRoleByUserName(username);
        Set<String> permissions=new HashSet<>();


        for(String role:roles){
            Set<String> permission=getPermissionByRoleName(role);
            for(String per:permission)
            permissions.add(per);
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permissions);
        info.setRoles(roles);

        System.out.println("roles: "+roles);
        System.out.println("permissions: "+permissions);

        return info;
    }


    //get the roles by user name
    private Set<String> getRoleByUserName(String username){
        Set<String> roles=new HashSet<>();
        //查询身份
        List<roleDao> rolelist=iroleList.selectListByUserName(username);
        for(roleDao role:rolelist){
            roles.add(role.getRoleName());
        }
        //System.out.println(roles);
        return roles;
    }

    //get the permission by role name
    private Set<String> getPermissionByRoleName(String rolename){
        Set<String> permissions=new HashSet<>();
        //查询身份
        List<permissionDao> permissionlist=ipermissionList.selectListByRoleName(rolename);
        for(permissionDao permission:permissionlist){
            permissions.add(permission.getPermission());
        }
        //System.out.println(permissions);
        return permissions;
    }
}
