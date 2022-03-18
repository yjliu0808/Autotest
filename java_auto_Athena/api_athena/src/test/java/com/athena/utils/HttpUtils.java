package com.athena.utils;

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
