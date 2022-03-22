package com.grey.bpm.services;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author Grey
 * @description
 * @date 2022/1/7 11:19
 */
public class EndListener implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("******************* 已经进入到结束监听方法 *********************");

    }
}
