package com.grey.workflow.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.grey.workflow.entities.Loan;
import com.grey.workflow.req.LoanREQ;
import com.grey.workflow.utils.Result;

public interface ILoanService extends IService<Loan> {

    Result add(Loan loan);

    /**
     * 条件分页查询借款列表
     * @param req
     * @return
     */
    Result listPage(LoanREQ req);

    Result update(Loan loan);
}
