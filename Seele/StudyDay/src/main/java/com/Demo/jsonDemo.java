package com.Demo;

import com.alibaba.fastjson.*;
import com.api.base.util.testUtil;
import java.util.regex.*;

public class jsonDemo {

    public static void main(String[] args) {
        String path = "src/main/java/com/Demo/sheep.json";
        JSONObject json = testUtil.readJson(path);
        JSONObject result = (JSONObject) json.get("result");
        // 获取data的value，数组
        String data = result.getString("data");
        JSONArray array = JSON.parseArray(data);
        JSONObject a = array.getJSONObject(0);
        // 验证ID
        String id = a.getString("id");
        boolean isId = Pattern.matches("6269", id);
        JSONArray h = a.getJSONArray("albums");
        String url = h.getString(0);
        // 匹配验证网址
        String p1 = "^http.*\\.com.*jpg$";
        boolean isHttp = Pattern.matches(p1, url);
        // 匹配邮箱
        String email = a.getString("email");
        String p2 = "^[a-z]+[0-9]+@.*\\.com$";
        boolean isEmail = Pattern.matches(p2, email);
        System.out.println(isId);
    }
}
