package com.cui.blog.demo.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * http工具类
 */
public class HttpClientUtil {


    /**
     * get请求
     * @return
     */
    public static String doGet(String url, List<NameValuePair> listParam) throws Exception {
        String content = null;
        URI uri = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if(listParam == null || listParam.size() == 0) {
           uri = new URIBuilder(url).build();
        }else {
            uri = new URIBuilder(url).setParameters(listParam).build();
        }
        //创建get请求
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == GlobalParamter.SUCCESS){
                //解析响应,获取数据
               content = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }
        return content;
    }

    public static void main(String[] args) throws Exception{
        String json = "{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"should\": [\n" +
                "        { \"match\": { \"title\":  \"test\" }},\n" +
                "        { \"match\": { \"context\": \"test\"   }}\n" +
                "      ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
//        List<NameValuePair> listParam = new ArrayList<>();
//        listParam.add(new BasicNameValuePair("q","title:index"));
//        listParam.add(new BasicNameValuePair("q","context:可以仔细阅读这篇文章，"));
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String context = httpClientUtil.doPost("http://127.0.0.1:9200/blog/_search",  json);
        System.out.println(context);
    }


    /**
     * 有参的post请求
     * @param paramBody
     * @return
     */
    public static String doPost(String url,String paramBody) throws Exception {
        String content = null;
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建post请求
        HttpPost httpPost = new HttpPost(url);
        if(paramBody != null) {
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(paramBody));
        }
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == GlobalParamter.SUCCESS) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(response != null){
                response.close();
            }
            httpClient.close();
        }

        return content;
    }
}
