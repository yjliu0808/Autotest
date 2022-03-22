package com.grey.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.grey.workflow.entities.Leave;
import com.grey.workflow.req.LeaveREQ;
import com.grey.workflow.utils.Result;

public interface ILeaveService extends IService<Leave> {

    Result add(Leave leave);

    /**
     * 条件分页查询请假列表
     * @param req
     * @return
     */
    Result listPage(LeaveREQ req);

    Result update(Leave leave);
}
