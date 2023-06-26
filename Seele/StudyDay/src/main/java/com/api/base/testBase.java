package com.api.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class testBase {
    public Properties pro;
    // 读取配置文件
    public testBase(){
        try {
            pro = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir")+
                    "/src/main/resources/config.properties");
            pro.load(file);
        }catch (FileNotFoundException e){e.printStackTrace();}
        catch (IOException e){e.printStackTrace();}
    }
}
