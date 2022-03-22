package com.grey.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.grey.workflow.entities.BusinessStatus;
import com.grey.workflow.enums.BusinessStatusEnum;
import com.grey.workflow.utils.Result;

public interface IBusinessStatusService extends IService<BusinessStatus> {

    /**
     * 新增数据：状态 WAIT(1, "待提交")
     * @param businessKey
     * @return
     */
    int add(String businessKey);

    /**
     * 更新业务状态
     * @param businessKey 业务id
     * @param statusEnum 状态值
     * @param procInstId 流程实例id
     * @return
     */
    Result updateState(String businessKey, BusinessStatusEnum statusEnum, String procInstId);

    /**
     * 更新业务状态
     * @param businessKey 业务id
     * @param statusEnum 状态值
     * @return
     */
    Result updateState(String businessKey, BusinessStatusEnum statusEnum);

}
