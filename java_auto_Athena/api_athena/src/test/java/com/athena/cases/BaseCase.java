package com.athena.cases;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.athena.pojo.CaseInfo;
import com.athena.pojo.WriteBackData;
import com.athena.utils.ExceUtils;
import com.athena.utils.UserData;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.*;

import java.util.Map;
import java.util.Set;

/**
 * @Project:api_athena
 * @Date: 2022/3/19 12:31
 * @Author:Athena
 */
public class BaseCase {
    private Logger logger = Logger.getLogger(BaseCase.class);
    public void paramsReplace(CaseInfo caseInfo){
        String params = caseInfo.getParams();
        Set<String> keySet = UserData.vars.keySet();
        for(String vars_key : keySet){
            String vars_value = UserData.vars.get(vars_key).toString();
            if(!StringUtils.isBlank(params)){
                params = params.replace(vars_key,vars_value);
            }
        }
        //重新赋值参数化后的数据
        caseInfo.setParams(params);
    }
    @BeforeSuite
    public void steup(){
        logger.info("----------开始执行测试-----------------");
        //参数化,提前存值
        UserData.vars.put("${user}","15196174070");
        UserData.vars.put("${pwd}","123456");
        UserData.vars.put("${gqid}","4000002");
    }
    @AfterMethod
    public void AfterMethod(){
        logger.info("====================================");
    }
    //excel中开始及数量的全局变量
    public  int  startSheetIndex;
    public  int  sheetNum;
    @BeforeClass
    @Parameters({"startSheetIndex","sheetNum"})
    public void beforeClas(int startSheetIndex,int sheetNum){
        //接收tesng.xml中Parameters参数
        this.startSheetIndex=startSheetIndex;
        this.sheetNum=sheetNum;
    }
    /**
     * @ Author:Athena
     * @ Date 2022/3/19 15:43
     * @ Description //响应体重根据jsonpath取出数据存map(key:value)
     * @ Param[body, jsonPath, key]  key 自定义的 key (body中获取的数据)
     * @ return void
     */
    public void responseUserData(String body,String jsonPath,String key){
        Object value = JSONPath.read(body,jsonPath);
        if(value != null){
            UserData.vars.put(key,value);
        }
    }
    @AfterSuite
    public void resWriteExcel() throws Exception {
        //批量回写excrl
        ExceUtils.backWrite();

    }
    /**
    * @ Author:Athena
    * @ Date 2022/3/19 19:48
    * @ Description //添加响应内容到 list集合中
    * @ Param[rowNum, cellNum, sheetIndex, content]
    * @ return void
    */
    public void addresDataList(int rowNum, int cellNum, int sheetIndex, String content)  {
        // body ---第8行, CaseId用例ID---对应
        WriteBackData writeBackData = new WriteBackData(rowNum,cellNum,sheetIndex,content);
        ExceUtils.responseWriteList.add(writeBackData);
    }
    /**
    * @ Author:Athena
    * @ Date 2022/3/19 21:07
    * @ Description //接口响应断言,多字段匹配.期望和实际比较
    * @ Param[caseInfo, body]
    * @ return void
    */
    public boolean responseAssert(CaseInfo caseInfo,String body) {
        //默认断言结果
        boolean resResult = true;
        //{"code":0,"msg":"成功调用","data":{"id":80,"username":"4000003"}
        //{"$.code":0,"$.msg":"成功调用","$.data":""}===map<String,Object>===通过key取出实际的值和期望值去比较
        String expected = caseInfo.getExpected();
        //将期望的json字符串转map格式
        Map<String, Object> map = JSONObject.parseObject(expected, Map.class);
        //备注:key是已定义$.data格式的--json表达式的key,即符合期望和实际的,所以根据表达式获取期望和实际
        //遍历所有的key
        for (String key : map.keySet()) {
            //通过key获取期望值
            Object expectedValue = map.get(key);
            //通过key获取实际值
            Object actulValue = JSONPath.read(body, key);
            //期望和实际比较
            if (!expectedValue.equals(actulValue)) {
                logger.error("断言失败:"+ "json表达式:"+key+",实际值=" + actulValue + ",期望值=" + expectedValue);

                resResult = false;
            }

        if (resResult==true) {
            logger.info("断言失败:"+ "json表达式:"+key+",实际值=" + actulValue + ",期望值=" + expectedValue);


        }
        }
        return resResult;
    }

}
