package com.athena.cases;

import com.alibaba.fastjson.JSONPath;
import com.athena.utils.ExceUtils;
import com.athena.utils.UserData;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

/**
 * @Project:api_athena
 * @Date: 2022/3/19 12:31
 * @Author:Athena
 */
public class BaseCase {
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
        System.out.println("测试0");
        ExceUtils.backWrite();
        System.out.println("测试2");
    }
}
