package com.elysia.controller;

import com.elysia.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.fastjson.*;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.HashMap;
@Controller
@RequestMapping("/")
public class Genshin {
    int count;
    String result;
    @Autowired
    UserServices us;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/cardOne")
    @ResponseBody
    public String Once(@RequestParam String role, int yuan){
        String str = us.getJson();
        JSONObject c = JSON.parseObject(str);
        HashMap<String, Object> res = new HashMap<>();
        if (role.equals("ayaka") && yuan>=160){
            logger.info("抽取卡池为:" + c.getJSONArray("roles_1"));
            Random rd = new Random();
            count = rd.nextInt(10);
            result = c.getJSONArray("roles_1").get(count).toString();
            yuan -= 160;
            res.put("success", true);
            res.put("card", result);
            res.put("yuan", yuan);
        }else if (role.equals("yomiya") && yuan>=160) {
            logger.info("抽取卡池为:" + c.getJSONArray("roles_2"));
            Random rd = new Random();
            count = rd.nextInt(10);
            result = c.getJSONArray("roles_2").get(count).toString();
            yuan -= 160;
            res.put("success", true);
            res.put("card", result);
            res.put("yuan", yuan);
        }else if (role.equals("keqing") && yuan>=160) {
            logger.info("抽取卡池为:" + c.getJSONArray("roles_3"));
            Random rd = new Random();
            count = rd.nextInt(10);
            result = c.getJSONArray("roles_3").get(count).toString();
            yuan -= 160;
            res.put("success", true);
            res.put("card", result);
            res.put("yuan", yuan);
        }
        return JSON.toJSONString(res);
    }
    @PostMapping("/cardTen")
    @ResponseBody
    public String cardTen(@RequestParam String role, int yuan){
        String str = us.getJson();
        JSONObject c = JSON.parseObject(str);
        HashMap<String, Object> res = new HashMap<>();
        if (role.equals("ayaka") && yuan>=1600){
            logger.info("卡池" + c.getJSONArray("roles_1"));
            result = c.getJSONArray("roles_1").toString();
            yuan -= 1600;
            res.put("card", result);
            res.put("success", true);
            res.put("yuan", yuan);
        } else if (role.equals("yomiya") && yuan>=1600){
            logger.info("卡池" + c.getJSONArray("roles_2"));
            result = c.getJSONArray("roles_2").toString();
            yuan -= 1600;
            res.put("card", result);
            res.put("success", true);
            res.put("yuan", yuan);
        }else if (role.equals("keqing") && yuan>=1600){
            logger.info("卡池" + c.getJSONArray("roles_3"));
            result = c.getJSONArray("roles_3").toString();
            yuan -= 1600;
            res.put("card", result);
            res.put("success", true);
            res.put("yuan", yuan);
        }else {
            return "{\"success\":true, \"MsgNotFound\": \"出错了，请联系管理员吧！\"}";
        }
        return JSON.toJSONString(res);
    }

    // 充值
    @PostMapping("/money")
    @ResponseBody
    public String Money(@RequestParam int yuan){
        int[] list = {6, 30, 198, 328, 648};
        for (int j : list) {
            if (yuan == j) {
                yuan += (int) (j * 10 + j * 2.5);
            }
        }
        return String.format("{\"success\":true, \"yuan\":\"%d\"}", yuan);
    }
}
