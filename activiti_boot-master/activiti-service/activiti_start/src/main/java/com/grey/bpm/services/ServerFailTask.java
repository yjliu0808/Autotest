package com.grey.bpm.services;

import org.activiti.engine.delegate.DelegateExecution;

/**
 * @author Grey
 * @description
 * @date 2022/1/12 10:58
 */
public class ServerFailTask extends AbstractActivitiDelegate {
    @Override
    protected void handle(DelegateExecution execution) {
        System.out.println("************** 进入失败处理方法 ******************");
    }
}
