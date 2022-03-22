package com.grey.bpm.test;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ActivityStartTests {

    private static String resource = "process/approval.bpmn20.bpmn";
    private static String resourcePng = "process/approval.bpmn20.png";
    private static String processkey = "approval";
    private static String cretor = "grey";

    @Test
    public void contextLoads() {
    }

    /**
     * 使用 activiti 提供的默认方式来创建 mysql 的表
     */
    @Test
    public  void testCreateDbTable(){
        // 需要使用 acitiviti 提供的工具类 ProcessEngine ,使用方法  getDefaultProcessEngine
        // getDefaultProcessEngine 会默认从resources 下读取名字为 activiti.cfg.xml的文件
        // 创建processEngins ，就会创建 mysql 的表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }


    /**
     * 部署流程图文件，会涉及到下面4张表：
     * act_re_deployment 部署单元信息
     * act_re_procdef 已部署的流程定义
     * act_ge_bytearray 通用的流程定义和流程资源
     * act_ge_property 主键生成表，将生成下次流程部署的主键ID
     */
    @Test
    public void testDeployment(){
        // 1、创建 ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 3、使用 Service 进行流程的部署，定义一个流程的名字，把 Bpm 和 png 部署到数据库中
        Deployment deploy = repositoryService.createDeployment()
                .name("测试流程图")
                .addClasspathResource(resource)
                .addClasspathResource(resourcePng)
                .deploy();
        // 输出部署信息
        System.out.println("流程部署ID="+deploy.getId());
        System.out.println("流程部署名字="+deploy.getName());
    }

    /**
     * 查询部署好的流程定义数据
     */
    @Test
    public void getProcessDefinitionList(){
        // 1、创建 ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、获取 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 3、获取流程定义查询对象
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = query.processDefinitionKey(processkey).orderByProcessDefinitionAppVersion().desc().list();
        for (ProcessDefinition pd : processDefinitions) {
            System.out.print("流程部署ID:"+pd.getDeploymentId());
            System.out.print(", 流程定义ID:"+pd.getId());
            System.out.print(", 流程定义key:"+pd.getKey());
            System.out.print(", 流程定义名称:"+pd.getName());
            System.out.println(", 流程定义版本:"+pd.getVersion());
        }
    }

    /**
     * 启动流程实例，会涉及到下面7张表：
     * act_hi_taskinst 历史的任务实例
     * act_hi_procinst 历史的流程实例
     * act_hi_actinst 历史的流程实例
     * act_hi_identitylink 历史的流程运行过程中用户关系
     * act_ru_execution 运行时流程执行实例
     * act_ru_task 运行时任务
     * act_ru_identitylink 运行时用户关系信息，存储任务节点与参与者的相关信息
     *
     */
    @Test
    public void  testStartProcess(){
        //1、创建ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2、获取RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3、根据流程定义的id启动流程
        Map<String, Object> startMap = new HashMap<String, Object>();
        startMap.put("creator",cretor);
        startMap.put("flow",2);
        String applid = "test001"; // 初始化到表 act_hi_procinst.BUSINESS_KEY_

        // 通过 key 默认启动最新的版本
        ProcessInstance instance  = runtimeService.startProcessInstanceByKey(processkey,applid,startMap);

        //4、输出内容
        System.out.println("流程定义ID:"+instance.getProcessDefinitionId());
        System.out.println("流程实例ID:"+instance.getId());
        System.out.println("当前活动的ID："+instance.getActivityId());
    }

    /**
     * 个人任务列表查询
     */
    @Test
    public void testTaskListByAssignee(){
        // 1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2、获取 taskservice
        TaskService taskService = processEngine.getTaskService();

        // 3、根据流程 key 和 任务的负责人来查询任务
        List<Task> taskList = taskService.createTaskQuery()
                .processDefinitionKey(processkey)
                .taskAssignee("Grey")
                .list();

        // 4、输出
        for(Task task:taskList){
            System.out.println("流程实例ID=" + task.getProcessInstanceId());
            System.out.println("任务ID="+ task.getId());
            System.out.println("任务负责人="+task.getAssignee());
            System.out.println("任务名称="+task.getName());
        }
    }

    /**
     * 完成待处理任务
     */
    @Test
    public void completeTask(){
        // 1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2、获取Service
        TaskService taskService = processEngine.getTaskService();

        // 3、根据任务ID完成任务
        Map<String, Object> startMap = new HashMap<String, Object>();
        startMap.put("flow","toReceive");
        taskService.complete("7505",startMap);  // act_ru_task.ID_
    }

    /**
     * 查看流程办理历史信息
     */
    @Test
    public void historyInfo(){
        // 1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        // 2、获取 historyService
        HistoryService historyService = processEngine.getHistoryService();

        // 3、 获取节点历史记录查询对象 ACT_HI_ACTINST 表
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();

        // 实例 ID
        String processInstanceId = "12501";
        List<HistoricActivityInstance> list = query.processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceStartTime() // 根据开始时间排序 asc 升序
                .asc()
                .list();

        for (HistoricActivityInstance hi : list) {
            System.out.print("流程定义ID: " + hi.getProcessDefinitionId());
            System.out.print("，流程实例ID: " + hi.getProcessInstanceId());
            System.out.print("，节点ID: " + hi.getActivityId());
            System.out.print("，节点名称: " + hi.getActivityName());
            System.out.print("，任务办理人：" + hi.getAssignee());
            System.out.print("，开始时间：" + hi.getStartTime());
            System.out.println("结束时间：" + hi.getEndTime());
        }
    }




    /**
     * 单个实例流程挂起
     */
    @Test
    public void suspendSingleProcessInstance(){
        // 1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、通过RuntimeService获取流程实例对象
        ProcessInstance instance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("85001")  // act_ru_execution.PROC_INST_ID_
                .singleResult();

        //act_hi_procinst 历史的流程实例    BUSINESS_KEY_  = applid   通过ID 暂停
        //act_ru_execution 运行时流程执行实例  BUSINESS_KEY_  = applid   通过ID 暂停
        //可以通过 BUSINESS_KEY_ 找到 52501 来暂停单个实例流程

        // 4、得到当前流程实例的暂停状态
        boolean suspneded = instance.isSuspended();
        System.out.println("当前流程暂停状态：" + suspneded);

        // 5、获取流程实例ID
        String instanceId = instance.getId();

        // 6、判断是否已经暂停，如果已经暂停，就执行激活操作
        if(suspneded){
            runtimeService.activateProcessInstanceById(instanceId);
            System.out.println("流程定义id:"+instanceId+",已恢复"); // ACT_RU_EXECUTION.SUSPENSION_STATE_ 状态变为1
        }else{
            // 7、如果是激活状态，就执行暂停操作
            runtimeService.suspendProcessInstanceById(instanceId);
            System.out.println("流程定义id:"+instanceId+",已暂停");  // ACT_RU_EXECUTION.SUSPENSION_STATE_ 状态变为2
        }
    }

    /**
     * 等待节点触发流程测试
     */
    @Test
    public void ReceiveTaskTest(){
        // 1、获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 2、RuntimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();
        // 3、通过 trigger 触发流程节点向下流转
        Map<String, Object> startMap = new HashMap<String, Object>();
        startMap.put("assignee","test");
        startMap.put("flow","1");
        runtimeService.trigger("12504",startMap);  // act_ru_execution.ID_
    }

    @Test
    public void findHisToryInfo(){
        // 获取引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
         // 获取 historyservice
        HistoryService historyService = processEngine.getHistoryService();
        // 获取 actinst表的查询对象
        HistoricActivityInstanceQuery instanceQuery =  historyService.createHistoricActivityInstanceQuery();
        // 查询 actinst 表
        instanceQuery.processInstanceId("2501");
        // 增加排序操作 asc 升序
        instanceQuery.orderByHistoricActivityInstanceStartTime().asc();

        // 查询所有内容
        List<HistoricActivityInstance> activitInstanceList = instanceQuery.list();
        for(HistoricActivityInstance hi :activitInstanceList ){
            System.out.println(hi.getActivityId());
            System.out.println(hi.getActivityName());
            System.out.println(hi.getProcessDefinitionId());
            System.out.println(hi.getProcessInstanceId());
            System.out.println("========================");
        }
    }

}
