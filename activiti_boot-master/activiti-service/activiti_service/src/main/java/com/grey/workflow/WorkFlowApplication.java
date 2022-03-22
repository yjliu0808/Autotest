package com.grey.workflow;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSwagger2Doc  // 开启swagger接口文档  localhost:9090/workflow/swagger-ui.html 登录名：user/密码在控制台
@SpringBootApplication
public class WorkFlowApplication {
    public static void main(String []args){

        SpringApplication.run(WorkFlowApplication.class,args);

    }
}

