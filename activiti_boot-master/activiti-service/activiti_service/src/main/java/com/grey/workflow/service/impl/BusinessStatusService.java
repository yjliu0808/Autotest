package com.grey.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.grey.workflow.entities.BusinessStatus;
import com.grey.workflow.enums.BusinessStatusEnum;
import com.grey.workflow.mapper.BusinessStatusMapper;
import com.grey.workflow.service.IBusinessStatusService;
import com.grey.workflow.utils.Result;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BusinessStatusService extends ServiceImpl<BusinessStatusMapper, BusinessStatus>
        implements IBusinessStatusService {


    @Override
    public int add(String businessKey) {
        BusinessStatus bs = new BusinessStatus();
        // 待提交
        bs.setStatus(BusinessStatusEnum.WAIT.getCode());
        bs.setBusinessKey(businessKey);
        return baseMapper.insert(bs);
    }

    @Override
    public Result updateState(String businessKey, BusinessStatusEnum statusEnum, String procInstId) {
        // 1. 查询当前数据
        BusinessStatus bs = baseMapper.selectById(businessKey);
        // 2. 设置状态值
        bs.setStatus(statusEnum.getCode());
        bs.setUpdateDate(new Date());

        // 只要判断不为null,就更新，因为后面有个地方传递“”
        if(procInstId != null) {
            bs.setProcessInstanceId(procInstId);
        }

        // 3. 更新操作
        baseMapper.updateById(bs);
        return Result.ok();
    }

    @Override
    public Result updateState(String businessKey, BusinessStatusEnum statusEnum) {
        return updateState(businessKey, statusEnum, null);
    }

}
