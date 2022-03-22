package com.grey.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.grey.workflow.entities.Loan;
import com.grey.workflow.req.LoanREQ;
import org.apache.ibatis.annotations.Param;

public interface LoanMapper extends BaseMapper<Loan> {

    IPage<Loan> getLoanAndStatusList(IPage<Loan> page, @Param("req") LoanREQ req);

}
