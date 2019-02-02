package com.cui.blog.demo.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * http工具类
 */
public class HttpClientUtil {

//    private URI getURI(String url) throws Exception {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        URI uri = new URIBuilder(url).build();
//        return uri;
//    }

    /**
     * 无参的get请求
     * @return
     */
    public String doGet(String url, List<NameValuePair> listParam) throws Exception {
        String content = null;
        URI uri = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        if(listParam == null || listParam.size() == 0) {
           uri = new URIBuilder(url).build();
        }else {
            uri = new URIBuilder(url).setParameters(listParam).build();
        }
        System.out.println(uri+"===============");
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response = null;
        try {
            //执行请求
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
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
        List<NameValuePair> listParam = new ArrayList<>();
        listParam.add(new BasicNameValuePair("q","title:index"));
        listParam.add(new BasicNameValuePair("q","context:可以仔细阅读这篇文章，"));
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String context = httpClientUtil.doGet("http://127.0.0.1:9200/blog/_search",  listParam);
        System.out.println(context);
    }

    /**
     * 带参数的get请求
     * @param param
     * @return
     */
//    public String doGet(String url,String param){
//
//        return "";
//    }

    /**
     * 无参的post请求
     * @return
     */
    public String doPost(){

        return "";
    }

    /**
     * 有参的post请求
     * @param param
     * @return
     */
    public String doPost(String param){

        return "";
    }
}
