package com.api.base.test;

import java.io.IOException;
import java.util.HashMap;
import com.alibaba.fastjson.JSON;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import com.api.base.testBase;
import com.api.base.restclient.restClient;
import com.api.base.util.testUtil;

public class TestApi extends testBase{
    testBase tb;
    String host;
    String url, url_2;
    restClient rc;
    CloseableHttpResponse ch;
    HashMap<String, String> header;
    final static Logger log = Logger.getLogger(TestApi.class);
    // 前置条件
    @BeforeClass
    public void setUp(){
        tb = new testBase();
        host = pro.getProperty("HOST");
        url = host + "/apiv3/user/selfPosts?uid=1810398&since=1633951612";
        url_2 = "https://havanalogin.taobao.com/newlogin/login.do";
        log.info("执行用例的URL是:" + url);
        header = new HashMap<String, String>();
        header.put("X-Bce-Signature", "AppCode/e0d0574761fb4c54bb350dee104d8a00");
        header.put("content-type", "application/json; charset=utf-8");
    }

    @Test
    public void getTest() throws IOException{
        log.info("开始执行用例");
        rc = new restClient();
        ch = rc.testGet(url, header);
        // 断言
        int code = rc.getStatus(ch);
        Assert.assertEquals(code, 200, "request is failed");
        JSONObject resp = rc.getResponse(ch);
//        System.out.println("返回数据:" + resp);
        String pic = testUtil.getValue(resp,"data/items[2]/item_detail/image_list[0]/path");
        String str = testUtil.getValue(resp,"data/items[1]/item_detail/detail_plain");
        String since = testUtil.getValue(resp, "data/items[0]/since");
//        System.out.println(pic);
        Assert.assertEquals(since, "1631418457", "failed! Try again!");
        log.info("SUCCESS\t" + pic + str);
    }

    @Test
    public void postTest() throws IOException{
        rc = new restClient();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("appName", "qianniu");
        data.put("fromSite", "0");
        data.put("_bx-v", "2.2.3");
        String json = JSON.toJSONString(data);
        ch = rc.testPost(url_2, json, header);
        int code = rc.getStatus(ch);
        Assert.assertEquals(code, 200, "request is failed");
        JSONObject resp = rc.getResponse(ch);
        System.out.println("返回数据:" + resp);
//        String str = testUtil.getValue(resp, "status");
//        Assert.assertEquals(str, "true", "test case is failed");
        log.info("test case is passed! Beautiful!");
    }
}
