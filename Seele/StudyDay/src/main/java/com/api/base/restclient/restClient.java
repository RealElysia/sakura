package com.api.base.restclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;

// 请求方法封装
public class restClient {
    final static Logger log = Logger.getLogger(restClient.class);
    // get请求，无header
    public CloseableHttpResponse testGet(String url)throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        log.info("执行无请求头的get请求:");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        log.info("请求成功！");
        return response;
    }
    // 带有header的get请求
    public CloseableHttpResponse testGet(String url, HashMap<String, String> header)throws ClientProtocolException, IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        // 设置header
        for (Map.Entry<String, String> entry: header.entrySet()) {
            httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        log.info("执行有请求头的get请求:");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        log.info("请求成功！");
        return response;
    }
    // post请求
    public CloseableHttpResponse testPost(String url, String data, HashMap<String, String> header)throws IOException, ClientProtocolException{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost hp = new HttpPost(url);
        // 参数加载
        hp.setEntity(new StringEntity(data));
        for (Map.Entry<String, String> entry: header.entrySet()) {
            hp.addHeader(entry.getKey(), entry.getValue());
        }
        log.info("执行有请求头的post请求:");
        return httpClient.execute(hp);
    }
    // 请求状态吗
    public int getStatus(CloseableHttpResponse code){
        int status = code.getStatusLine().getStatusCode();
        log.info("本次请求状态码:" + status);
        return status;
    }
    // 返回内容
    public JSONObject getResponse(CloseableHttpResponse response)throws ParseException, IOException{
        log.info("String格式的返回参数");
        String str = EntityUtils.toString(response.getEntity(), "UTF-8");
        log.info("JSON返回参数");
        return JSON.parseObject(str);
    }
}
