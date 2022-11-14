package com.example.theweb.controller;

import com.example.theweb.dao.*;
import com.example.theweb.service.interfaceClass.IpermissionList;
import com.example.theweb.service.interfaceClass.IpictureList;
import com.example.theweb.service.interfaceClass.IroleList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiresRoles("admin")
public class adminController {

    @Autowired
    private IuserList iuserList;
    @Autowired
    private IpermissionList ipermissionList;
    @Autowired
    private IroleList iroleList;
    @Autowired
    private IpictureList ipictureList;


    @PostMapping("/userInformation")
    @ResponseBody
    public List<userAndRoleDao> getUserInfo(){
        List<userDao> userList=iuserList.selectListAll();
        List<roleDao> roleList=iroleList.selectListAll();
        List<userAndRoleDao> urList=new ArrayList<>();
        for(userDao user:userList){
            userAndRoleDao ur=new userAndRoleDao();

            ur.setUsername(user.getUsername());
            ur.setId(user.getId());
            ur.setAddress(user.getAddress());
            ur.setPassword(user.getPassword());
            ur.setAge(user.getAge());
            for(roleDao role:roleList){
                if(role.getUserName().equals(user.getUsername())){
                    ur.addRole(role.getRoleName()+"/");
                }
            }
            urList.add(ur);
        }
        return urList;
    }

    @PostMapping("/pictureUploadHistory")
    @ResponseBody
    public List<pictureDao> getUploadHistory(){
        return ipictureList.selectList();
    }

    @PostMapping("/deleteUserInformation")
    @ResponseBody
    public responseDao deleteUserLogic(@RequestParam(value="id",required = true) String id, @RequestParam(value="userName",required = false) String userName){
        System.out.println(id+" "+userName);
        try{
            iuserList.deleteUser(Integer.valueOf(id));
            String path= ResourceUtils.getURL("classpath:").getPath()+"static/image/"+ userName+"/";
            String s = path.replaceAll("%20", " ");
            path = URLDecoder.decode(s, "utf-8");
            File fileFolder=new File(path);
            if (fileFolder.isDirectory() && fileFolder.exists()){
                FileUtils.deleteDirectory(fileFolder);
            }
            iuserList.commitSession();
        }
        catch (SqlSessionException e){
            return new responseDao(e.getMessage());
        } catch (FileNotFoundException e) {
            return new responseDao(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            return new responseDao(e.getMessage());
        }catch(Exception e){
            return new responseDao(e.getMessage());
        }
        return new responseDao("来自后台: 删除成功");
    }

    @PostMapping("/updateUserInformation")
    @ResponseBody
    public responseDao updateUserLogic(@RequestParam(value="id",required = true) String id,
                                       @RequestParam(value="userName",required = true) String name,
                                       @RequestParam(value="password",required = true)String password,
                                       @RequestParam(value="age",required = false)String age,
                                       @RequestParam(value="address",required = false)String address,
                                       @RequestParam(value="UorA",required = false)String UorA){
        //System.out.println(id+name+password);
        //update user information
        try {
            userDao user = new userDao(name, password);
            if(age!=null & !age.equals("")){
                user.setAge(Integer.valueOf(age));
            }
            else if(address!=null && !address.equals("")) {
                user.setId(Integer.valueOf(id));
            }
            iuserList.updateUser(user);

            List<roleDao> roles=iroleList.selectListByUserName(name);

            if(roles.size()==0){return new responseDao("来自后台: 用户非法注册, 无法更新");}

            boolean isAdmin=false;
            for (roleDao role:roles){
                if(role.getRoleName().equals("admin")){
                    isAdmin=true;
                }
            }

            //如果本身是user，增加admin角色
            if(UorA.equals("admin")){
                if(!isAdmin){
                    roleDao role=new roleDao(name,"admin");
                    iroleList.insertRole(role);
                }
            }

            //如果本身就是admin，删掉admin角色
            else if(UorA.equals("user")){
                if(isAdmin){
                    roleDao role=new roleDao(name,"admin");
                    iroleList.deleteRole(role);
                }
            }



            iuserList.commitSession();
        }catch (SqlSessionException e){
            return new responseDao(e.getMessage());
        }


        //update user

        return new responseDao("来自后台: 用户更新成功");
    }

    @PostMapping("/insertUserInformation")
    @ResponseBody
    public responseDao insertUserLogic(@RequestParam(value="id",required = true) String id,
                                       @RequestParam(value="userName",required = true) String name,
                                       @RequestParam(value="password",required = true)String password,
                                       @RequestParam(value="age",required = false)String age,
                                       @RequestParam(value="address",required = false)String address,
                                       @RequestParam(value="UorA",required = false)String UorA){
        try {
            //检测用户是否存在
            List<userDao> userList = iuserList.selectListByUserName(name);
            if (userList.size() >= 1) {
                return new responseDao("来自后台: 用户已存在");
            }

            //开始插入客户数据
            userDao user = new userDao(name, password);
            if(age!=null & !age.equals("")){
                user.setAge(Integer.valueOf(age));
            }
            else if(address!=null && !address.equals("")) {
                user.setId(Integer.valueOf(id));
            }

            iuserList.insertUser(user);

            //开始插入客户角色
            roleDao userRole=new roleDao(name,"user");
            iroleList.insertRole(userRole);
            //检测是否有管理员权限
            if(UorA.equals("admin")){
                roleDao adminRole=new roleDao(name,"admin");
                iroleList.insertRole(adminRole);
            }



            iuserList.commitSession();
        }catch(SqlSessionException e){
            return new responseDao(e.getMessage());
        }



        return new responseDao("来自后台: 用户插入成功");
    }


}
