package com.grey.workflow;

import com.grey.workflow.enums.BusinessStatusEnum;
import org.junit.jupiter.api.Test;

public class BusinessStatusEnumTest {


    @Test
    public void test(){
        System.out.println("所有业务类型：");
          for (BusinessStatusEnum c : BusinessStatusEnum.values())
              System.out.println(c + " 编码：" + c.getCode());
    }
}
