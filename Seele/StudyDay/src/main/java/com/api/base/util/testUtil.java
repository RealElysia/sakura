package com.api.base.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class testUtil {
    // JSON解析类
    public static String getValue(JSONObject resp, String jsonPath){
        Object obj = resp;
        for(String s: jsonPath.split("/")){
            if (!s.isEmpty()){
                if (!(s.contains("[")||s.contains("]"))){
                        obj = ((JSONObject) obj).get(s);
                } else if (s.contains("[") || s.contains("]")) {
                    obj =((JSONArray)((JSONObject)obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replaceAll("]", "")));
                }
            }
        }
        return obj.toString();
    }
    // JSON 文件读取
    public static JSONObject readJson(String fileName){
        String jsonStr = "";
        File jsonFile = new File(fileName);
        try{
            FileReader fr = new FileReader(jsonFile);
            Reader r = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch=r.read())!=-1){
                sb.append((char)ch);
            }
            fr.close();
            r.close();
            jsonStr = sb.toString();
        }catch (FileNotFoundException e){
            JSONObject jsonNot = new JSONObject();
            jsonNot.put("code", "404");
            jsonNot.put("error", "file is not found");
            return jsonNot;
        }catch (IOException e){
            e.printStackTrace();
        }
        return JSONObject.parseObject(jsonStr);
    }
}
