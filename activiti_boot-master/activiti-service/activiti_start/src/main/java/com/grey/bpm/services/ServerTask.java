package com.grey.bpm.services;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;

/**
 * @author Grey
 * @description
 * @date 2022/1/7 11:04
 */
public class ServerTask extends AbstractActivitiDelegate {
    private Expression text1;
    private Expression text2;

    @Override
    protected void handle(DelegateExecution execution) {
        System.out.println("************** 进入自动处理节点 ******************");

        String value1 = (String) text1.getValue(execution);
        execution.setVariable("var1", new StringBuffer(value1).reverse().toString());

        String value2 = (String) text2.getValue(execution);
        execution.setVariable("var2", new StringBuffer(value2).reverse().toString());
    }
}
