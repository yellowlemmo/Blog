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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetSocketAddress;
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

    public static void main(String[] args) throws Exception {
        Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();// 集群名
//        QueryBuilder qb = QueryBuilders.matchAllQuery();
        TransportClient transportClient = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(new InetSocketAddress(1)));
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
