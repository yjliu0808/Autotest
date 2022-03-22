package com.grey.workflow;

import com.grey.workflow.entities.SysUser;
import com.grey.workflow.service.ISysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestUser {

    @Autowired
    ISysUserService sysUserService;

    @Test
    public void test1() {
        SysUser sysUser = sysUserService.findByUsername("zhan");
        System.out.println(sysUser);
    }

}
