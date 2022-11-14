package com.example.theweb.service.impl;

import com.example.theweb.dao.userDao;
import com.example.theweb.service.interfaceClass.IuserList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionException;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class userListImpl implements IuserList {

    //mapper文件没有配置好，先不用了，直接sqlsessionfactory生成sql连接
    //@Autowired(required = false)
    //private userListmapper usermapper;


    private SqlSession session;
    private boolean connect_state = false;

    @PostConstruct
    public void init() {
        int i = generateSqlSession();
        if (i == 1) {
            connect_state = true;
        }
    }


    @Override
    public int generateSqlSession() {
        String resource = "config/MyBatisCfg.xml";
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            if (sqlSessionFactory.getClass().getName() == null) {
                return 0;
            }
            this.session = sqlSessionFactory.openSession();

        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        return 1;
    }

    @Override
    public boolean loginByUserNameAndPassword(userDao user) {
        List<userDao> userlist=session.selectList("com.example.theweb.mapper.userListmapper.loginByPasswordAndUserName",user);
        //System.out.println(userlist.size()+" "+user.getUsername()+" "+user.getPassword());
        if(userlist.size()>0){return true;}
        else{return false;}
    }

    @Override
    public List<userDao> selectListByUserName(String userName) {
        return session.selectList("com.example.theweb.mapper.userListmapper.selectListByUserName",userName);
    }

    @Override
    public SqlSession getSession() {
        return session;
    }

    @Override
    public void commitSession() {
        session.commit();
    }

    @Override
    public void closeSession() {
        session.close();
    }

    @Override
    public String insertUser(userDao user) {
        try {
            session.insert("com.example.theweb.mapper.userListmapper.insertUser", user);
        }catch(SqlSessionException e){
            return e.getMessage();
        }
        return "success";
    }

    @Override
    public List<userDao> selectListAll() {
        return session.selectList("com.example.theweb.mapper.userListmapper.selectListAll");
    }

    @Override
    public void deleteUser(int id) {
        session.delete("com.example.theweb.mapper.userListmapper.deleteUserById",id);
    }

    @Override
    public void updateUser(userDao user) {
        session.update("com.example.theweb.mapper.userListmapper.updateUser",user);
    }


}
