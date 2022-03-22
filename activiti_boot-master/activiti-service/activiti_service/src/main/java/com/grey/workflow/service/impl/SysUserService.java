package com.grey.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grey.workflow.entities.SysUser;
import com.grey.workflow.mapper.SysUserMapper;
import com.grey.workflow.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service // 不要少了
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser findByUsername(String username) {
        if(StringUtils.isEmpty(username)) {
            return null;
        }
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);

        SysUser sysUser = baseMapper.selectOne(wrapper);
        return sysUser;
    }
}
