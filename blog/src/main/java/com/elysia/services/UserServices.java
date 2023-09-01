package com.elysia.services;

import com.elysia.entity.User;
import com.elysia.mapping.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.io.*;
@Service
public class UserServices {
    @Autowired
    private UserMapper userMapper;
    private String str;

    public User getUserById(String id){
        return userMapper.getUserById(id);
    }

    public int delete(String id){return userMapper.delete(id);}

    public int update(User user){return userMapper.update(user);}

    public User save(User user){
        int save = userMapper.saveNew(user);
        return user;
    }

    public List<User> selectAll( ){
        return userMapper.selectAll();
    }
    public String getJson(){
        File f = new File("/usr/local/apache-tomcat-10.1.11/webapps/blog/WEB-INF/classes/static/RoleCards.json");
        try {
            FileReader fileReader = new FileReader(f);
            Reader reader = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuilder stb = new StringBuilder();
            while ((ch = reader.read()) != -1) {
                stb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            str = stb.toString();
        }catch (IOException e){
            System.out.println("JSON文件读取错误，请检查");
        }
        return str;
    }
}
