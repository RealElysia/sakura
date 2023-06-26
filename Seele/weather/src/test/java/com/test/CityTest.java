package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.func.queryWeather;

import java.util.List;

public class CityTest {
    String url = "https://weatherquery.api.bdymkt.com/weather/query/by-area";
    excelTest el = new excelTest();
    List<String> city = el.readExcel("src/test/java/com/test/city.xlsx");

    @Test(description = "北京天气查询")
    public void case_bj(){
        // 获取查询的城市和ID
        String bj = city.get(0);
        // 执行查询
        queryWeather wq = new queryWeather();
        JSONObject js = wq.weatherQuery(url, bj);
        // 返回的数据处理
        String res = JSON.toJSONString(js.get("data"));
        JSONObject ct = JSON.parseObject(res);
        // 查询后返回的城市
        String cx = ct.getString("area");
        // 断言，测试通过
        Assert.assertEquals(cx, bj, "case is success!");
    }

    @Test(description = "杭州天气查询")
    public void case_hz(){
        // 获取查询的城市
        String hz = city.get(1);
        // 执行查询
        queryWeather wq = new queryWeather();
        JSONObject js = wq.weatherQuery(url, hz);
        // 返回的数据处理
        String res = JSON.toJSONString(js.get("data"));
        JSONObject ct = JSON.parseObject(res);
        // 查询后返回的城市
        String cx = ct.getString("area");
        // 断言，测试通过
        Assert.assertEquals(cx, hz, "case is success!");
    }
}
