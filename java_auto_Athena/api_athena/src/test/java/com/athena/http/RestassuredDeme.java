package com.athena.http;
import com.alibaba.fastjson.JSONPath;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
/**
 * @Project:api_athena
 * @Date: 2022/3/15 21:19
 * @Author:Athena
 */
public class RestassuredDeme {

    /**
    * @ Author:Athena
    * @ Date 2022/3/17 16:35
    * @ Description //voc查询demo
    * @ Param
    * @ return  
    */
    @Test
    public void  http_post() {
        Response response = given()
                        .header("Content-Type", "application/json")
                        .body("{\"Content\":\"测试\"}")
                        .post("https://cloud.tencent.com/voc/gateway/DescribeRequirements");
        int statusCode = response.statusCode();
        Headers headers =response.headers();
        String body =response.asString();
        System.out.println(response.asString());
    }
    @Test
    public void  jsonpathTest() {
        Response response = given()
                .header("Content-Type", "application/json")
                .body("{\"Content\":\"测试\"}")
                .post("https://cloud.tencent.com/voc/gateway/DescribeRequirements");
        String body =response.asString();
        //JSONPath.read(json字符串,jsonpath表达式)
        System.out.println(JSONPath.read(body, "$.code"));
        System.out.println(JSONPath.read(body, "$.msg"));
        System.out.println(JSONPath.read(body, "$.data"));
    }

}

