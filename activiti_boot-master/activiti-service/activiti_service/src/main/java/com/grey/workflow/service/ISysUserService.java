package com.grey.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.grey.workflow.entities.SysUser;

public interface ISysUserService extends IService<SysUser> {

    SysUser findByUsername(String username);

}
