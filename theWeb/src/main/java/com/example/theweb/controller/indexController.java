package com.example.theweb.controller;
import com.example.theweb.dao.roleDao;
import com.example.theweb.dao.userDao;
import com.example.theweb.service.interfaceClass.IpermissionList;
import com.example.theweb.service.interfaceClass.IroleList;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.shiro.mgt.SecurityManager;
import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class indexController {

    @Autowired
    private IuserList iuserList;
    @Autowired
    private IpermissionList ipermissionList;
    @Autowired
    private IroleList iroleList;


    @RequestMapping("/")//redirect to login page
    public String toIndex(Model model){
        model.addAttribute("failInf","");
        return"index";
    }

    @RequestMapping("/login")//redirect to login page
    public String tologin(){
        return"index";
    }

    @RequestMapping("/register")//redirect to login page
    public String toregister(){
        return"register";
    }


    @RequestMapping("/registerLogic")
    public String registerPost(String txtAddress, String txtAge, String txtUsername, String txtPassword, String txtPasswordconfirm,Model model){
        System.out.println("login");
        //插入用户数据
        List<userDao> userList=iuserList.selectListByUserName(txtUsername);
        if(userList.size()>=1){
            System.out.println("User already exist");
            model.addAttribute("failInf","User already exist");
            return "register";
        }
        else if(!txtPassword.equals(txtPasswordconfirm)){
            System.out.println("The password is not equal");
            model.addAttribute("failInf","The password is not equal");
            return "register";
        }
        int age=Integer.valueOf(txtAge);
        if(age<0 ||age>100){age=0;}
        userDao user=new userDao(txtUsername,txtPassword,age,txtAddress);
        String info=iuserList.insertUser(user);
        if(!info.equals("success")){
            model.addAttribute("failInf","Insert Error: "+info);
            return "register";
        }

        //插入用户身份以及确认权限
        roleDao role=new roleDao(txtUsername,"user");
        String roleInfo=iroleList.insertRole(role);
        //注册信息检查没问题，正式向数据库提交信息
        iuserList.commitSession();


        //直接登录
        String loginInfo=login(txtUsername,txtPassword);
        if(!info.equals("success")){
            model.addAttribute("failInf","Role Error: "+loginInfo);
            return "register";
        }


        return "redirect:/guest/hello";
    }



    @RequestMapping("/loginLogic")
    public String LoginPost(String txtUsername, String txtPassword, Model model){
        String info=login(txtUsername,txtPassword);
        if(!info.equals("success")){
            model.addAttribute("failInf",info);
            return "index";
        }

//        model.addAttribute("user",txtUsername);
//        List<roleDao> roles=iroleList.selectListByUserName(txtUsername);
//        for(roleDao role:roles){
//            if(role.getRoleName().equals("admin"))
//                return "redirect:/admin/hello";
//        }


//        userDao user=new userDao(txtUsername,txtPassword);
//        boolean login=iuserList.loginByUserNameAndPassword(user);
//        //System.out.println(login);
//        if(login){
//            model.addAttribute("user",txtUsername);
//            return "redirect:/guest/hello";
//        }
//        else{
//            model.addAttribute("failInf","login fail");
//            return "loginfail";
//        }

        return "redirect:/guest/hello";
    }


    private String login(String txtUsername, String txtPassword){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(txtUsername,txtPassword);

        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            return "user does not exist";
        }catch (AuthenticationException e) {
            return "fail authorization";
        }
        return "success";
    }


}
