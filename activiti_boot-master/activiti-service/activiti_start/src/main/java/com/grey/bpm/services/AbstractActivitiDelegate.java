package com.grey.bpm.services;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author Grey
 * @description
 * @date 2022/1/7 11:11
 */
public abstract class AbstractActivitiDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
    //    handle(execution);
    }

    protected abstract void handle(DelegateExecution execution);
}
