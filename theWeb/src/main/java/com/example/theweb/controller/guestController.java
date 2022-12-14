package com.example.theweb.controller;


import com.example.theweb.dao.permissionDao;
import com.example.theweb.dao.pictureDao;
import com.example.theweb.dao.responseDao;
import com.example.theweb.dao.roleDao;
import com.example.theweb.service.interfaceClass.IpermissionList;
import com.example.theweb.service.interfaceClass.IpictureList;
import com.example.theweb.service.interfaceClass.IroleList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/guest")
@RequiresRoles("user")
public class guestController {

    @Autowired
    private IuserList iuserList;
    @Autowired
    private IpermissionList ipermissionList;
    @Autowired
    private IroleList iroleList;
    @Autowired
    private IpictureList ipictureList;


    @RequestMapping("/")
    public String toGuestIndex(){
        return "welcomePage";
    }

    @RequestMapping("/hello")
    public String toWelcomePage(Model model){
        String userName=SecurityUtils.getSubject().getPrincipals().toString();
        List<roleDao> roleList=iroleList.selectListByUserName(userName);
        List<permissionDao> permissionList=new ArrayList<permissionDao>();
        for(roleDao role:roleList) {
            List<permissionDao> list=ipermissionList.selectListByRoleName(role.getRoleName());
            for(permissionDao permission:list){
                permissionList.add(permission);
            }
        }
        model.addAttribute("username",userName);
        model.addAttribute("roleList",roleList);
        model.addAttribute("permissionList",permissionList);
        return "welcomePage";
    }

    @RequestMapping("/operation")
    @RequiresPermissions("admin:insert")
    public String doOperation(){
        return "redirect:/";
    }



    @PostMapping( "/userUploadLogic")
    @ResponseBody
    public String downLoadPicture(@RequestParam(value="txtDescription",required = false) String description, @RequestParam(value="files",required = false) MultipartFile[] files) {


        String path = null;
        try {

            //path = ResourceUtils.getURL("classpath:").getPath();
            path=ResourceUtils.getURL("classpath:").getPath()+"static/image/"+SecurityUtils.getSubject().getPrincipals().toString()+"/";
            String s = path.replaceAll("%20", " ");
            path = URLDecoder.decode(s, "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        System.out.println(path);

        File fileFolder=new File(path);
        //??????????????????????????????????????????
        if (!fileFolder.isDirectory() && !fileFolder.exists()) {
            fileFolder.mkdirs();
        }

        if(files!=null) {
            System.out.println("The number of the files: "+files.length);
            for(MultipartFile file:files) {
                System.out.println("File original name:  " + file.getOriginalFilename());

                //??????????????????
                long fileSize=file.getSize();

                if(!checkFileSize(fileSize,10,"M")){
                    return "????????????: ????????????: "+fileSize;
                }


                long timeMillis = System.currentTimeMillis();
                String fileUrl=String.valueOf(timeMillis)+file.getOriginalFilename();
                try {
                    System.out.println("final url: "+path+fileUrl);
                    file.transferTo(new File(path,fileUrl));
                }catch(IOException e){
                    System.out.println(e.getMessage());
                    return"????????????: ??????????????????";
                }

                //????????????????????????
                pictureDao picture=new pictureDao(SecurityUtils.getSubject().getPrincipals().toString(),fileUrl,description);
                ipictureList.inserPicture(picture);

            }
            iuserList.commitSession();//??????commit?????????????????????
        }

        System.out.println("content:"+description);
        return "????????????: ????????????";
    }

    @PostMapping( "/userExportLogic")
    @ResponseBody
    public List<pictureDao> exportPicture(){
        return ipictureList.selectListByUserName(SecurityUtils.getSubject().getPrincipals().toString());
    }

    @PostMapping("/pictuerDeleteLogic")
    @ResponseBody
    public responseDao deletePicture(@RequestParam(value="userName", required = false) String userName, @RequestParam(value="pictureUrl", required = false) String pictureUrl, @RequestParam(value="description", required = false) String description)
    //public String deletePicture(String userName,String pictureUrl)
    {
        //System.out.println("delete content: "+userName+"  "+pictureUrl);
        //System.out.println(picture.getUserName());

        //if(userName==loginName)

        String path=null;
        try{
            path=ResourceUtils.getURL("classpath:").getPath()+"static/image/"+userName+"/";
            String s = path.replaceAll("%20", " ");
            path = URLDecoder.decode(s, "utf-8");

            File file=new File(path,pictureUrl);
            if(file.exists()) {
                file.delete();
            }
            else{return new responseDao("????????????: ???????????????");}
        }catch (IOException e){
            return new responseDao("????????????: ??????????????????");
        }


        pictureDao picture=new pictureDao(userName,pictureUrl,description);
        ipictureList.deletePicture(picture);
        iuserList.commitSession();
        return new responseDao("????????????: ????????????");
    }


    public boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

}
