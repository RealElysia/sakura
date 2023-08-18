package com.elysia.controller;

import com.elysia.entity.User;
import com.elysia.entity.player;
import com.elysia.services.UserServices;
import com.elysia.services.playServices;
import com.web.Weibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServices us;
    @Autowired
    private playServices ps;

    /*
     * 费尽九牛二虎之力搭建的博客，也是老婆们的档案
     * @author: Bronya
     * */
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    // 登陆页
    @ResponseBody
    @PostMapping("/loginIn")
    public player loginIn(@RequestParam int id){
        player p = ps.getInfo(id);
        logger.info("数据库查询：{}", p);
        List<player> all = ps.All();
        logger.info("所有信息:" + all);
        if (all.contains(p)){
            return p;
        }else {
            return new player(404, "error", "MsgNotFound");
        }
    }
    // 首页博客
    @RequestMapping("/home")
    public String Home(){
        return "main";
    }
    // 账号注册
    @RequestMapping("/signup")
    public String signUp(){
        return "signUp";
    }

    @PostMapping("/register")
    @ResponseBody
    public player Register(@RequestParam String user, String pwd){
        List<player> allMsg = ps.All();
        if (allMsg.isEmpty()){
            player fs = new player(16101401, user, pwd);
            ps.saveNew(fs);
            return fs;
        }else {
            int id = allMsg.size() + 16101401;
            player other = new player(id,user,pwd);
            ps.saveNew(other);
            return other;
        }
    }
    // 纸片人老婆们
    @GetMapping("/laopo")
    public String getResult(){
        return "laopo";
    }
    // 这是第一个，爱莉希雅
    @GetMapping("/elysia")
    public String Elysia(@RequestParam String id, Model md){
        User ely = us.getUserById(id);
        md.addAttribute("elysia", ely);
        return "MyGirl/Elysia";
    }
    // 第二个，神里绫华
    @GetMapping("/ayaka")
    public String Ayaka(Model md, @RequestParam String id){
        User ayaka = us.getUserById(id);
        md.addAttribute("ayaka", ayaka);
        return "MyGirl/Ayaka";
    }
    // 刻晴
    @GetMapping("/keqing")
    public String Keqing(Model md, @RequestParam String id){
        User kq = us.getUserById(id);
        md.addAttribute("kq", kq);
        return "MyGirl/Keqing";
    }
    // 宵宫
    @GetMapping("/Yoimiya")
    public String Yoimiya(Model md, @RequestParam String id){
        User yo = us.getUserById(id);
        md.addAttribute("yo", yo);
        return "MyGirl/Yoimiya";
    }

    @GetMapping("/study")
    public String Study(){
        return "study";
    }

    @GetMapping("/selfWeb")
    public String SelfWeb(){
        return "SelfWeb";
    }

    @GetMapping("/study1")
    public String Study_1(){
        return "txt/study_1";
    }

    @GetMapping("/study2")
    public String Study_2(){
        return "txt/study_2";
    }

    @PostMapping("/selfWeb")
    @ResponseBody
    public String Web(@RequestParam String ck, String uid, int page) throws IOException {
        Weibo wb = new Weibo();
        File flo = new File("/Users/ayaka/Desktop/sakura/blog/miao");
        final String URL = "https://weibo.com/ajax/profile/getImageWall";

        if (flo.exists() && flo.isDirectory()){
            File[] f = flo.listFiles();
            if (f != null){
                for (File fi: f) {
                    if (fi.isFile()){
                        fi.delete();
                    }
                }
            }
        }

        for (int i=0; i<=page; i++){
            wb.save(URL, uid, ck);
        }
        return "{\"success\": true}";
    }
}
