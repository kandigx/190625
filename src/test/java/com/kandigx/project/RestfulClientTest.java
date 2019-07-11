package com.kandigx.project;

import com.alibaba.fastjson.JSON;
import com.kandigx.project.vo.OrderVO;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kandigx
 * @date 2019-07-11 11:06
 */
public class RestfulClientTest {

    private int size = 8000;

    @Test
    public void dataAcceptTest() throws IOException {

        HttpClient client = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://localhost:8080/accept/dataEntry");

        StringEntity entity = new StringEntity(getRequestBody(),"UTF-8");

        BasicHttpEntity httpEntity = new BasicHttpEntity();

        httpEntity.setContent(entity.getContent());
        httpEntity.setContentType("application/json");

        request.setEntity(httpEntity);
        request.setConfig(RequestConfig.DEFAULT);

        long startTime = System.currentTimeMillis();
        HttpResponse response = client.execute(request);
        long endTime = System.currentTimeMillis();

        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println("执行时间：" + (endTime-startTime));


    }

    private String getRequestBody() {

        List<OrderVO> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new OrderVO("1121212","2018-09-12 23:31:12",new ArrayList<>(),"12.21","99982812","Mike","1","19","19923313213","gei.@di.com"));
        }
        return JSON.toJSONString(list);
    }

}
