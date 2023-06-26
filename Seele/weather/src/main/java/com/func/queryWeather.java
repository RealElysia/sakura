package com.func;

import com.alibaba.fastjson.*;
import com.baidubce.http.*;
import com.baidubce.model.*;

public class queryWeather {
    // 查询天气的方法，百度API调用
    public JSONObject weatherQuery(String url, String area)
    {
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, url);
        // 公钥、密钥
        request.setCredentials("d37d227ee5724b8c9ad0645c63153016", "cc1bbf04eccc4583aee8772ca251a852");
        request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");
        request.addQueryParameter("area", area);
        ApiExplorerClient client = new ApiExplorerClient(new AppSigner());
        // 发送请求
        ApiExplorerResponse response = client.sendRequest(request);
        // 返回天气情况
        return JSON.parseObject(response.getResult());
    }
}