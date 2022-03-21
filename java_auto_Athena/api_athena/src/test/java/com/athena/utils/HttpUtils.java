package com.athena.utils;

import com.athena.pojo.CaseInfo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @Project:api_athena
 * @Date: 2022/3/17 22:06
 * @Author:Athena
 */
public class HttpUtils {
    /**
    * @ Author:Athena
    * @ Date 2022/3/19 12:46
    * @ Description //获取默认请求头方法
    * @ Param[]
    * @ return java.util.Map<java.lang.String,java.lang.Object>
    */
    public static Map<String,Object> getDefaulHeaders(){
        //准备请求头数据
        Map<String,Object> headers = new HashMap<String, Object>();
        headers.put("Content-Type", "application/json");
        return headers;
    }
    public static String call(CaseInfo caseInfo,Map<String,Object> headers){
        //1.获取请求参数
        String url = caseInfo.getUrl();
        String params = caseInfo.getParams();
        String type = caseInfo.getType();
        //2.请求判断类型
        String body = null;
        if("post".equals(type)){
            body = HttpUtils.mypost(url,headers,params);
        }else if("get".equals(type)){
            body = HttpUtils.myget(url,headers);
        }else if("patch".equals(type)){
            body = HttpUtils.mypatch(url,headers,params);
        }
        System.out.println(body);
        return body;
    }
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:10
    * @ Description //get请求
    * @ Param[url, headers]   URL：请求地址  headers：请求头
    * @ return java.lang.String   响应体，不调用asString,返回response
    */
    public static  String myget(String url, Map<String,Object> headers){
        return given().headers(headers).get(url).asString();
    }
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:14
    * @ Description //post请求
    * @ Param[url, headers, params]   URL：请求地址  headers：请求头 ,params:json参数{ID:"1002",Nickname:""}
    * @ return java.lang.String  响应体，不调用asString,返回response
    */
    public static String mypost(String url, Map<String,Object> headers,String params){
        return given().headers(headers).body(params).post(url).asString();
    }
    /**
    * @ Author:Athena
    * @ Date 2022/3/17 22:13
    * @ Description //pach请求
    * @ Param[url, headers, params]   URL：请求地址  headers：请求头 ,params:json参数{ID:"1002",Nickname:"",}
    * @ return java.lang.String  响应体，不调用asString,返回response
    */
    public static String mypatch(String url, Map<String,Object> headers,String params){
        return given().headers(headers).body(params).patch(url).asString();
    }
}
