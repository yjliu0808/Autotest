package com.grey.bpm.services;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * @author Grey
 * @description
 * @date 2022/1/6 17:33
 */
public class StartListener  implements ExecutionListener {
 
	private static final long serialVersionUID = 1L;

	@Override
    public void notify(DelegateExecution delegateExecution) {

    	System.out.println("******************* 已经进入到启动监听方法 *********************");

    }
}
